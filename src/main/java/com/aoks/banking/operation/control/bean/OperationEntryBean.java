package com.aoks.banking.operation.control.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.banking.operation.model.OperationEntry;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class OperationEntryBean implements GenericBean<OperationEntry> {

	private static final long serialVersionUID = 1L;
	
	private OperationEntry model;
	private long id;
	private BigDecimal amount;
	private boolean credit;
	private Calendar ocurrence;
	private String description;
	private OperationAccountBean<?> account;

	
	public String getAccountDescription(){
		return (account != null ? account.getDescription() : "");
	}
	public OperationAccountBean<?> getAccount() {
		return account;
	}
	public void setAccount(OperationAccountBean<?> account) {
		this.account = account;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	
	
	public Calendar getOcurrence() {
		return ocurrence;
	}
	public void setOcurrence(Calendar ocurrence) {
		this.ocurrence = ocurrence;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	
	
	@Override
	public OperationEntry build(GenericFactory<OperationEntry> factory) {
		
		if (model == null)
			model = new OperationEntry();
		
		if (account != null)
			model.setAccount(account.getModel());
		
		model.setAmount(amount);
		
		if (credit) model.setCredit();
		
		model.setDescription(description);
		model.setOcurrence(ocurrence);
		
		return model;
	}
	@Override
	public OperationEntryBean load(OperationEntry model) {
		
		if (model == null)
			throw new IllegalStateException();

		OperationAccount ac = (OperationAccount) model.getAccount();
		if (ac != null)
			this.account = OperationAccountBean.create(ac);
		
		this.amount = model.getAmount();
		this.credit = model.isCredit();
		this.description = model.getDescription();
		this.id = model.getId();
		this.model = model;
		this.ocurrence = model.getOcurrence();
		
		return this;
	}

	
	@Override public OperationEntry getModel() { return model; }
}
