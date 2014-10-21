package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.IndividualSupplier;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.IndividualRegister;
import com.aoks.utils.webmvc.GenericFactory;

public class IndividualSupplierBean extends SupplierBean<IndividualSupplier> {

	private static final long serialVersionUID = 1L;
	
	private IndividualSupplier model;
	
	public IndividualSupplierBean() {
		super(new IndividualRegisterBean());
	}
	
	
	@Override
	public boolean isIndividual() {
		return true;
	}
	@Override
	public boolean isLegal() {
		return false;
	}
	
	
	@Override
	public String getDefaultRegistration() {
		String ret = "";
		try {
			ret = register.getRegistration("cpf").getValue();
		} catch (Exception e) {}
		return ret;
	}


	@Override
	public IndividualSupplier build(GenericFactory<IndividualSupplier> factory) {
		return factory.create(this);
	}


	@Override
	public IndividualSupplier getModel() {
		return model;
	}
	public void setModel(IndividualSupplier model) {
		this.model = model;
	}


	@Override
	public IndividualSupplierBean load(IndividualSupplier model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getBehavior().getName();
		
		this.register = (RegisterBean<?>) new IndividualRegisterBean().load((IndividualRegister) model.getBehavior().getRegister());
		
		return this;
	}
	
}
