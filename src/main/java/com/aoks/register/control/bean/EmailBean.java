package com.aoks.register.control.bean;

import com.aoks.register.model.Email;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class EmailBean implements GenericBean<Email> {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String email;
	private Email model;
	
	
	public EmailBean() {}
	public EmailBean(Email model) {
		this.model = model;
	}
	
	
	public Email getModel() {
		return model;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	
	
	@Override
	public Email build(GenericFactory<Email> factory) {
		
		if (model == null)
			model = new Email();
		
		model.setEmail(email);
		
		return model;
	}
	@Override
	public EmailBean load(Email model) {
		
		if (model == null)
			throw new IllegalStateException();

		this.model = model;
		
		this.id = model.getId();
		this.email = model.getEmail();
		
		return this;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof EmailBean)) return false;
		EmailBean that = (EmailBean) obj;
		if (this.id > 0 && this.id == that.id)
    		return true;
    	else if (this.email == null ? that.email == null : this.email.equals(that.email))
    		return true;
    	return false;
    }
    
    @Override
    public int hashCode() {
    	int result = 17;
    	if (id > 0)
    		result = result * 31 + (int)(id^(id>>>32));
    	else 
    		result = result * 31 + (email != null ? email.hashCode() : 0);
    	return result;
    }

}
