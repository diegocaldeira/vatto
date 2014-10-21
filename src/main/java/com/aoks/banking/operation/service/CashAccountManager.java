package com.aoks.banking.operation.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.operation.model.CashAccount;
import com.aoks.utils.webmvc.AbstractManager;


@RequestScoped
public class CashAccountManager extends AbstractManager<CashAccount> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<CashAccount> getModelClazz() {
		return CashAccount.class;
	}

	
	
}
