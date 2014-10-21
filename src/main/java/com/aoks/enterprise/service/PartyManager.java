package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Party;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class PartyManager extends AbstractManager<Party> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Party> getModelClazz() {
		return Party.class;
	}

}
