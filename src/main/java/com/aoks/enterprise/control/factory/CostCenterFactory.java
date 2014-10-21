package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.CostCenterBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.CostCenter;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class CostCenterFactory implements GenericFactory<CostCenter> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public CostCenter create(GenericBean<CostCenter> _bean) {
		
		CostCenterBean bean = (CostCenterBean)_bean;
		
		CostCenter model = bean.getModel();
		if (model == null) {
			model = new CostCenter();
			wrapper.saveOrUpdate(model);
		}
		
		model = (CostCenter)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Company company = (bean.getCompany() != null ? bean.getCompany().getModel() : null);
		
		if (company != null){
			
			EnterpriseBehavior companyBehavior = company.getBehavior();
			EnterpriseBehavior costCenterBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("costDimension", companyBehavior, costCenterBehavior);
			
		}
		
		return model;
	}

}
