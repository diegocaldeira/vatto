package com.aoks.banking.operation.control.bean;

import com.aoks.banking.bank.control.bean.BankBean;
import com.aoks.banking.operation.model.SavingsAccount;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class SavingsAccountBean extends BankAccountBean<SavingsAccount> implements Comparable<SavingsAccountBean>{

	private static final long serialVersionUID = 1L;
	
	private SavingsAccount model;

	@Override
	public SavingsAccount build(GenericFactory<SavingsAccount> factory) {
		
		if (model == null)
			model = new SavingsAccount();
			
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
	public SavingsAccount getModel() {
		return model;
	}

	
	@Override
	public SavingsAccountBean load(SavingsAccount model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		loadEntries(model);
		
		return this;
		
	}
	
	
	public SavingsAccountBean load(SavingsAccount model, boolean onlyLazy) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		if (model.getBank() != null)
			this.bank = new BankBean().load(model.getBank());
		
		this.bankUnit = model.getBankUnit();
		this.bankUnitDigit = model.getBankUnit();
		this.description = model.getDescription();
		this.digit = model.getDigit();
		this.number = model.getNumber();
		this.opening = model.getOpening();
		this.start = model.getStart();
		this.startBalance = model.getStartBalance();
		
		return this;
	}

	
	@Override
	public int compareTo(SavingsAccountBean _bean){
		return this.number.compareTo(_bean.number);
	}
	
}
