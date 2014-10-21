package com.dp.app.menu;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("budgetMenuController")
@SessionScoped
public class BudgetMenuController extends ApplicationMenuController implements Serializable{

	private static final long serialVersionUID = 1L;

	public BudgetMenuController(){
		super("BUDGET-WRAPPER", "EXPENSE");
	}
	
}
