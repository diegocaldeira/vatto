package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.IndividualSupplier;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class IndividualSupplierManager extends AbstractManager<IndividualSupplier> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<IndividualSupplier> getModelClazz() {
		return IndividualSupplier.class;
	}

}
