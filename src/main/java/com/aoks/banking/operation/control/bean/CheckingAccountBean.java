package com.aoks.banking.operation.control.bean;

import com.aoks.banking.bank.control.bean.BankBean;
import com.aoks.banking.operation.model.CheckingAccount;
import com.aoks.utils.webmvc.GenericFactory;

public class CheckingAccountBean extends BankAccountBean<CheckingAccount> implements Comparable<CheckingAccountBean>{

	private static final long serialVersionUID = 1L;
	
	private CheckingAccount model;

	@Override
	public CheckingAccount build(GenericFactory<CheckingAccount> factory) {
		
		if (model == null)
			model = new CheckingAccount();
			
		if (bank != null)
			model.setBank(bank.getModel());
		
		model.setBankUnit(bankUnit);
		model.setBankUnitDigit(bankUnitDigit);
		model.setDescription(description);
		model.setDigit(digit);
		model.setNumber(number);
		model.setOpening(opening);
		model.setStart(start);
		model.setStartBalance(startBalance);
		
		return model;
	}

	@Override
	public CheckingAccount getModel() {
		return model;
	}
	
	@Override
	public CheckingAccountBean load(CheckingAccount model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		loadEntries(model);
		
		return this;
		
	}

	public CheckingAccountBean load(CheckingAccount model, boolean onlyLazy) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		if (model.getBank() != null)
			this.bank = new BankBean().load(model.getBank());
		
		this.bankUnit = model.getBankUnit();
		this.bankUnitDigit = model.getBankUnitDigit();
		this.description = model.getDescription();
		this.digit = model.getDigit();
		this.number = model.getNumber();
		this.opening = model.getOpening();
		this.start = model.getStart();
		this.startBalance = model.getStartBalance();
		
		return this;
	}
	

	public void setId(long id){ this.id = id;}
	
	
	@Override
	public int compareTo(CheckingAccountBean _bean) {
		return number.compareTo(_bean.number);
	}

}
