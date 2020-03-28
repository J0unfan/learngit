package ljf.GP.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ljf.GP.shop.category.service.CategoryService;
import ljf.GP.shop.category.vo.Category;
import ljf.GP.shop.product.service.ProductService;
import ljf.GP.shop.product.vo.Product;

/**
 * 首页访问Action
 * 
 * @author ljf
 */
public class IndexAction extends ActionSupport {
	// 注入一级分类service
	private CategoryService categoryService;

	private ProductService productService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 访问首页的方法
	@Override
	public String execute() throws Exception {
		// 查询所有一级分类并放入session
		List<Category> categories = categoryService.findAll();
		ActionContext.getContext().getSession().put("categories", categories);
		// 查询热门商品并存入值栈
		List<Product> hList = productService.findHotProducts();
		ActionContext.getContext().getValueStack().set("hList", hList);
		// 查询最新商品并存入值栈
		List<Product> nList = productService.findNewProducts();
		ActionContext.getContext().getValueStack().set("nList", nList);

		return "index";
	}
}
