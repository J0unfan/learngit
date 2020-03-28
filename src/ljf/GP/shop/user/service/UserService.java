package ljf.GP.shop.user.service;
/**
*
* @author ljf
*/

import ljf.GP.shop.user.dao.UserDao;
import ljf.GP.shop.user.vo.User;
import ljf.GP.shop.utils.MailUtils;
import ljf.GP.shop.utils.UUIDUtils;

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
}
