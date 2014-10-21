package com.aoks.banking.operation.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.operation.model.SavingsAccount;
import com.aoks.utils.webmvc.AbstractManager;


@RequestScoped
public class SavingsAccountManager extends AbstractManager<SavingsAccount> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<SavingsAccount> getModelClazz() {
		return SavingsAccount.class;
	}

}
