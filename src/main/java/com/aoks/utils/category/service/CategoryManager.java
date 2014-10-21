package com.aoks.utils.category.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class CategoryManager extends AbstractManager<Category> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Category> getModelClazz() {
		return Category.class;
	}

	@Override
	public Category createModel() {
		return new Category();
	}
	
}
