package com.aoks.utils.category.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.utils.category.model.CategoryDimension;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class CategoryDimensionManager extends AbstractManager<CategoryDimension> {

	private static final long serialVersionUID = 1L;
	
	
	public CategoryDimension loadByName(String name){
		return wrapper.get(CategoryDimension.class, "name", name);
	}

	
	@Override
	public CategoryDimension createModel() {
		return new CategoryDimension();
	}

	@Override
	public Class<CategoryDimension> getModelClazz() {
		return CategoryDimension.class;
	}

}
