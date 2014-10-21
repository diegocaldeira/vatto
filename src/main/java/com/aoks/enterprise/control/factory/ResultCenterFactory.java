package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.ResultCenterBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.ResultCenter;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class ResultCenterFactory implements GenericFactory<ResultCenter> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public ResultCenter create(GenericBean<ResultCenter> _bean) {
		
		ResultCenterBean bean = (ResultCenterBean)_bean;
		
		ResultCenter model = bean.getModel();
		if (model == null) {
			model = new ResultCenter();
			wrapper.saveOrUpdate(model);
		}
		
		model = (ResultCenter)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Company company = (bean.getCompany() != null ? bean.getCompany().getModel() : null);
		
		if (company != null){
			
			EnterpriseBehavior companyBehavior = company.getBehavior();
			EnterpriseBehavior resultCenterBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("resultDimension", companyBehavior, resultCenterBehavior);
			
		}
		
		return model;
	}

}
