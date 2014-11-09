package com.dp.app.menu;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("dashboardMenuController")
@SessionScoped
public class DashboardMenuController extends ApplicationMenuController implements Serializable{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		loadModule("DASHBOARD-WRAPPER");
		load("DASHBOARD");
	}
}
