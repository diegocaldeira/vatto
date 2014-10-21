package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Branch;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class BranchManager extends AbstractManager<Branch> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Branch> getModelClazz() {
		return Branch.class;
	}

}
