package com.aoks.security.control.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.aoks.security.control.EncrypterUtils;
import com.aoks.security.model.AuditEntity;
import com.aoks.security.model.LoginInfo;
import com.aoks.security.model.PhoneInfo;
import com.aoks.security.model.User;
import com.aoks.utils.social.control.bean.SocialInfoBean;
import com.aoks.utils.social.model.SocialInfo;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class UserBean implements GenericBean<User>{
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private byte[] picture;
	
	private String firstName;
	private String lastName;
	private String profession;
	private boolean status;
	private PhoneInfo phone;
	private EmailInfoBean email;
	protected Map<String, SocialInfoBean> socials;
	
	private String username;
	private String password;
	
	private AuditEntity auditEntity;
	
	private User model;
	
	
	public UserBean(){
		socials = new HashMap<String, SocialInfoBean>();
	}
	
	
	public SocialInfoBean getSocial(String type){
		if(socials == null)
			socials = new HashMap<String, SocialInfoBean>();
			
		if (socials.containsKey(type))
			return socials.get(type);
		
		SocialInfoBean ret = new SocialInfoBean();
		socials.put(type, ret);
		ret.setKey(type);
		
		return ret;
		
	}
	
	public void addSocial(String key, String value){
		socials.put(key, new SocialInfoBean(key, value));
	}
	public Set<SocialInfoBean> getSocials() {
		return new HashSet<SocialInfoBean>(socials.values());
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	
	
	public StreamedContent getPicture() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            return new DefaultStreamedContent(new ByteArrayInputStream(picture));
        }
    }
	
	
	public void setPicture(byte[] picture) 		{ this.picture = picture; }

	public String getFirstName() 				{ return firstName; }
	public void setFirstName(String firstName) 	{ this.firstName = firstName; }
	
	public String getLastName() 				{ return lastName; }
	public void setLastName(String lastName) 	{	this.lastName = lastName; }
	
	public String getProfession() 				 { return profession; }
	public void setProfession(String profession) { this.profession = profession; }

	public String getUserDescription() {
		return getFirstName() + " " + getLastName() + " (" + getUsername() + ")";
	}
	public void setUserDescription(String userDescription) {
		// nothing
	}

	public boolean isStatus() 					{ return status; }
	public void setStatus(boolean status) 		{ this.status = status; }

	public PhoneInfo getPhone() 				{ return phone = (phone == null) ? new PhoneInfo() : phone; }
	public void setPhone(PhoneInfo phone) 		{ this.phone = phone; }

	public EmailInfoBean getEmail() 			{ return email = (email == null) ? new EmailInfoBean() : email; }
	public void setEmail(EmailInfoBean email)	{ this.email = email; }
	
	public String getUsername() 				{ return username; }
	public void setUsername(String username)	{ this.username = username; }
	
	public String getPassword() 				{ return password;}
	public void setPassword(String password)	{ this.password = password; }
	
	public String getFacebook()					{ return getSocial("facebook").getValue();  }
	public void setFacebook(String facebook) 	{ getSocial("facebook").setValue(facebook); }

	public String getTwitter() 					{ return getSocial("twitter").getValue();	}
	public void setTwitter(String twitter)		{ getSocial("twitter").setValue(twitter);	}

	public String getLinkedin() 				{ return getSocial("linkedin").getValue();	}
	public void setLinkedin(String linkedin)	{ getSocial("linkedin").setValue(linkedin); }


	@Override
	public User build(GenericFactory<User> factory) {
		if (model == null)
			model = new User();
		
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setStatus(status);
		model.setPicture(picture);
		
		for(SocialInfoBean socialBean : getSocials())
			model.addSocialInfo(socialBean.getKey(), socialBean.getValue());

		phone = getPhone();
		phone.setMain(Boolean.TRUE);
		
		if(phone.getPhoneNumber() != null)
			model.addPhoneInfo(phone);
		
		EmailInfoBean emailInfo = new EmailInfoBean();
		emailInfo.setAddress(email.getAddress());
		emailInfo.setMain(Boolean.TRUE);
		model.addEmailInfo(emailInfo.build(null));
		
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUsername(username);
		loginInfo.setPassword(new EncrypterUtils().digest("sha1", password).getHexString());
		
		if(auditEntity == null){
			auditEntity = new AuditEntity();
			auditEntity.setCreationDate(GregorianCalendar.getInstance());
		}
		
		model.setAuditEntity(auditEntity);
		model.setLoginInfo(loginInfo);
		
		return model;
	}
	
	@Override
	public GenericBean<User> load(User model) {

		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.picture = model.getPicture();
		this.firstName = model.getFirstName();
		this.lastName = model.getLastName();
		this.status = model.isStatus();
		this.email = (EmailInfoBean) new EmailInfoBean().load(model.getMainEmailInfo());
		this.username = model.getLoginInfo().getUsername();
		this.password = model.getLoginInfo().getPassword();
		this.phone = model.getMainPhoneInfo();
		this.auditEntity = model.getAuditEntity();
		
		for(SocialInfo socialInfo : model.getSocialInfos())
			this.addSocial(socialInfo.getType(), socialInfo.getValue());
		
		return this;
	}

	@Override 
	public User getModel() { 
		return model; 
	}
	
	public AuditEntity getAuditEntity() 				{ return auditEntity; }
	public void setAuditEntity(AuditEntity auditEntity) { this.auditEntity = auditEntity; }

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof UserBean)) return false;
		UserBean that = (UserBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if (this.username == null ? that.username == null : this.username.equals(that.username))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else 
			result = result * 31 + (username != null ? username.hashCode() : 0);
		return result;
	}
	
}
