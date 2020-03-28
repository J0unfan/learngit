package ljf.GP.shop.user.action;
/**
*
* @author ljf
*/

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.user.service.UserService;
import ljf.GP.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的对象
	private User user = new User();

	public User getModel() {
		return user;
	}

	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 接收验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 跳转到注册页面的方法
	 */
	public String registPage() {
		return "registPage";
	}

	/**
	 * ajax进行异步校验用户名的方法
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String findByName() throws UnsupportedEncodingException {
		String result = "yes";
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} else {
			// 没查询到该用户:用户名可以使用
			result = "no";
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		}
		return SUCCESS;
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String codeCheck() throws UnsupportedEncodingException {
		// 结果返回“yes”正确，返回“no”错误
		String result = "yes";
		// 从session中获得验证码
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");

		if (!checkcode.equalsIgnoreCase(checkcode1)) {
			result = "no";
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} else {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		}
		return SUCCESS;
	}

	/**
	 * 用户注册
	 */
	public String regist() {
		userService.save(user);
		this.addActionMessage("注册成功!请去邮箱激活!");
		return "msg";
	}

	/**
	 * 用户激活的方法
	 */
	public String action() {
		User existUser = userService.findByCode(user.getCode());
		// 判断激活码
		if (existUser == null) {
			// 激活码错误的
			this.addActionMessage("激活失败:激活码错误!");
		} else {
			// 激活成功
			// 修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功:请去登录!");
		}
		return "msg";
	}

	/**
	 * 跳转到登录页面
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 登录的方法
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// 登录失败
			this.addActionError("登录失败:用户名或密码错误或用户未激活!");
			return LOGIN;
		} else {
			// 登录成功
			// 将用户的信息存入到session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			// 页面跳转
			return "loginSuccess";
		}
	}

	/**
	 * 用户退出的方法
	 */
	public String quit() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
