package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.AccountabilityType;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class AccountabilityTypeBean implements GenericBean<AccountabilityType> {

	private static final long serialVersionUID = 1L;

	private long id;
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public AccountabilityType build(GenericFactory<AccountabilityType> factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountabilityType getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericBean<AccountabilityType> load(AccountabilityType model) {
		// TODO Auto-generated method stub
		return null;
	}

}
