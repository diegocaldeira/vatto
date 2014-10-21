package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.DepartmentBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.Department;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class DepartmentFactory implements GenericFactory<Department> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Department create(GenericBean<Department> _bean) {
		
		DepartmentBean bean = (DepartmentBean)_bean;
		
		Department model = bean.getModel();
		if (model == null) {
			model = new Department();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Department)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Company company = (bean.getCompany() != null ? bean.getCompany().getModel() : null);
		
		if (company != null){
			
			EnterpriseBehavior companyBehavior = company.getBehavior();
			EnterpriseBehavior departmentBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("structureDimension", companyBehavior, departmentBehavior);
			
		}
		
		return model;
	}

}
