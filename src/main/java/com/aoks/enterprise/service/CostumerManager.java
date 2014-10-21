package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Costumer;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class CostumerManager extends AbstractManager<Costumer> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<Costumer> getModelClazz() {
		return Costumer.class;
	}

}
