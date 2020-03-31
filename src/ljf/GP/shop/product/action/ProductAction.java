package ljf.GP.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.category.service.CategoryService;
import ljf.GP.shop.product.service.ProductService;
import ljf.GP.shop.product.vo.Product;
import ljf.GP.shop.utils.PageBean;

/**
 * 商品的Action
 * 
 * @author ljf
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	// 模型驱动
	private Product product = new Product();
	// 注入service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 接收分类cid
	private Integer cid;
	// 接收二级分类id
	private Integer csid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// 注入一级分类service
	private CategoryService categoryService;
	// 接收当前页数
	private int page;

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public Product getModel() {
		return product;
	}

	// 根据商品ID进行查询商品
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	// 根据一级分类id查询商品
	public String findByCid() {
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		// 将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	// 根据二级分类id查询商品
	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
