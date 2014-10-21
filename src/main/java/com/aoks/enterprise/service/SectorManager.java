package com.aoks.enterprise.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.Sector;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class SectorManager extends AbstractManager<Sector> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Sector> getModelClazz() {
		return Sector.class;
	}

}
