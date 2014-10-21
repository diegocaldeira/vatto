package com.aoks.portalmanager.control.security;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.portalmanager.control.security.bean.ApplicationPermissionBean;
import com.aoks.portalmanager.model.security.ApplicationPermission;
import com.aoks.portalmanager.service.ApplicationPermissionManager;
import com.aoks.security.control.bean.UserBean;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;


@Named("permissionController")
@SessionScoped
public class PermissionController extends AbstractController<ApplicationPermission, ApplicationPermissionBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ApplicationPermissionManager manager;
	
	private UserBean user;
	
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public boolean hasUser(){
		return user != null;
	}
	
	
	@Override
	public AbstractManager<ApplicationPermission> getManager() {
		return manager;
	}
	@Override
	public Class<ApplicationPermissionBean> getBeanClazz() {
		return ApplicationPermissionBean.class;
	}
	@Override
	public GenericFactory<ApplicationPermission> getFactory() {
		return null;
	}
	@Override
	public GenericDataModel<ApplicationPermissionBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
