package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.RepresentativeBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Regional;
import com.aoks.enterprise.model.entities.Representative;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class RepresentativeFactory implements GenericFactory<Representative>{

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Representative create(GenericBean<Representative> _bean) {
		
		RepresentativeBean bean = (RepresentativeBean)_bean;
		
		Representative model = bean.getModel();
		if (model == null) {
			model = new Representative();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Representative)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Regional regional = (bean.getRegional() != null ? bean.getRegional().getModel() : null);
		
		if (regional != null){
			
			EnterpriseBehavior regionalBehavior = regional.getBehavior();
			EnterpriseBehavior representativeBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("regionalDimension", regionalBehavior, representativeBehavior);
			
		}
		
		return model;
	}

}
