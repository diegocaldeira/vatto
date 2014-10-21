package com.aoks.utils.category.control.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CategoryBean implements GenericBean<Category> {

	private static final long serialVersionUID = 1L;
	
	private Category model;
	
	private long id;
	
	private String code;
	private String name;
	private String description;
	
	private CategoryBean parent;
	private List<CategoryBean> childs;
	private CategoryDimensionBean dimension;
	
	public CategoryBean() {
		childs = new ArrayList<CategoryBean>();
	}
	
	
	@Override
	public CategoryBean load(Category model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.name = model.getName();
		this.code = model.getCode();
		this.description = model.getDescription();
		this.id = model.getId();
		
		Set<Category> ccs = model.getChilds();
		for (Category category : ccs) {
			childs.add(new CategoryBean().load(category));
		}
		
		if(this.dimension != null){
			this.dimension = new CategoryDimensionBean().load(model.getDimension());
			this.dimension.addCategory(this);
		}

		if(this.parent != null){
			this.parent = new CategoryBean().load(model.getParent());
			this.parent.getParent().addChild(this);
		}
	
		return this;
	}
	@Override
	public Category build(GenericFactory<Category> factory) {
		
		if (model == null)
			model = new Category();
		
		model.setCode(code);
		model.setDescription(description);
		model.setName(name);
		
		if (dimension != null){
			model.setDimension(dimension.getModel());
			dimension.getModel().addCategory(model);
		}
		
		if (parent != null){
			model.setParent(parent.getModel());
			parent.getModel().addChild(model);
		}
		
		return model;
	}
	@Override
	public Category getModel() {
		return model;
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
	
	
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	
	
	public List<CategoryBean> getChilds(){
		return childs;
	}
	public void setChilds(List<CategoryBean> childs){
		this.childs = childs;
	}
	public void addChild(CategoryBean child){
		childs.add(child);
	}
	
	
	public CategoryBean getParent() {
		return parent;
	}
	public void setParent(CategoryBean parent) {
		this.parent = parent;
	}
	
	
	public void setDimension(CategoryDimensionBean dimension) {
		this.dimension = dimension;
	}
	public CategoryDimensionBean getDimension() {
		return dimension;
	}
	
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CategoryBean)) return false;
		CategoryBean that = (CategoryBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if ((this.dimension == null ? that.dimension == null : this.dimension.equals(that.dimension))
				 && (this.code == null ? that.code == null : this.code.equals(that.code)))
			return true;;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else {
			result = result * 31 + (code != null ? code.hashCode() : 0);
			result = result * 31 + (dimension != null ? dimension.hashCode() : 0);
		}
		return result;
	}
	
}
