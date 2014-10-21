package com.aoks.dashboard.control;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.budget.service.FlowTransactionManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("dashboardController")
@SessionScoped
public class DashboardController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject private FlowTransactionManager transactionManager;

	public void init(){
		try {
			transactionManager.getTotalTransactionToday();
			transactionManager.getTotalTransactionMonthly();
			transactionManager.getTotalFutureTransactions();
			transactionManager.getTotalPercentageTransactions();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getExpensePercentage() { 
		return new DecimalFormat("##").format(new Double(transactionManager.getExpensePercentage())); 
	}
	public String getIncomePercentage()  { 
		return new DecimalFormat("##").format(new Double(transactionManager.getIncomePercentage()));
	}
	
	public Double getTotalDebitFuture()  { return transactionManager.getTotalDebitFuture();  }
	public Double getTotalCreditFuture() { return transactionManager.getTotalCreditFuture(); }
	
	public Double getTotalDebitMonthly()  { return transactionManager.getTotalDebitMonthly();  }
	public Double getTotalCreditMonthly() { return transactionManager.getTotalCreditMonthly(); }
	
	public Double getTotalDebitToday()  { return transactionManager.getTotalDebitToday();  }
	public Double getTotalCreditToday() { return transactionManager.getTotalCreditToday(); }
	
	public FlowTransactionManager getTransactionManager() { return transactionManager; }

}
