package com.aoks.register.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.register.control.bean.AddressBean;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.bean.datamodel.AddressDataModel;
import com.aoks.register.control.factory.AddressFactory;
import com.aoks.register.model.Address;
import com.aoks.register.model.IndividualRegister;
import com.aoks.register.service.AddressManager;
import com.aoks.register.service.IndividualRegisterManager;
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
@Named("individualAddressController")
@SessionScoped
public class IndividualAddressController extends AbstractController<Address, AddressBean> {

	private static final long serialVersionUID = 1L;

	@Inject private AddressManager manager;
	@Inject private IndividualRegisterManager registerManager;
	@Inject private AddressFactory factory;
	
	private AddressDataModel dataModel;
	private IndividualRegisterBean register;
	
	
	/**
	 * Load beans to be synchronized with list view
	 * 
	 * @param register
	 */
	public void loadBeans(IndividualRegisterBean register){
		this.register = register;
		dataModel = new AddressDataModel(this.register.getAddresses());
	}
	
	
	/**
	 * Add new address to register
	 * 
	 * @throws IllegalStateException
	 */
	public void addRegister() {
		doPreSave();
		
		Address address = bean.build(getFactory());
		IndividualRegister individualRegister = register.build(null);
		
		if (individualRegister == null) 
			throw new IllegalStateException();
		
		individualRegister.addAddress(address);
		
		//GAMBIARRA POR CAUSA DE PROBLEMAS NA DATATABLE
		Long nextId = manager.nextIdValid().longValue();
		if(!register.getAddresses().isEmpty()){
			for(AddressBean bean : register.getAddresses()){
				if(bean.getId() >= nextId){
					nextId = bean.getId() + 1;
				}
			}
		}
		bean.setId(nextId);
		
		register.addAddress(bean);

		doPostSave();
	}
	
	
	/**
	 * Remove address from register
	 * 
	 * @throws IllegalStateException
	 */
	@Override
	public void delete() {
		if (register == null) return;
		
		Address address = getFactory().create(bean);
		IndividualRegister individualRegister = register.build(null);
		
		if (individualRegister == null)
			throw new IllegalStateException();
		
		individualRegister.removeAddress(address);
		register.getAddresses().remove(bean);
		
		clean();
	}
	
	
	public IndividualRegisterBean getRegister() 			 	  		{ return register; 			}
	public void setRegister(IndividualRegisterBean register) 	  		{ this.register = register; }

	
	public AbstractManager<IndividualRegister> getRegisterManager() 	{ return registerManager;	}

	
	@Override public GenericDataModel<AddressBean> getDataModel() 		{ return dataModel; }
	@Override public AbstractManager<Address> getManager() 		  		{ return manager; 			}
	@Override public GenericFactory<Address> getFactory() 		  		{ return factory; 			}
	@Override public Class<AddressBean> getBeanClazz() 			  		{ return AddressBean.class; }

	
	/**
	 * Select address bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event)	{ bean = (AddressBean) event.getObject();  }  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) { clean(); }


    
}
