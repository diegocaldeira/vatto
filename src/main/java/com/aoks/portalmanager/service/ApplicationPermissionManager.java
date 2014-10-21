package com.aoks.portalmanager.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.portalmanager.model.security.ApplicationPermission;
import com.aoks.utils.webmvc.AbstractManager;


@RequestScoped
public class ApplicationPermissionManager extends AbstractManager<ApplicationPermission> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<ApplicationPermission> getModelClazz() {
		return ApplicationPermission.class;
	}

}
