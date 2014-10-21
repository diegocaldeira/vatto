package com.aoks.banking.operation.control.bean;

import java.util.ArrayList;
import java.util.List;

import com.aoks.banking.account.model.Entry;
import com.aoks.banking.operation.model.CashAccount;
import com.aoks.banking.operation.model.CheckingAccount;
import com.aoks.banking.operation.model.CreditCardAccount;
import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.banking.operation.model.OperationEntry;
import com.aoks.banking.operation.model.SavingsAccount;
import com.aoks.utils.webmvc.GenericBean;

/**
 * 
 * @author Diego Pereira
 *
 * @param <T>
 */
public abstract class OperationAccountBean<T extends OperationAccount> implements GenericBean<T> {

	private static final long serialVersionUID = 1L;

	protected long id;
	protected String description;
	protected List<OperationEntryBean> entries;
	
	public OperationAccountBean() {
		entries = new ArrayList<OperationEntryBean>();
	}
	
	
	@Override
	public long getId(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	
	public List<OperationEntryBean> getEntries(){
		return entries;
	}
	public void setEntries(List<OperationEntryBean> entries){
		this.entries = entries;
	}
	
	
	/**
	 * 
	 * @param model
	 */
	public void loadEntries(OperationAccount model){
		
		List<Entry> en = model.getEntries();
		for (Entry entry : en) {
			entries.add(new OperationEntryBean().load((OperationEntry) entry));
		}
		
	}
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public static OperationAccountBean<?> create(OperationAccount model){

		if (model instanceof CheckingAccount)
			return new CheckingAccountBean().load((CheckingAccount) model, true);
		else if (model instanceof CreditCardAccount)
			return new CreditCardAccountBean().load((CreditCardAccount) model, true);
		else if (model instanceof CashAccount)
			return new CashAccountBean().load((CashAccount) model, true);
		else if (model instanceof SavingsAccount)
			return new SavingsAccountBean().load((SavingsAccount) model, true);
		
		return null;
		
	}
	
	
}
