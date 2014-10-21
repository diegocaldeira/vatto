package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "Party", schema="enterprise")
public class Party extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;
	
	public Party() {
		if (behavior == null){
			behavior = new PartyBehavior();
			behavior.setEntity(this);
		}
			
	}
	
}