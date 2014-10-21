package com.aoks.enterprise.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.IndividualBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="IndividualPartner", schema="enterprise")
public class IndividualPartner extends Partner{

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
	
	
	public IndividualPartner() {
		if (behavior == null){
			behavior = new IndividualBehavior(EnterpriseEntityType.INDIVIDUALPARTNER);
			behavior.setEntity(this);
		}
			
	}
	
}
