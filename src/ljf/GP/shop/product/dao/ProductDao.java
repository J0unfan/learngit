package ljf.GP.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ljf.GP.shop.product.vo.Product;

/**
 * 商品持久层
 * 
 * @author ljf
 */
public class ProductDao extends HibernateDaoSupport {

	// 首页热门商品展示
	public List<Product> findHot() {
		// 离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> products = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return products;
	}

	// 首页最新商品展示查询
	public List<Product> findNew() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> products = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);

		return products;
	}

}
