package com.aoks.enterprise.control.factory;

import javax.inject.Inject;

import com.aoks.enterprise.control.bean.LegalSupplierBean;
import com.aoks.enterprise.model.entities.LegalSupplier;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.factory.LegalRegisterFactory;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

public class LegalSupplierFactory implements GenericFactory<LegalSupplier> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private LegalRegisterFactory legalFactory;
	@Inject private PersistenceWrapper wrapper;

	@Override
	public LegalSupplier create(GenericBean<LegalSupplier> _bean) {
		
		LegalSupplierBean bean = (LegalSupplierBean)_bean;
		
		LegalSupplier model = bean.getModel();
		if (model == null) {
			model = new LegalSupplier();
			bean.setModel(model);
			wrapper.saveOrUpdate(model);
		}
		
//		model = (IndividualPartner)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());
		
		LegalRegister legalRegister = legalFactory.create((LegalRegisterBean) bean.getRegister());
		model.getBehavior().setRegister(legalRegister);
		
		return model;
	}

}
