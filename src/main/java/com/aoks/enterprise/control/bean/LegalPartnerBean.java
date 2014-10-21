package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.LegalPartner;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericFactory;

public class LegalPartnerBean extends PartnerBean<LegalPartner> implements Comparable<LegalPartnerBean>{

	private static final long serialVersionUID = 1L;
	
	private String type;
	private LegalPartner model;
	
	public LegalPartnerBean() {
		super(new LegalRegisterBean());
	}
	
	public String getType() 			{ return type; }
	public void setType(String type) 	{ this.type = type; }

	@Override
	public LegalPartner build(GenericFactory<LegalPartner> factory) {
		return factory.create(this);
	}

	@Override
	public LegalPartner getModel() 				{ return model; }
	public void setModel(LegalPartner model)	{ this.model = model; }

	@Override
	public LegalPartnerBean load(LegalPartner model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.id = model.getId();
		this.model = model;
		
		if (model.getType() != null)
			this.type = model.getType().name();
		
		this.register = (RegisterBean<?>) new LegalRegisterBean().load((LegalRegister) model.getBehavior().getRegister());
		
		return this;
	}

	@Override
	public int compareTo(LegalPartnerBean _bean) {
		return (((LegalRegisterBean) getRegister()).getCompanyName().compareTo((((LegalRegisterBean) _bean.register).getCompanyName())));
	}

}
