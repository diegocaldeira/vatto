package com.aoks.enterprise.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.LegalOrganizationBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="LegalPartner", schema="enterprise")
public class LegalPartner extends Partner {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name="cPartnerType")
	private PartnerType type;
	
	
	public PartnerType getType() {
		return type;
	}
	public void setType(PartnerType type) {
		this.type = type;
	}
	
	
	public LegalPartner() {
		if (behavior == null){
			behavior = new LegalOrganizationBehavior(EnterpriseEntityType.LEGALPARTNER);
			behavior.setEntity(this);
		}
			
	}
	
}
