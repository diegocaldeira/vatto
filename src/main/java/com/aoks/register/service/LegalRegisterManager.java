package com.aoks.register.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class LegalRegisterManager extends AbstractManager<LegalRegister> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<LegalRegister> getModelClazz() {
		return LegalRegister.class;
	}

}
