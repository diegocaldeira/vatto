package com.aoks.security.control;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.security.control.bean.CredentialsBean;
import com.aoks.security.model.User;
import com.aoks.security.service.UserManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class LoginModule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserManager manager;
	
	public User authenticate(CredentialsBean credentials){

		String username = credentials.getUsername();
		
		User tmpuser = manager.getUserFromUsername(username);
		
		if (tmpuser == null)
			return null;
		
		String password = tmpuser.getLoginInfo().getPassword();
		String hexString = new EncrypterUtils().digest("sha1", credentials.getPassword()).getHexString();
		
		if (!password.equals(hexString))
			return null;
		
		return tmpuser;
		
	}
	
}
