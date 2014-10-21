package com.aoks.register.service;

import com.aoks.register.model.Email;
import com.aoks.utils.webmvc.AbstractManager;

public class EmailManager extends AbstractManager<Email> {

	private static final long serialVersionUID = 1L;
	

	@Override
	public Class<Email> getModelClazz() {
		return Email.class;
	}
}
