package com.aoks.utils.category.control.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aoks.utils.category.model.CategorizedApplication;
import com.aoks.utils.category.model.CategoryDimension;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class CategorizedApplicationBean implements GenericBean<CategorizedApplication> {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private CategorizedApplication model;
	
	private List<CategoryDimensionBean> dimensions;
	
	public CategorizedApplicationBean() {
		dimensions = new ArrayList<CategoryDimensionBean>();
	}
	
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	 
	
	public List<CategoryDimensionBean> getDimensions(){
		return dimensions;
	}
	public void setDimensions(List<CategoryDimensionBean> dimensions){
		this.dimensions = dimensions;
	}
	
	
	@Override
	public CategorizedApplicationBean load(CategorizedApplication model) {
		
		if (model == null)
			throw new IllegalStateException();

		this.model = model;
		
		this.name = model.getName();
		this.id = model.getId();
		
		Set<CategoryDimension> dims = model.getDimensions();
		for (CategoryDimension categoryDimension : dims) {
			dimensions.add(new CategoryDimensionBean().load(categoryDimension));
		}
		
		return this;
	}
	@Override
	public CategorizedApplication build(GenericFactory<CategorizedApplication> factory) {
		
		if (model == null)
			model = new CategorizedApplication();
		
		model.setName(name);
		
		return model;
	}
	@Override
	public CategorizedApplication getModel() {
		return model;
	}
	
	
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CategorizedApplicationBean)) return false;
		CategorizedApplicationBean that = (CategorizedApplicationBean) obj;
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


}
