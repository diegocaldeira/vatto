package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Representative;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class RepresentativeManager extends AbstractManager<Representative> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Representative> getModelClazz() {
		return Representative.class;
	}

}
