package com.aoks.enterprise.model.entities;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public enum AreaActuationType {
	
	TRADE("Comercio"), SERVICES("Servicos"), INDUSTRY("Industria"), RURAL("Rural"), OTHERS("Outros");
	
	String areaActuation;
	
	AreaActuationType(String areaActuation) {
		this.areaActuation = areaActuation;
	}
	
	public String getAreaActuation() { return areaActuation; }
	public void setAreaActuation(String areaActuation) { this.areaActuation = areaActuation; }
	
}
