package ljf.GP.shop.category.vo;
/**
* 一级分类实体类对象
* @author ljf
*/

import java.util.HashSet;
import java.util.Set;

public class Category {
	private Integer cid;
	private String cname;
	// 一级分类中二级分类的集合
	private Set categorySeconds = new HashSet<>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
}
