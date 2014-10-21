package com.aoks.banking.operation.service;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;

import com.aoks.banking.operation.model.CheckingAccount;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 *
 */
@RequestScoped
public class CheckingAccountManager extends AbstractManager<CheckingAccount>{

	private static final long serialVersionUID = 1L;
	
	public BigInteger nextIdValid(){
		BigInteger id = (BigInteger) wrapper.get("banking.bankingsequence", "ctable", "BankingSequence", "cnext");

		id = (id == null) ? new BigInteger("1") : id;

		return id;
	}

	@Override public Class<CheckingAccount> getModelClazz() { return CheckingAccount.class; }

}
