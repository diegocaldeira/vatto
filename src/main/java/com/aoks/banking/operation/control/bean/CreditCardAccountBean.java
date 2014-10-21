package com.aoks.banking.operation.control.bean;

import java.util.Calendar;

import com.aoks.banking.operation.model.CreditCardAccount;
import com.aoks.banking.operation.model.CreditCardFlag;
import com.aoks.utils.webmvc.GenericFactory;


public class CreditCardAccountBean extends OperationAccountBean<CreditCardAccount> implements Comparable<CreditCardAccountBean>{

	private static final long serialVersionUID = 1L;
	
	private CreditCardAccount model;
	
	private String cardNumber;
	private String flag;
	private Calendar start;
	private String maturityDay;
	private String calculationTime;
	private CheckingAccountBean linkedAccount;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

	public Calendar getStart() {
		return start;
	}
	public void setStart(Calendar start) {
		this.start = start;
	}
	

	public String getMaturityDay() {
		return maturityDay;
	}
	public void setMaturityDay(String maturityDay) {
		this.maturityDay = maturityDay;
	}

	
	public String getCalculationTime() {
		return calculationTime;
	}
	public void setCalculationTime(String calculationTime) {
		this.calculationTime = calculationTime;
	}
	

	public CheckingAccountBean getLinkedAccount() {
		return linkedAccount;
	}
	public void setLinkedAccount(CheckingAccountBean linkedAccount) {
		this.linkedAccount = linkedAccount;
	}
	
	@Override
	public CreditCardAccount build(GenericFactory<CreditCardAccount> factory) {
		
		if (this.model == null)
			this.model = new CreditCardAccount();
		
		model.setCalculationTime(Integer.valueOf(calculationTime));
		model.setCardNumber(cardNumber);
		model.setDescription(description);
		try {
			model.setFlag(CreditCardFlag.valueOf(flag));
		} catch (Exception e) {
			// do nothing
		}
		
		model.setMaturityDay(Integer.valueOf(maturityDay));
		model.setStart(start);
		
		
		return model;
	}
	@Override
	public CreditCardAccount getModel() {
		return model;
	}
	
	@Override
	public CreditCardAccountBean load(CreditCardAccount model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		loadEntries(model);
		
		return this;
	}
	
	public CreditCardAccountBean load(CreditCardAccount model, boolean onlyLazy) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		this.calculationTime = String.valueOf(model.getCalculationTime());
		this.cardNumber = model.getCardNumber();
		this.description = model.getDescription();
		this.flag = (model.getFlag() != null ? model.getFlag().name() : "");
		this.maturityDay = String.valueOf(model.getMaturityDay());
		this.start = model.getStart();
		
		return this;
	}
	
	
	@Override
	public int compareTo(CreditCardAccountBean _bean) {
		return cardNumber.compareTo(_bean.cardNumber);
	}
	
}
