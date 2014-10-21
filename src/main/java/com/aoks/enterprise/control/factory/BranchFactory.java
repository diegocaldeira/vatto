package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.BranchBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Branch;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class BranchFactory implements GenericFactory<Branch> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Branch create(GenericBean<Branch> _bean) {
		
		BranchBean bean = (BranchBean)_bean;
		
		Branch model = bean.getModel();
		if (model == null) {
			model = new Branch();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Branch)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Company company = (bean.getCompany() != null ? bean.getCompany().getModel() : null);
		
		if (company != null){
			
			EnterpriseBehavior companyBehavior = company.getBehavior();
			EnterpriseBehavior branchBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("structureDimension", companyBehavior, branchBehavior);
			
		}
		
		return model;
	}

}
