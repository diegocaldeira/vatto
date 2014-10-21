package com.aoks.banking.bank.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.bank.model.Bank;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class BankManager extends AbstractManager<Bank> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Bank> getModelClazz() {
		return Bank.class;
	}

}
