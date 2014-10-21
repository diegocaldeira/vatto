package com.aoks.enterprise.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.LegalOrganizationBehavior;
import com.aoks.enterprise.model.MarketSegment;

/**
 * Representes a costumer
 * 
 * @author Diego Pereira
 * @site www.diegopeerira.com.br
 * 
 */
@Entity
@Table(name = "Costumer", schema="enterprise")
public class Costumer extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
     * The market segment for the costumer production
     */
    @ManyToOne
    @JoinColumn(name = "cSegmentId")
    private MarketSegment marketSegment;
    
    /**
     * The costumer website
     */
    @Column(name = "cWebsite")
    private String website;

    public Costumer() {
    	if (behavior == null){
    		behavior = new LegalOrganizationBehavior(EnterpriseEntityType.COSTUMER);
    		behavior.setEntity(this);
    	}
	}
    
    
    public MarketSegment getMarketSegment() {
        return marketSegment;
    }
    public void setMarketSegment(MarketSegment segment) {
        this.marketSegment = segment;
    }
    

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
}
