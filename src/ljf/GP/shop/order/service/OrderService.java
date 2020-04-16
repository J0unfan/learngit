package ljf.GP.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ljf.GP.shop.order.dao.OrderDao;
import ljf.GP.shop.order.vo.Order;
import ljf.GP.shop.utils.PageBean;

/**
 *
 * @author ljf
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单
	public void save(Order order) {
		orderDao.save(order);
	}

	// 根据用户id查询订单，分页
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
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
		List<Order> list = orderDao.findPageByUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//
}
