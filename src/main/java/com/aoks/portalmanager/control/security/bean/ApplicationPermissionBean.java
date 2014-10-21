package com.aoks.portalmanager.control.security.bean;

import com.aoks.portalmanager.model.security.ApplicationPermission;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class ApplicationPermissionBean implements GenericBean<ApplicationPermission> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public ApplicationPermission build(GenericFactory<ApplicationPermission> factory) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ApplicationPermission getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GenericBean<ApplicationPermission> load(ApplicationPermission model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
