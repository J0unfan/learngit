package ljf.GP.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import ljf.GP.shop.cart.vo.Cart;
import ljf.GP.shop.cart.vo.CartItem;
import ljf.GP.shop.product.service.ProductService;
import ljf.GP.shop.product.vo.Product;
import ljf.GP.shop.user.vo.User;

/**
 *
 * @author ljf
 */
public class CartAction extends ActionSupport {
	// 接收pid
	private Integer pid;
	// 接收数量count
	private Integer count;
	// 注入商品的Service
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 将购物项添加到购物车
	public String addCart() {
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		// 从session中获取购物车
		Cart cart = getCart();
		cart.addCart(cartItem);

		return "addCart";
	}

	// 清空购物车
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();

		return "clearCart";
	}

	// 从购物车中移除购物项
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);

		return "removeCart";
	}

	// 我的购物车：展示的执行方法
	public String myCart() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (user != null) {
			return "myCart";
		} else {
			return ActionSupport.LOGIN;
		}
	}

	/**
	 * 获得购物车的方法:从session中获得购物车
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
