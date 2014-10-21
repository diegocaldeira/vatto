package com.aoks.register.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.aoks.enterprise.model.entities.AreaActuationType;

/**
 * Represents a legal register.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "LegalRegister", schema="register")
public class LegalRegister extends Register {

	private static final long serialVersionUID = 1L;


	@Enumerated(EnumType.STRING)
	private AreaActuationType areaActuation;
	
    @Column(name = "cCompanyName")
    private String companyName;

    @Column(name = "cFancyName")
    private String fancyName;
    
    
    public AreaActuationType getAreaActuation() {
		return areaActuation;
	}
	public void setAreaActuation(AreaActuationType areaActuation) {
		this.areaActuation = areaActuation;
	}
	

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    

    public String getFancyName() {
        return fancyName;
    }
    public void setFancyName(String fancyName) {
        this.fancyName = fancyName;
    }
    

    @Override
    public String toString() {
        return "";
    }
    

}
