package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.PartyBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="CompanyGroup", schema="enterprise")
public class CompanyGroup extends EnterpriseEntity {

	private static final long serialVersionUID = 1L;

	public CompanyGroup() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.COMPANYGROUP);
			behavior.setEntity(this);
		}
	}
	
}
