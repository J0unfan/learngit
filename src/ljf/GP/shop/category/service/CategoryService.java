package ljf.GP.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ljf.GP.shop.category.dao.CategoryDao;
import ljf.GP.shop.category.vo.Category;

/**
 * 一级分类service层
 * 
 * @author ljf
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// 查询所有一级分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	// 新增一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}

	// 删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	// 修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
	}

	// 根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
}
