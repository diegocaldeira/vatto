package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * Represents a organization department.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 */
@Entity
@Table(name = "Department", schema="enterprise")
public class Department extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;

	public Department() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.DEPARTMENT);
			behavior.setEntity(this);
		}
			
	}
	
}
