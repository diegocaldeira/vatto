package com.aoks.security.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.security.control.bean.CredentialsBean;
import com.aoks.security.control.bean.UserBean;
import com.aoks.security.model.User;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("sessionController")
@SessionScoped
public class SecureSessionController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User logged;
	private UserBean loggedBean;
	
	private CredentialsBean credentials = new CredentialsBean();

	@Inject private LoginModule loginModule;
	
	public boolean isLogged(){
		return logged != null;
	}
	
	public boolean isPrincipalAllowedTo(String principal, String action){
		return false;
	}
	
	
	public void attemptLogin() throws IOException{
		logged = loginModule.authenticate(credentials);
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		if(isLogged()){
			loggedBean = (UserBean) new UserBean().load(logged);
			ec.redirect(ec.getRequestContextPath() + "/");
		}else {
		    ResourceBundle bundle = context.getApplication().getResourceBundle(context, "message");
		    String loginError = bundle.getString("login_user_password_wrong");
		    String tryAgain = bundle.getString("general_please_try_again");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, loginError, tryAgain));
        }
	}
	
	
	public void attemptLogout(){
		logged = null;
		loggedBean = null;
		credentials = new CredentialsBean();
	}

	public CredentialsBean getCredentials() 				{ return credentials; }
	public void setCredentials(CredentialsBean credentials) { this.credentials = credentials; }
	
	public UserBean getLoggedBean() 				{ return loggedBean; }
	public void setLoggedBean(UserBean loggedBean)	{ this.loggedBean = loggedBean; }
	
}
