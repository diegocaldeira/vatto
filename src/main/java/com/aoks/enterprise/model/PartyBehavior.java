package com.aoks.enterprise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aoks.register.model.Register;

/**
 * Represents the enterprise behavior of an entity that is not an individual not an
 * organization within the enterprise context.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@DiscriminatorValue("partyBehavior")
public class PartyBehavior extends EnterpriseBehavior{

	private static final long serialVersionUID = 1L;

	public PartyBehavior() {}

    public PartyBehavior(EnterpriseEntityType type) {
        this.type = type;
    }

	@Override
	public void setRegister(Register register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Register getRegister() {
		// TODO Auto-generated method stub
		return null;
	}
}
