package com.aoks.register.service;

import com.aoks.register.model.Phone;
import com.aoks.utils.webmvc.AbstractManager;

public class PhoneManager extends AbstractManager<Phone> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Phone> getModelClazz() {
		return Phone.class;
	}

}
