package ljf.GP.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ljf.GP.shop.order.vo.Order;
import ljf.GP.shop.order.vo.OrderItem;
import ljf.GP.shop.utils.PageHibernateCallback;

/**
 *
 * @author ljf
 */
public class OrderDao extends HibernateDaoSupport {
	// 保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// 查询我的订单：统计订单数量
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 查询我的订单：分页查询订单数据
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, new Object[] { uid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// DAO层根据订单id查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// DAO层修改订单的方法:
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// DAO层统计所有订单数量
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// DAO层分页查询订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	// DAO根据订单id查询订单项
	public List<OrderItem> findOrderItems(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
