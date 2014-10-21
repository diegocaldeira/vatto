package com.aoks.utils.category.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.utils.category.model.CategorizedApplication;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class CategorizedApplicationManager extends AbstractManager<CategorizedApplication> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Class<CategorizedApplication> getModelClazz() {
		return CategorizedApplication.class;
	}
	
	public CategorizedApplication loadByName(String name){
		CategorizedApplication categorizedApplication = wrapper.get(CategorizedApplication.class, "name", name);
		return categorizedApplication;
	}

	
	@Override
	public CategorizedApplication createModel() {
		return new CategorizedApplication();
	}

}
