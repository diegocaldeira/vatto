package com.aoks.utils.webmvc;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("registerController")
@SessionScoped
public class RegisterController implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name = "DASHBOARD";
	
	private Boolean create;
	private Boolean view;
	
	public Boolean isLoaded(String name) { return (this.name == null ? false : (name == null ? false : this.name.equals(name))); }
	public void load(String name) { this.name = name; }

	
	public String getName() { 
		switch(name){
			case "DASHBOARD": name = "DASHBOARD"; break;
			case "BANKING-WRAPPER": name = "BANK-UNIT"; break;
			case "BUDGET-WRAPPER": name = "EXPENSE"; break;
			case "CONTACT": name = "CONTACT-DASHBOARD"; break;
		}
		return name; 
	}
	public void setName(String name) { this.name = name; }
	
	public Boolean getCreate() { return create; }
	public void setCreate(Boolean create) { this.create = create; }
	
	public Boolean getView() { return view; }
	public void setView(Boolean view) { this.view = view; }
	
}
