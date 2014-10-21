package com.aoks.register.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents a type of registration.
 * Ex: type = 'cnpj' and value='1234567890' 
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Embeddable
public class Registration {

	@Column(name="cKey")	private String type;
	@Column(name="cValue")	private String value;
	
	
	public Registration() {}
	
	public Registration(String type, String value){
		this.type = type;
		this.value = value;
	}

	
	public String getType()				{ return type; }
	public void setType(String type)	{ this.type = type; }
	
	public String getValue() 			{ return value; }
	public void setValue(String value)	{ this.value = value; }
	
}
