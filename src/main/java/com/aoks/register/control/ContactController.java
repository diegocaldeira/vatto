package com.aoks.register.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.register.control.bean.ContactBean;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.datamodel.ContactDataModel;
import com.aoks.register.control.factory.ContactFactory;
import com.aoks.register.control.factory.LegalRegisterFactory;
import com.aoks.register.model.Contact;
import com.aoks.register.model.LegalRegister;
import com.aoks.register.service.ContactManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * Performs operations in the interface layer .
 * 
 * @author Diego Pereira
 * 
 */
@Named("contactController")
@SessionScoped
public class ContactController extends AbstractController<Contact, ContactBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private ContactManager manager;
	@Inject private ContactFactory factory;
	@Inject private LegalRegisterFactory registerFactory;
	
	private ContactDataModel dataModel;
	private LegalRegisterBean register;
	
	
	/**
	 * Load beans to be synchronized with list view
	 * 
	 * @param contacts
	 */
	public void loadBeans(LegalRegisterBean register){
		this.register = register;
		dataModel = new ContactDataModel(this.register.getContacts());
	}
	
	
	/**
	 * Add new contact to register
	 * 
	 * @throws IllegalStateException
	 */
	public void addContact() {
		doPreSave();
		
		Contact contact = getFactory().create(bean);
		LegalRegister legalRegister = (LegalRegister) registerFactory.create(register);
		
		if (legalRegister == null) 
			throw new IllegalStateException();
		
		legalRegister.addContact(contact); 

		//GAMBIARRA POR CAUSA PRECISA DE ID NO DATATABLE 
		Long nextId = manager.nextIdValid().longValue();
		if(!register.getContacts().isEmpty()){
			for(ContactBean bean : register.getContacts()){
				if(bean.getId() >= nextId){
					nextId = bean.getId() + 1;
				}
			}
		}
		bean.setId(nextId);
		
		contact.setRegister(legalRegister);
		register.addContact(bean);
		
		doPostSave();
	}
	
	
	/**
	 * Remove contact from register
	 * 
	 * @throws IllegalStateException
	 */
	@Override
	public void delete() {
		if (register == null) return;
		
		Contact contact = getFactory().create(bean);
		LegalRegister legalRegister = (LegalRegister) register.build(registerFactory);
		
		if (legalRegister == null) 
			throw new IllegalStateException();
		
		legalRegister.removeContact(contact);
		register.getContacts().remove(bean);

		clean();
	}
	
	
	public void create(){
		try {
			bean = new ContactBean();
			bean.setRegister(register);
			beans.add(bean);
		} catch (Exception e) {}
	}

	
	public LegalRegisterBean getRegister() 							{ return register; }
	public void setRegister(LegalRegisterBean register) 			{ this.register = register; }

	@Override public GenericDataModel<ContactBean> getDataModel() 	{ return dataModel; }
	@Override public AbstractManager<Contact> getManager() 			{ return manager; }
	@Override public GenericFactory<Contact> getFactory() 			{ return factory; }
	@Override public Class<ContactBean> getBeanClazz() 				{ return ContactBean.class; }
	
	
	/**
	 * Select contact bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) 	   { bean = (ContactBean) event.getObject(); }  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) { clean(); }


}
