package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.CostumerBean;
import com.aoks.enterprise.model.entities.Costumer;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.factory.LegalRegisterFactory;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class CostumerFactory implements GenericFactory<Costumer> {

	private static final long serialVersionUID = 1L;
	
	@Inject private LegalRegisterFactory legalFactory;
	@Inject private PersistenceWrapper wrapper;

	@Override
	public Costumer create(GenericBean<Costumer> _bean) {
		
		CostumerBean bean = (CostumerBean)_bean;
		
		Costumer model = bean.getModel();
		if (model == null) {
			model = new Costumer();
			wrapper.saveOrUpdate(model);
		}
		
		model.getBehavior().setName(bean.getName());

		LegalRegister legalRegister = legalFactory.create((LegalRegisterBean) bean.getRegister());
		model.getBehavior().setRegister(legalRegister);
		
		return model;
	}

}
