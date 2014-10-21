package com.aoks.register.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.register.model.IndividualRegister;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class IndividualRegisterManager extends AbstractManager<IndividualRegister> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<IndividualRegister> getModelClazz() {
		return IndividualRegister.class;
	}

}
