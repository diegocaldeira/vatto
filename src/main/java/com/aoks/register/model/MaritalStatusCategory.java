package com.aoks.register.model;

/**
 * Defines values for marital status.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
public enum MaritalStatusCategory {

    /**
     *
     */
	NONE("general_empty"),
	
	/**
    *
    */
    SINGLE("model_marital_status_single"),
    
    /**
     *
     */
    MARRIED("model_marital_status_married"),
    
    /**
     *
     */
    DIVORCED("model_marital_status_divorced"),
    
    /**
     *
     */
    WIDOWER("model_marital_status_widower");
	
	private String maritalStatus;
	
	MaritalStatusCategory(String maritalStatus){ this.maritalStatus = maritalStatus; }
	
	public String getMaritalStatus() 					{ return maritalStatus; }
	public void setMaritalStatus(String maritalStatus)	{ this.maritalStatus = maritalStatus; }
}
