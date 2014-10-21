package com.aoks.register.control.bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.aoks.register.model.Address;
import com.aoks.register.model.Contact;
import com.aoks.register.model.GenreCategory;
import com.aoks.register.model.IndividualRegister;
import com.aoks.register.model.MaritalStatusCategory;
import com.aoks.register.model.Registration;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class IndividualRegisterBean extends RegisterBean<IndividualRegister> implements Comparable<IndividualRegisterBean>{

	private static final long serialVersionUID = 1L;
	
	private IndividualRegister model;
	
	private Calendar birth;
	private String name;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String maritalStatus;
	private String nationality;
	private String naturality;
	private String genre;
	private String email;
	protected String cpf;
	protected String rg;
	
	
	public Calendar getBirth() 							{ return birth; }
	public void setBirth(Calendar birth) 				{ this.birth = birth; }
	
	
	public String getFatherName() 						{ return fatherName; }
	public void setFatherName(String fatherName) 		{ this.fatherName = fatherName; }
	
	
	public String getName() 							{ return name; }
	public void setName(String name) 					{ this.name = name; }

	
	public String getFirstName() 						{ return firstName; }
	public void setFirstName(String firstName) 			{ this.firstName = firstName;	}
	
	
	public String getLastName() 						{ return lastName; }
	public void setLastName(String lastName) 			{ this.lastName = lastName; }
	
	
	public String getMaritalStatus() 					{ return maritalStatus; }
	public void setMaritalStatus(String maritalStatus)  { this.maritalStatus = maritalStatus; }
	
	
	public String getMiddleName() 						{ return middleName; }
	public void setMiddleName(String middleName) 		{ this.middleName = middleName; }
	
	
	public String getMotherName() 						{ return motherName; }
	public void setMotherName(String motherName) 		{ this.motherName = motherName; }
	
	
	public String getNationality() 						{ return nationality; }
	public void setNationality(String nationality)  	{ this.nationality = nationality; }
	
	
	public String getNaturality() 						{ return naturality; }
	public void setNaturality(String naturality) 		{ this.naturality = naturality; }
	
	
	public String getGenre() 							{ return genre; }
	public void setGenre(String genre)					{ this.genre = genre;	}
	
	
	public String getEmail() 							{ return email; }
	public void setEmail(String email)  				{ this.email = email; }
	
	
	public String getCpf() { return getRegistration("cpf").getValue(); }
	public void setCpf(String cpf) {
		getRegistration("cpf").setValue(cpf);
		this.cpf = cpf;
	}
	
	
	public String getRg() {	return getRegistration("rg").getValue(); }
	public void setRg(String rg) {
		getRegistration("rg").setValue(rg);
		this.rg = rg;
	}
	
	
	
	@Override
	public IndividualRegister build(GenericFactory<IndividualRegister> factory) {
		
		if (model == null)
			model = new IndividualRegister();
		
		model.setBirth(birth);
		
		String fullName = (firstName != null) ? firstName : "";
		fullName += (middleName != null) ? " " + middleName : "";
		fullName += (lastName != null) ? " " + lastName : "";
		
		if(name != null)
			model.setName(name);
		else
			model.setName(fullName);
		
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setMiddleName(middleName);
		model.setFatherName(fatherName);
		model.setMotherName(motherName);
		model.setNationality(nationality);
		model.setNaturality(naturality);
		model.setPhone(phone.build(null));
		model.setEmail(email);
		model.setFacebook(facebook);
		model.setTwitter(twitter);
		model.setSite(site);

		if(genre != null){
			List<GenreCategory> genreCategories = Arrays.asList(GenreCategory.values());
			for(GenreCategory genreCategory : genreCategories){
				if(genreCategory.name().equals(genre)){
					model.setGenreCategory(genreCategory);
				}
			}
		}else{
			model.setGenreCategory(GenreCategory.NONE);
		}
		
		if(maritalStatus != null){
			List<MaritalStatusCategory> mscList = Arrays.asList(MaritalStatusCategory.values());
			for(MaritalStatusCategory msc : mscList){
				if(msc.name().equals(maritalStatus)){
					model.setMaritalStatus(MaritalStatusCategory.valueOf(maritalStatus));
				}
			}
		}else{
			model.setMaritalStatus(MaritalStatusCategory.NONE);
		}
		
		Set<RegistrationBean> registrations = getRegistrations();
		for (RegistrationBean registrationBean : registrations) {
			model.addRegistration(registrationBean.getKey(), registrationBean.getValue());
		}
		
		return model;
	}
	
	
	@Override public IndividualRegister getModel() 				{ return model; }
			  public void setModel(IndividualRegister model)	{ this.model = model; }
	
			
    @Override
	public IndividualRegisterBean load(IndividualRegister model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();

		List<Registration> regs = model.getRegistrations();
		for (Registration registration : regs) {
			registrations.put(registration.getType(), new RegistrationBean(registration.getType(), registration.getValue()));
		}
		
		List<? extends Address> ads = model.getAddresses();
		for (Address address : ads) {
			addresses.add(new AddressBean().load(address));
		}
		
		List<? extends Contact> conts = model.getContacts();
		for (Contact contact : conts) {
			contacts.add(new ContactBean().load(contact));
		}
		
		this.birth = model.getBirth();
		this.fatherName = model.getFatherName();
		this.name = model.getName();
		this.firstName = model.getFirstName();
		
		if (model.getGenreCategory() != null)
			this.genre = model.getGenreCategory().getGenre();
		
		this.lastName = model.getLastName();
		if (model.getMaritalStatus() != null)
			this.maritalStatus = model.getMaritalStatus().getMaritalStatus();
		
		this.middleName = model.getMiddleName();
		this.motherName = model.getMotherName();
		this.nationality = model.getNationality();
		this.naturality = model.getNaturality();
		this.phone = new PhoneBean().load(model.getPhone());
		this.email = model.getEmail();
		this.site = model.getSite();
		this.facebook = model.getFacebook();
		this.twitter = model.getTwitter();
		
		return this;
	}
    
    
	@Override
	public int compareTo(IndividualRegisterBean _bean) {
		return this.getName().compareTo(_bean.getName());
	}
	
}
