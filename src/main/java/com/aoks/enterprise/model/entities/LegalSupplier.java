package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
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
@Table(name="LegalSupplier", schema="enterprise")
public class LegalSupplier extends Supplier {

	private static final long serialVersionUID = 1L;
	
	public LegalSupplier() {
		if (behavior == null){
			behavior = new LegalOrganizationBehavior(EnterpriseEntityType.LEGALSUPPLIER);
			behavior.setEntity(this);
		}
			
	}

}
