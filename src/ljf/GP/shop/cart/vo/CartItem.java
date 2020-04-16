package ljf.GP.shop.cart.vo;
/**
*
* @author ljf
*/

import ljf.GP.shop.product.vo.Product;

public class CartItem {
	private Product product; // 商品信息
	private int count; // 某商品数量
	private double subtotal; // 某商品小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 小计自动计算
	public double getSubtotal() {
		return count * product.getShop_price();
	}
}
