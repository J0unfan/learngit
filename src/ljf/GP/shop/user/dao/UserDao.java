package ljf.GP.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ljf.GP.shop.user.vo.User;
import ljf.GP.shop.utils.PageHibernateCallback;

/**
 *
 * @author ljf
 */
public class UserDao extends HibernateDaoSupport {

	// 根据username查询是否有此用户
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// 注册用户存入数据库方法
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	// 根据激活码查询用户
	public User findByCode(String code) {
		String sql = "from User where code = ?";
		List<User> user = this.getHibernateTemplate().find(sql, code);
		if (user != null && user.size() > 0) {
			return user.get(0);
		}
		return null;
	}

	// 修改用户状态
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	// 用户登录
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> users = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	// 用户总数
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页显示用户信息
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

	// 根据用户id查找
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	// 删除用户
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
}
