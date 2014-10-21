package com.aoks.budget.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@Named("budgetDashboardCTL")
@SessionScoped
public class DashboardController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DashboardModel model;
	
	public DashboardController() {

		model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
          
        column1.addWidget("resumeA");  
        column1.addWidget("resumeC");  
          
        column2.addWidget("resumeB");  
        column2.addWidget("resumeD");  
          
        model.addColumn(column1);  
        model.addColumn(column2);  
        
	}
	
	public DashboardModel getModel() {
		return model;
	}
	
	public void setModel(DashboardModel model) {
		this.model = model;
	}

	public void handleReorder(DashboardReorderEvent event) {  
    }  
	
}
