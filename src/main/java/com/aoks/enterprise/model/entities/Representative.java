package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * Represents a sale representantive
 *  
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 	
 */
@Entity
@Table(name = "Representative", schema="enterprise")
public class Representative extends EnterpriseEntity{

	private static final long serialVersionUID = 1L;

	public Representative() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.REPRESENTATIVE);
			behavior.setEntity(this);
		}
			
	}
	
}
