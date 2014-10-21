package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.ResultCenter;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class ResultCenterManager extends AbstractManager<ResultCenter> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<ResultCenter> getModelClazz() {
		return ResultCenter.class;
	}

}
