package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Regional;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class RegionalManager extends AbstractManager<Regional> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Regional> getModelClazz() {
		return Regional.class;
	}

}
