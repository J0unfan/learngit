package ljf.GP.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ljf.GP.shop.categorysecond.service.CategorySecondService;
import ljf.GP.shop.categorysecond.vo.CategorySecond;
import ljf.GP.shop.product.service.ProductService;
import ljf.GP.shop.product.vo.Product;
import ljf.GP.shop.utils.PageBean;

/**
 * 后台商品管理的Action
 *
 * @author ljf
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();

	private Integer page;

	private ProductService productService;

	private CategorySecondService categorySecondService;

	@Override
	public Product getModel() {
		return product;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 文件上传需要的三个属性：
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	// 查询所有的商品
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将PageBean数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}

	// 跳转到添加页面的方法:
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	// 保存商品的方法
	public String save() throws ParseException, IOException {
		// 时间格式的转换
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = temp.format(date);
		Date date3 = temp.parse(date2);

		product.setPdate(date3);
		if (upload != null) {
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File diskFile = new File(realPath + "//" + uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		// System.out.println(product.getCategorySecond().toString());
		return "saveSuccess";
	}

	// 删除商品的方法:
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}

	// 跳转到编辑商品页面的方法
	public String edit() {
		product = productService.findByPid(product.getPid());

		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);

		// 页面跳转到编辑页面:
		return "editSuccess";
	}

	// 编辑商品的方法
	public String update() throws ParseException, IOException {
		// 将信息修改到数据库
		// 时间格式的转换
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = temp.format(date);
		Date date3 = temp.parse(date2);

		product.setPdate(date3);
		// 上传图片
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
