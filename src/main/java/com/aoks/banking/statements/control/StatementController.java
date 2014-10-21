package com.aoks.banking.statements.control;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.aoks.banking.operation.control.bean.OperationAccountBean;
import com.aoks.banking.operation.control.bean.OperationEntryBean;


@Named("statementController")
@SessionScoped
public class StatementController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OperationAccountBean<?> account;

	private List<OperationEntryBean> beans;
	
	
	public OperationAccountBean<?> getAccount() {
		return account;
	}
	public void setAccount(OperationAccountBean<?> account) {
		this.account = account;
		if (this.account != null)
			beans = this.account.getEntries();
	}
	
	
	public List<OperationEntryBean> getBeans() {
		return beans;
	}
	public void setBeans(List<OperationEntryBean> beans) {
		this.beans = beans;
	}
	
	
	public boolean hasAccount(){
		return (account != null);
	}
	
}
