package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.RegionalBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.Regional;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class RegionalFactory implements GenericFactory<Regional> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Regional create(GenericBean<Regional> _bean) {
		
		RegionalBean bean = (RegionalBean)_bean;
		
		Regional model = bean.getModel();
		if (model == null) {
			model = new Regional();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Regional) wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Company company = (bean.getCompany() != null ? bean.getCompany().getModel() : null);
		
		if (company != null){
			
			EnterpriseBehavior companyBehavior = company.getBehavior();
			EnterpriseBehavior regionalBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("regionalDimension", companyBehavior, regionalBehavior);
			
		}
		
		return model;
	}

}
