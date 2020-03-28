package ljf.GP.shop.categorysecond.vo;
/**
* 二级分类的实体类对象
* @author ljf
*/

import java.util.HashSet;
import java.util.Set;

import ljf.GP.shop.category.vo.Category;
import ljf.GP.shop.product.vo.Product;

public class CategorySeconds {
	private Integer csid;
	private String csname;
	// 所属的一级分类
	private Category category;
	// 二级分类下的商品集合
	private Set<Product> products = new HashSet<Product>();

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
