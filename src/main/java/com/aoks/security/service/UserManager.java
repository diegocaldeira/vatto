package com.aoks.security.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.security.model.User;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class UserManager extends AbstractManager<User> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<User> getModelClazz() {
		return User.class;
	}

	public User getUserFromUsername(String username){
		
		User user = wrapper.get(User.class, "loginInfo.username", username);
		return user;
		
	}

}
