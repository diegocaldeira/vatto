package com.aoks.enterprise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aoks.register.model.LegalRegister;
import com.aoks.register.model.Register;

/**
 * Represents and organizational behavior within an enterprise context.
 *  
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@DiscriminatorValue("legalBehavior")
public class LegalOrganizationBehavior extends EnterpriseBehavior{

	private static final long serialVersionUID = 1L;
	
    public LegalOrganizationBehavior() {
    	register = new LegalRegister();
    }

    public LegalOrganizationBehavior(EnterpriseEntityType type) {
        this.type = type;
    }

    
    @Override
    public Register getRegister() {
    	return register;
    }
    @Override
    public void setRegister(Register register) {
    	this.register = (LegalRegister) register;
    }
    
}
