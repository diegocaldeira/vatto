package com.aoks.banking.operation.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.banking.operation.control.bean.CheckingAccountBean;
import com.aoks.banking.operation.control.bean.CreditCardAccountBean;
import com.aoks.banking.operation.control.bean.OperationAccountBean;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private CheckingAccountController caController;
	@Inject private CreditCardAccountController ccController;

	private List<OperationAccountBean<?>> accounts;
	
	/**
	 * 
	 */
	private void loadAccounts(){
		caController.loadBeansByType(null);
		ccController.loadBeansByType(null);
		
		accounts = new ArrayList<OperationAccountBean<?>>();
		List<CheckingAccountBean> beans = caController.getDataModel().getFilteredBeans();
		for (CheckingAccountBean cab : beans) {
			accounts.add(cab);
		}
		
		List<CreditCardAccountBean> beans2 = ccController.getDataModel().getFilteredBeans();
		for (CreditCardAccountBean ccb : beans2) {
			accounts.add(ccb);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<OperationAccountBean<?>> getAccounts() {
		if (accounts == null)
			loadAccounts();
		return accounts;
	}
	
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<OperationAccountBean<?>> getAccountsAC(String query){
		if (accounts == null)
			loadAccounts();
		
		List<OperationAccountBean<?>> suggestions = new ArrayList<OperationAccountBean<?>>();  
        for(OperationAccountBean<?> b : accounts) {  
            if(b.getDescription().startsWith(query))  
                suggestions.add(b);
        }  
          
        return suggestions;  
	}
	
}
