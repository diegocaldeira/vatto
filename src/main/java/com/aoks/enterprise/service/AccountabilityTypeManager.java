package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.AccountabilityType;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class AccountabilityTypeManager extends AbstractManager<AccountabilityType> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<AccountabilityType> getModelClazz() {
		return AccountabilityType.class;
	}

	public AccountabilityType loadByName(String name){
		
		AccountabilityType type = this.wrapper.get(AccountabilityType.class, "name", name);
		return type;
		
	}

}
