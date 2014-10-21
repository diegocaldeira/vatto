package com.aoks.banking.operation.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.operation.model.CreditCardAccount;
import com.aoks.utils.webmvc.AbstractManager;


@RequestScoped
public class CreditCardAccountManager extends AbstractManager<CreditCardAccount> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<CreditCardAccount> getModelClazz() {
		return CreditCardAccount.class;
	}

}
