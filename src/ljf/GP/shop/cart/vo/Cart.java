package ljf.GP.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ljf
 */
public class Cart implements Serializable {
	// 购物项集合，Map的key就是商品pid,value:购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	// Cart对象中有一个cartitems属性
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// 购物总计
	private double total;

	public double getTotal() {
		return total;
	}

	// 1.将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		// 商品id
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项
		if (map.containsKey(pid)) {
			CartItem cartItem2 = map.get(pid); // 购物车中原来的购物项
			cartItem2.setCount(cartItem2.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		map.clear();
		total = 0;
	}
}
