package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.LegalSupplier;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class LegalSupplierManager extends AbstractManager<LegalSupplier> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<LegalSupplier> getModelClazz() {
		return LegalSupplier.class;
	}

}
