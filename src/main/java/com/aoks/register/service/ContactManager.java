package com.aoks.register.service;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.register.model.Contact;
import com.aoks.utils.category.service.CategoryManager;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class ContactManager extends AbstractManager<Contact> {

	private static final long serialVersionUID = 1L;

	@Inject private CategoryManager categoryManager;	
	@Inject private EmailManager emailManager;
	@Inject private PhoneManager phoneManager;
	

	public BigInteger nextIdValid(){
		BigInteger id = (BigInteger) wrapper.get("register.registersequence", "ctable", "ContactSequence", "cnext");
		
		if(id == null){
			id = new BigInteger("1");
		}
		
		return id;
	}
	
	
	@Override
	public Class<Contact> getModelClazz() {
		return Contact.class;
	}
	
}
