package com.aoks.banking.operation.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.operation.model.InvestmentAccount;
import com.aoks.utils.webmvc.AbstractManager;


@RequestScoped
public class InvestmentAccountManager extends AbstractManager<InvestmentAccount> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<InvestmentAccount> getModelClazz() {
		return InvestmentAccount.class;
	}

}
