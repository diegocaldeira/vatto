package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.CompanyGroup;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class CompanyGroupManager extends AbstractManager<CompanyGroup> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<CompanyGroup> getModelClazz() {
		return CompanyGroup.class;
	}

}
