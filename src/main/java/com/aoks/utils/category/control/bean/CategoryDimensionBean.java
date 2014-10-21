package com.aoks.utils.category.control.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aoks.utils.category.model.CategorizedApplication;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.category.model.CategoryDimension;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;


public class CategoryDimensionBean implements GenericBean<CategoryDimension>, Comparable<CategoryDimensionBean> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private CategoryDimension model;
	
	private String name;
	private String description;
	
	private List<CategoryBean> categories;
	private List<CategorizedApplicationBean> categorizedApplications;
	
	public CategoryDimensionBean() {
		categories = new ArrayList<CategoryBean>();
		categorizedApplications = new ArrayList<CategorizedApplicationBean>();
	}
	
	
	@Override
	public CategoryDimensionBean load(CategoryDimension model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.name = model.getName();
		this.description = model.getDescription();
		this.id = model.getId();
		
		Set<Category> cats = model.getCategories();
		for (Category category : cats) {
			categories.add(new CategoryBean().load(category));
		}
		
		return this;
		
	}
	@Override
	public CategoryDimension build(GenericFactory<CategoryDimension> factory) {
		
		if (model == null)
			model = new CategoryDimension();
		
		model.setDescription(description);
		model.setName(name);

		List<CategorizedApplicationBean> applications = getCategorizedApplications();
		
		for (CategorizedApplicationBean categorizedApplicationBean : applications) {
			CategorizedApplication catapp = categorizedApplicationBean.getModel();
			if (catapp != null)
				model.addCategorizedApplication(catapp);
		}
		
		return model;
	}
	@Override
	public CategoryDimension getModel() {
		return model;
	}
	
	
	@Override
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public void addCategory(CategoryBean categoryBean) {
		categories.add(categoryBean);
	}
	public List<CategoryBean> getCategories(){
		return categories;
	}
	public void setCategories(List<CategoryBean> categories){
		this.categories = categories;
	}
	
	
	public List<CategorizedApplicationBean> getCategorizedApplications() {
		return categorizedApplications;
	}
	public void setCategorizedApplications(List<CategorizedApplicationBean> categorizedApplications) {
		this.categorizedApplications = categorizedApplications;
	}
	public void addCategorizedApplication(CategorizedApplicationBean categorizedApplication) {
		categorizedApplications.add(categorizedApplication);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CategoryDimensionBean)) return false;
		CategoryDimensionBean that = (CategoryDimensionBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if (this.name == null ? that.name == null : this.name.equalsIgnoreCase(that.name))
			return true;
		return false;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else 
			result = result * 31 + (name != null ? name.hashCode() : 0);
		return result;
	}
	
	@Override
	public int compareTo(CategoryDimensionBean that) {
		return (new Long(this.id).compareTo(new Long(that.id))) * -1;
	}


}
