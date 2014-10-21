package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Department;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class DepartmentManager extends AbstractManager<Department> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Department> getModelClazz() {
		return Department.class;
	}

}
