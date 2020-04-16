package ljf.GP.shop.user.service;
/**
*
* @author ljf
*/

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ljf.GP.shop.user.dao.UserDao;
import ljf.GP.shop.user.vo.User;
import ljf.GP.shop.utils.MailUtils;
import ljf.GP.shop.utils.PageBean;
import ljf.GP.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 按用户名查找用户
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void save(User user) {
		// 存入用户信息到数据库
		user.setState(0);// 0:表示用户未激活，1:表示用户已经激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// 发送激活邮件
		System.out.println(user.getEmail());
		MailUtils.sendMail(user.getEmail(), code);
	}

	// 根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 修改用户的状态
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}

	// 后台查询所有用户信息, 分页
	public PageBean<User> fingByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示10个
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1) * limit;
		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据用户id查找
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	// 删除用户
	public void delete(User existUser) {
		userDao.delete(existUser);
	}
}
