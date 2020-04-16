package ljf.GP.shop.order.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.order.vo.Order;

/**
 *
 * @author ljf
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}
}
