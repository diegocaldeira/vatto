package com.aoks.enterprise.control.factory;

import javax.inject.Inject;

import com.aoks.enterprise.control.bean.LegalPartnerBean;
import com.aoks.enterprise.model.entities.LegalPartner;
import com.aoks.enterprise.model.entities.PartnerType;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.factory.LegalRegisterFactory;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

public class LegalPartnerFactory implements GenericFactory<LegalPartner> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private LegalRegisterFactory legalFactory;
	@Inject private PersistenceWrapper wrapper;

	@Override
	public LegalPartner create(GenericBean<LegalPartner> _bean) {
		
		LegalPartnerBean bean = (LegalPartnerBean)_bean;
		
		LegalPartner model = bean.getModel();
		if (model == null) {
			model = new LegalPartner();
			bean.setModel(model);
			wrapper.saveOrUpdate(model);
		}
		
		model = (LegalPartner)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		try {
			model.setType(PartnerType.valueOf(bean.getType()));
		} catch (Exception e) {}
		
		LegalRegister legalRegister = legalFactory.create((LegalRegisterBean) bean.getRegister());
		model.getBehavior().setRegister(legalRegister);
		
		return model;
	}

}
