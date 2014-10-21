package com.aoks.banking.collection.control.bean;

import com.aoks.banking.collection.model.CollectionAccount;
import com.aoks.banking.operation.control.bean.CheckingAccountBean;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class CollectionAccountBean implements GenericBean<CollectionAccount> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String description;
	private CheckingAccountBean checkingAccount;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public CheckingAccountBean getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(CheckingAccountBean checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	

	@Override
	public long getId() {
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	@Override
	public CollectionAccount build(GenericFactory<CollectionAccount> factory) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CollectionAccount getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GenericBean<CollectionAccount> load(CollectionAccount model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
