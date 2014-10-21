package com.aoks.register.control.bean;

import java.util.Arrays;
import java.util.List;

import com.aoks.enterprise.model.entities.AreaActuationType;
import com.aoks.register.model.Address;
import com.aoks.register.model.Contact;
import com.aoks.register.model.LegalRegister;
import com.aoks.register.model.Registration;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class LegalRegisterBean extends RegisterBean<LegalRegister> {

	private static final long serialVersionUID = 1L;
	
	private LegalRegister model;
	private String fancyName;
	private String companyName;
	private AreaActuationType areaActuation;
	protected String cnpj;
	protected String inscrEstadual;
	protected String inscrMunicipal;
	
	
	public String getFancyName() {
		return fancyName;
	}
	public void setFancyName(String fancyName) {
		this.fancyName = fancyName;
	}
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	public String getCnpj() {
		return getRegistration("cnpj").getValue();
	}
	public void setCnpj(String cnpj) {
		getRegistration("cnpj").setValue(cnpj);
		this.cnpj = cnpj;
	}
	
	
	public String getInscrEstadual() {
		return getRegistration("inscrest").getValue();
	}
	public void setInscrEstadual(String inscrEstadual) {
		getRegistration("inscrest").setValue(inscrEstadual);
		this.inscrEstadual = inscrEstadual;
	}
	
	
	public String getInscrMunicipal() {
		return getRegistration("inscrmun").getValue();
	}
	public void setInscrMunicipal(String inscrMunicipal) {
		getRegistration("inscrmun").setValue(inscrMunicipal);
		this.inscrMunicipal = inscrMunicipal;
	}
	
	
	public AreaActuationType getAreaActuation() {
		return areaActuation;
	}
	public void setAreaActuation(AreaActuationType areaActuation) {
		this.areaActuation = areaActuation;
	}
	
	
	public List<AreaActuationType> areaActuationList(){
		return Arrays.asList(AreaActuationType.values());
	}
	
	public LegalRegister getModel() {
		return model;
	}
	public void setModel(LegalRegister model) {
		this.model = model;
	}
	

	@Override
	public LegalRegister build(GenericFactory<LegalRegister> factory) {
		return factory.create(this);
	}

	@Override
	public GenericBean<LegalRegister> load(LegalRegister model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.companyName = model.getCompanyName();
		this.fancyName = model.getFancyName();
		this.site = model.getSite();
		this.phone = new PhoneBean().load(model.getPhone());
		this.areaActuation = model.getAreaActuation();
		this.facebook = model.getFacebook();
		this.twitter = model.getTwitter();
		
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
		
		return this;
	}
	
}
