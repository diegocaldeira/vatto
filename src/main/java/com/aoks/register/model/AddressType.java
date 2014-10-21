package com.aoks.register.model;

/**
 * Defines values for a address category
 * .
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public enum AddressType {

	NONE("NONE"),
    RESIDENTIAL("RESIDENTIAL"),
    COMMERCIAL("COMMERCIAL");
	
	private String type;
	
	AddressType(String type){
		this.type = type;
	}

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

}
