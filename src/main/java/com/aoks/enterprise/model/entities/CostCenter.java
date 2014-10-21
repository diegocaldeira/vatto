package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * Represents a cost center. 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 */
@Entity
@Table(name = "CostCenter", schema="enterprise")
public class CostCenter extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;

	public CostCenter() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.COSTCENTER);
			behavior.setEntity(this);
		}
			
	}
	
}
