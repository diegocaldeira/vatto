package com.aoks.utils.webmvc;

import java.io.Serializable;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public abstract class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public void load(String name){
		this.name = name;
	}
	
	public boolean isLoaded(String name){
		return (this.name == null ? false : (name == null ? false : this.name.equals(name)));
	}
	
	
	public boolean isNull()	{ return (name == null); }
	public String getName() { return this.name; }
	
}