package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.Partner;
import com.aoks.register.control.bean.RegisterBean;


public abstract class PartnerBean<T extends Partner> extends EnterpriseEntityBean<T> {

	private static final long serialVersionUID = 1L;

	public PartnerBean(RegisterBean<?> register) {
		super(register);
	}
	
}
