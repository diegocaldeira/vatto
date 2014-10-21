package com.aoks.register.control.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aoks.register.model.Register;
import com.aoks.utils.webmvc.GenericBean;

/**
 * 
 * @author Diego Pereira
 *
 * @param <T>
 */
public abstract class RegisterBean<T extends Register> implements GenericBean<T>{

	protected static final long serialVersionUID = 1L;

	protected long id;
	
	protected List<AddressBean> addresses;
	protected List<ContactBean> contacts;
	protected Map<String, RegistrationBean> registrations;
	protected String site;
	protected PhoneBean phone;
	protected String facebook;
	protected String twitter;
	
	
	public RegisterBean() {
		addresses = new ArrayList<AddressBean>();
		contacts = new ArrayList<ContactBean>();
		registrations = new HashMap<String, RegistrationBean>();
		phone = new PhoneBean();
	}
	
	public RegistrationBean getRegistration(String type){
		
		if (registrations.containsKey(type))
			return registrations.get(type);
		
		RegistrationBean ret = new RegistrationBean();
		registrations.put(type, ret);
		ret.setKey(type);
		
		return ret;
		
	}
	
	public void addRegistration(String key, String value){
		registrations.put(key, new RegistrationBean(key, value));
	}
	public Set<RegistrationBean> getRegistrations() {
		return new HashSet<RegistrationBean>(registrations.values());
	}
	
	
	public List<AddressBean> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressBean> addresses) {
		this.addresses = addresses;
	}
	public void addAddress(AddressBean address){
		if (addresses.contains(address))
			addresses.remove(address);
		addresses.add(address);
	}
	
	
	public List<ContactBean> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactBean> contacts) {
		this.contacts = contacts;
	}
	public void addContact(ContactBean contact){
		if (contacts.contains(contact))
			contacts.remove(contact);
		contacts.add(contact);
	}
	
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	

	public PhoneBean getPhone() {
		return phone;
	}
	public void setPhone(PhoneBean phone) {
		this.phone = phone;
	}
	

	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	

	@Override
	public long getId() {
		return id;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof RegisterBean)) return false;
		RegisterBean<?> that = (RegisterBean<?>) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		return false;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + (int)(id^(id>>>32));
		return result;
	}
	
}
