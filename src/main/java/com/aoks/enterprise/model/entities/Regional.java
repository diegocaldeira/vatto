package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * Represente a geographical based regional.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "Regional", schema="enterprise")
public class Regional extends EnterpriseEntity{

	private static final long serialVersionUID = 1L;

	public Regional() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.REGIONAL);
			behavior.setEntity(this);
		}
			
	}
	
}
