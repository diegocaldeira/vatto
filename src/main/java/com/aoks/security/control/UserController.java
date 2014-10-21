package com.aoks.security.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import sun.misc.IOUtils;

import com.aoks.security.control.bean.UserBean;
import com.aoks.security.model.User;
import com.aoks.security.service.UserManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("userController")
@SessionScoped
public class UserController extends AbstractController<User, UserBean> {

	private static final long serialVersionUID = 1L;

	@Inject private UserManager manager;
	@Inject protected PersistenceWrapper wrapper;
	@Inject protected SecureSessionController secureSession;
	
	
	/**
	 * Set logged user to bean
	 * 
	 * @param bean
	 */
	public void edit(UserBean bean){
		this.bean = bean;
	}
	
	/**
	 * Load picture to user profile
	 * 
	 * @param event
	 */
	public void storeUserPicture(FileUploadEvent event){
		byte[] content = event.getFile().getContents();
		bean.setPicture(content);
	}
	
	@Override
	public void create() {
		super.create();

	    try {
	    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/img/blue-user-icon.png");
	    	bean.setPicture(IOUtils.readFully(stream, -1, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void save() {
		manager.load(getAsModel(bean));
		
		User model = manager.getModel();
		
		if(manager.getModel().getAuditEntity().getId() != 0L)
			manager.getModel().getAuditEntity().getModifyDates().add(GregorianCalendar.getInstance());
		
		if(secureSession.isLogged())
			manager.getModel().getAuditEntity().getUsers().add(secureSession.getLoggedBean().getModel());
		else
			manager.getModel().getAuditEntity().setUser(model);
		
		wrapper.saveOrUpdate(manager.getModel());
	}

	@Override public Class<UserBean> getBeanClazz() 			{ return UserBean.class; }
	@Override public GenericFactory<User> getFactory() 			{ return null; }
	@Override public AbstractManager<User> getManager() 		{ return manager; }
	@Override public GenericDataModel<UserBean> getDataModel()	{ return null; }
	
}
