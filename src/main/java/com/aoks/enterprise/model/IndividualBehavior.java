package com.aoks.enterprise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aoks.register.model.IndividualRegister;
import com.aoks.register.model.Register;

/**
 * Represents the enterprise behavior of an individual within the enterprise context
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@DiscriminatorValue("individualBehavior")
public class IndividualBehavior extends EnterpriseBehavior {

	private static final long serialVersionUID = 1L;
	
	/**
     * An embedded object to record the register that this specific behavior had
     * throughout time.
     */

    public IndividualBehavior() {
    	if (register == null) register = new IndividualRegister();
    }

    public IndividualBehavior(EnterpriseEntityType type) {
        this.type = type;
    }

    // getters and setters
    @Override
    public Register getRegister() {
    	return register;
    }
    @Override
    public void setRegister(Register register) {
    	this.register = (IndividualRegister) register;
    }
}