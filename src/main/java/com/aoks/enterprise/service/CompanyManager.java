package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Company;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class CompanyManager extends AbstractManager<Company> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Company> getModelClazz() {
		return Company.class;
	}

}
