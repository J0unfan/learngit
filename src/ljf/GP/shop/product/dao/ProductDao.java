package ljf.GP.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ljf.GP.shop.product.vo.Product;
import ljf.GP.shop.utils.PageHibernateCallback;

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

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 根据分类ID查询商品个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据分类id查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据二级分类id查询商品个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据二级分类查询商品信息
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { csid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 统计商品总数
	public int findCount() {
		String sql = "select count(*) from Product";
		List<Long> re = this.getHibernateTemplate().find(sql);
		if (re != null && re.size() > 0) {
			return re.get(0).intValue();
		}
		return 0;
	}

	// 后台查询所有商品的方法
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		// hibernate分页查询的方法
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	// 删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	// 更新商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
