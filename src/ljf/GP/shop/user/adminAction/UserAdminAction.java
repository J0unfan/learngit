package ljf.GP.shop.user.adminAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.user.service.UserService;
import ljf.GP.shop.user.vo.User;
import ljf.GP.shop.utils.PageBean;

/**
 * 后台用户管理的action
 * 
 * @author ljf
 */
public class UserAdminAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 注入Service
	private UserService userService;

	// 接收pege参数
	private Integer page;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	// 后台查询所有用户，带分页
	public String findAll() {
		PageBean<User> pageBean = userService.fingByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 后台用户删除
	public String delete() {
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}

	// 后台用户编辑
	public String edit() {
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}

	// 后台用户修改
	public String update() {
		userService.update(user);
		return "updateSuccess";
	}

}
