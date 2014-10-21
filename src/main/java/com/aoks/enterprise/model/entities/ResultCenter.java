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
@Table(name = "ResultCenter", schema="enterprise")
public class ResultCenter extends EnterpriseEntity{

	private static final long serialVersionUID = 1L;

	public ResultCenter() {
		if (behavior == null){
			behavior = new PartyBehavior(EnterpriseEntityType.RESULTCENTER);
			behavior.setEntity(this);
		}
	}
	
}
