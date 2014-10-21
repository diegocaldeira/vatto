package com.aoks.utils.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("tabMenuController")
@SessionScoped
public class TabMenu implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name = "SIMPLES";
	
	public Boolean isLoaded(String name) {
		return (this.name == null ? false : (name == null ? false : this.name.equals(name)));
	}
	public void load(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
