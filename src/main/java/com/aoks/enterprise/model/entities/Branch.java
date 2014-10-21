package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.LegalOrganizationBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "Branch", schema="enterprise")
public class Branch extends EnterpriseEntity{
	
	private static final long serialVersionUID = 1L;

	public Branch() {
		if (behavior == null){
			behavior = new LegalOrganizationBehavior(EnterpriseEntityType.BRANCH);
			behavior.setEntity(this);
		}
			
	}

}
