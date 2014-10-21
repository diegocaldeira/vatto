package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.IndividualPartnerBean;
import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.enterprise.model.entities.PartnerType;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.factory.IndividualRegisterFactory;
import com.aoks.register.model.IndividualRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class IndividualPartnerFactory implements GenericFactory<IndividualPartner> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private IndividualRegisterFactory individualFactory;
	@Inject private PersistenceWrapper wrapper;

	@Override
	public IndividualPartner create(GenericBean<IndividualPartner> _bean) {
		
		IndividualPartnerBean bean = (IndividualPartnerBean)_bean;
		
		IndividualPartner model = bean.getModel();
		if (model == null) {
			model = new IndividualPartner();
			bean.setModel(model);
			wrapper.saveOrUpdate(model);
		}
		
		model = (IndividualPartner)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		IndividualRegister individualRegister = ((IndividualRegisterBean) bean.getRegister()).build(individualFactory);
		model.getBehavior().setRegister(individualRegister);

		try {
			model.setType(PartnerType.valueOf(bean.getType()));
		} catch (Exception e) {}
		
		
		return model;
	}

	public PersistenceWrapper getWrapper() 					{ return wrapper; }
	public IndividualRegisterFactory getIndividualFactory() { return individualFactory; }

}
