package ljf.GP.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ljf.GP.shop.product.dao.ProductDao;
import ljf.GP.shop.product.vo.Product;

/**
 * 商品的service层
 * 
 * @author ljf
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页热门商品
	public List<Product> findHotProducts() {
		return productDao.findHot();
	}

	// 首页最新商品
	public List<Product> findNewProducts() {
		return productDao.findNew();
	}
}
