package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * Represents a sector
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "Sector", schema="enterprise")
public class Sector extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;

	public Sector() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.SECTOR);
			behavior.setEntity(this);
		}
			
	}
	
}
