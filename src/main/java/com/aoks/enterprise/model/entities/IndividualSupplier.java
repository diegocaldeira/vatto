package com.aoks.enterprise.model.entities;

import javax.persistence.Entity;
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
@Table(name="IndividualSupplier", schema="enterprise")
public class IndividualSupplier extends Supplier {

	private static final long serialVersionUID = 1L;
	
	public IndividualSupplier() {
		if (behavior == null){
			behavior = new IndividualBehavior(EnterpriseEntityType.INDIVIDUALSUPPLIER);
			behavior.setEntity(this);
		}
			
	}

}
