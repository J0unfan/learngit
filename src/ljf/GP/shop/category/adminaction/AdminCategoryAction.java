package ljf.GP.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.category.service.CategoryService;
import ljf.GP.shop.category.vo.Category;

/**
 * 后台一级分类管理action
 * 
 * @author ljf
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	// 模型驱动使用的对象
	private Category category = new Category();

	@Override
	public Category getModel() {
		return category;
	}

	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 查询所有一级分类
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		// 保存到值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	// 保存一级分类的方法
	public String save() {
		// 调用Service完成保存一级分类
		categoryService.save(category);
		// 进行页面跳转:
		return "saveSuccess";
	}

	// 删除一级分类的方法:
	public String delete() {
		// 调用Service完成 一级分类的删除
		// 级联删除一定先查询在删除:
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		// 进行页面转向:
		return "deleteSuccess";
	}

	// 编辑一级分类的方法:
	public String edit() {
		// 接收cid:
		// 根据cid进行查询:
		category = categoryService.findByCid(category.getCid());
		// 完成页面转向:将一级分类数据显示到页面上.
		return "editSuccess";
	}

	// 修改一级分类的方法:
	public String update() {
		// 使用模型驱动接收前台提交数据:
		categoryService.update(category);
		// 页面跳转:
		return "updateSuccess";
	}
}
