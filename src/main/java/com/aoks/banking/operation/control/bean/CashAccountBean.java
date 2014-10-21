package com.aoks.banking.operation.control.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import com.aoks.banking.operation.model.CashAccount;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CashAccountBean extends OperationAccountBean<CashAccount> implements Comparable<CashAccountBean>{

	private static final long serialVersionUID = 1L;

	protected CashAccount model;
	protected Calendar start;
	protected BigDecimal startBalance;
	
	public Calendar getStart() {
		return start;
	}
	public void setStart(Calendar start) {
		this.start = start;
	}
	
	
	public BigDecimal getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}
	
	
	@Override
	public CashAccount build(GenericFactory<CashAccount> factory) {
		
		if (this.model == null)
			this.model = new CashAccount();
		
		model.setDescription(description);
		model.setStart(start);
		model.setStartBalance(startBalance);
		
		return model;
	}
	@Override
	public CashAccount getModel() {
		return model;
	}
	
	
	@Override
	public CashAccountBean load(CashAccount model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		loadEntries(model);
		
		return this;
		
	}
	public CashAccountBean load(CashAccount model, boolean onlyLazy) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		this.start = model.getStart();
		this.description = model.getDescription();
		this.startBalance = model.getStartBalance();
		
		return this;
	}
	
	@Override
	public int compareTo(CashAccountBean _bean){
		return this.description.compareTo(_bean.getDescription());
	}
	
}
