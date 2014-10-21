package com.aoks.register.control.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aoks.register.model.Contact;
import com.aoks.register.model.Email;
import com.aoks.register.model.Phone;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class ContactBean implements GenericBean<Contact> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public Contact model;
	
	private String firstName;
	private String lastName;
	private String fullName;
	protected String facebook;
	protected String twitter;
	protected String cpf;
	protected String rg;
	private String contactType;  
	
//	private List<CategoryBean> categories;
	private List<PhoneBean> phones;
	private List<EmailBean> emails;
	
	private RegisterBean<?> register;
	
	public ContactBean() {
//		categories = new ArrayList<CategoryBean>();
		phones = new ArrayList<PhoneBean>();
		emails = new ArrayList<EmailBean>();
	}
	
	
	@Override
	public Contact build(GenericFactory<Contact> factory) {
		return factory.create(this);
	}
	
	@Override
	public Contact getModel() {
		return model;
	}
	@Override
	public ContactBean load(Contact model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		
		this.firstName = model.getFirstName();
		this.lastName = model.getLastName();
		this.contactType = model.getContactType();
		this.facebook = model.getFacebook();
		this.twitter = model.getTwitter();
		
		Set<? extends Email> ems = model.getEmails();
		for (Email email : ems) {
			this.addEmail(new EmailBean().load(email));
		}
		
		Set<? extends Phone> phs = model.getPhones();
		for (Phone phone : phs) {
			this.addPhone(new PhoneBean().load(phone));
		}
		
//		Set<Category> cats = model.getCategorizedHelper().getCategories();
//		for (Category category : cats) {
//			this.addCategory(new CategoryBean().load(category));
//		}
//		
		return this;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFullName(String firstName, String lastName){
		this.fullName = firstName + " " + lastName; 
	}
	public String getFullName(){
		return fullName;
	}

	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
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
	
	
	public String getCpf() {
		return register.getRegistration("cpf").getValue();
	}
	public void setCpf(String cpf) {
		register.getRegistration("cpf").setValue(cpf);
		this.cpf = cpf;
	}
	
	
	public String getRg() {
		return register.getRegistration("rg").getValue();
	}
	public void setRg(String rg) {
		register.getRegistration("rg").setValue(rg);
		this.rg = rg;
	}
	
	
	public void addPhone(PhoneBean phone){
		phones.add(phone);
	}
	public List<PhoneBean> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneBean> phones) {
		this.phones = phones;
	}
	public PhoneBean getFirstPhone(){
		if (phones.size() == 0)
			phones.add(new PhoneBean());
		return phones.get(0);
	}
	
	
	public void addEmail(EmailBean email){
		emails.add(email);
	}
	public List<EmailBean> getEmails() {
		return emails;
	}
	public void setEmails(List<EmailBean> emails) {
		this.emails = emails;
	}
	public EmailBean getFirstEmail(){
		if (emails.size() == 0)
			emails.add(new EmailBean());
		return emails.get(0);
	}
	
	
	public RegisterBean<?> getRegister() {
		return register;
	}
	public void setRegister(RegisterBean<?> register) {
		this.register = register;
	}
	
	
	
//	public void addCategory(CategoryBean category){
//		categories.add(category);
//	}
//	public List<CategoryBean> getCategories() {
//		return categories;
//	}
//	public void setCategories(List<CategoryBean> categories) {
//		this.categories = categories;
//	}
//	public String getCategoriesResume(){
//		String ret = "";
//		for (CategoryBean cat : categories) {
//			ret = ret + cat.getName() + ", ";
//		}
//		if (ret.length() > 2)
//			ret = ret.substring(0, ret.length()-2);
//		return ret;
//	}
	
	
	@Override
	public long getId()			{ return id; }
	public void setId(long id)	{ this.id = id; }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof ContactBean)) return false;
		ContactBean that = (ContactBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if ((this.firstName == null ? that.firstName == null : this.firstName.equals(that.firstName)) &&
				(this.lastName == null ? that.lastName == null : this.lastName.equals(that.lastName)))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else {
			result = result + 31 * (firstName != null ? firstName.hashCode() : 0);
			result = result + 31 * (lastName != null ? lastName.hashCode() : 0);
		}
		return result;
	}
	
	
}
