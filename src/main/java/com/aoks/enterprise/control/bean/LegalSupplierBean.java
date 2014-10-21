package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.LegalSupplier;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericFactory;

public class LegalSupplierBean extends SupplierBean<LegalSupplier> {

	private static final long serialVersionUID = 1L;
	
	private LegalSupplier model;
	
	public LegalSupplierBean() {
		super(new LegalRegisterBean());
	}
	
	@Override
	public String getDefaultRegistration() {
		String ret = "";
		try {
			ret = register.getRegistration("cnpj").getValue();
		} catch (Exception e) {}
		return ret;
	}
	
	@Override
	public boolean isIndividual() {
		return false;
	}
	@Override
	public boolean isLegal() {
		return true;
	}
	
	
	@Override
	public void setName(String name) {
		try {
			((LegalRegisterBean)register).setCompanyName(name);
		} catch (Exception e) {}
		super.setName(name);
	}
	
	
	@Override
	public LegalSupplier build(GenericFactory<LegalSupplier> factory) {
		return factory.create(this);
	}

	@Override
	public LegalSupplier getModel() {
		return model;
	}
	public void setModel(LegalSupplier model) {
		this.model = model;
	}
	

	@Override
	public LegalSupplierBean load(LegalSupplier model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.id = model.getId();
		this.model = model;
		
		this.register = (RegisterBean<?>) new LegalRegisterBean().load((LegalRegister) model.getBehavior().getRegister());
		
		return this;
	}
	
}
