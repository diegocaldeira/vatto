package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.Supplier;
import com.aoks.register.control.bean.RegisterBean;


public abstract class SupplierBean<T extends Supplier> extends EnterpriseEntityBean<T> {

	private static final long serialVersionUID = 1L;

	public abstract String getDefaultRegistration();
	public abstract boolean isIndividual();
	public abstract boolean isLegal();
	
	public SupplierBean(RegisterBean<?> register) {
		super(register);
	}
	
}
