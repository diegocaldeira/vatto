package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.utils.webmvc.GenericBean;

public abstract class EnterpriseEntityBean<T extends EnterpriseEntity> implements GenericBean<T> {

	private static final long serialVersionUID = 1L;

	protected long id;
	protected String name;
	protected RegisterBean<?> register;
	
	
	public EnterpriseEntityBean(RegisterBean<?> register) {
		this.register = register;
	}
	
	@Override
	public long getId() {
		return id;
	}

	
	public RegisterBean<?> getRegister() {
		return register;
	}
	public void setRegister(RegisterBean<?> register) {
		this.register = register;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
