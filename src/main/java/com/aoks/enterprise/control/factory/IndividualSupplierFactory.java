package com.aoks.enterprise.control.factory;

import javax.inject.Inject;

import com.aoks.enterprise.control.bean.IndividualSupplierBean;
import com.aoks.enterprise.model.entities.IndividualSupplier;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.factory.IndividualRegisterFactory;
import com.aoks.register.model.IndividualRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

public class IndividualSupplierFactory implements GenericFactory<IndividualSupplier> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private IndividualRegisterFactory individualFactory;
	@Inject private PersistenceWrapper wrapper;

	@Override
	public IndividualSupplier create(GenericBean<IndividualSupplier> _bean) {
		
		IndividualSupplierBean bean = (IndividualSupplierBean)_bean;
		
		IndividualSupplier model = bean.getModel();
		if (model == null) {
			model = new IndividualSupplier();
			bean.setModel(model);
			wrapper.saveOrUpdate(model);
		}
		
//		model = (IndividualPartner)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());
		
		IndividualRegister individualRegister = individualFactory.create((IndividualRegisterBean) bean.getRegister());
		model.getBehavior().setRegister(individualRegister);

		return model;
	}

}
