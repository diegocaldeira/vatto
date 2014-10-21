package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.CostCenter;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class CostCenterManager extends AbstractManager<CostCenter> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<CostCenter> getModelClazz() {
		return CostCenter.class;
	}

}
