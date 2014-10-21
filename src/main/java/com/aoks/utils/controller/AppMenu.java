package com.aoks.utils.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("appMenu")
@SessionScoped
public class AppMenu implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name = "DASHBOARD";
	
	private Boolean create;
	private Boolean view;
	
	public Boolean isLoaded(String name) { 
		return (this.getName() == null ? false : (name == null ? false : this.getName().equals(name))); 
	}
	public void load(String name) { this.name = name; }


	public String getName() 				{  return name;  }
	public void setName(String name) 		{  this.name = name; }
	
	public Boolean getCreate() 				{ return create; }
	public void setCreate(Boolean create) 	{ this.create = create; }
	
	public Boolean getView() 				{ return view; }
	public void setView(Boolean view) 		{ this.view = view; }
	
	
}
