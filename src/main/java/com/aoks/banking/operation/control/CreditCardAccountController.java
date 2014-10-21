package com.aoks.banking.operation.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.banking.bank.control.bean.datamodel.CreditCardAccountDataModel;
import com.aoks.banking.operation.control.bean.CreditCardAccountBean;
import com.aoks.banking.operation.model.CreditCardAccount;
import com.aoks.banking.operation.service.CreditCardAccountManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.RegisterController;


@Named("creditCardAccountController")
@SessionScoped
public class CreditCardAccountController extends AbstractController<CreditCardAccount, CreditCardAccountBean> {

	private static final long serialVersionUID = 1L;

	@Inject private CreditCardAccountManager manager;
	
	@Inject private RegisterController registerController;
	
	private CreditCardAccountDataModel dataModel;
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<CreditCardAccountBean> listBeans = new ArrayList<CreditCardAccountBean>();
		List<CreditCardAccount> entities = manager.list();
		
		for(CreditCardAccount entity : entities){
			listBeans.add(new CreditCardAccountBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new CreditCardAccountDataModel(listBeans);
	}

	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra") || application.getName().equals("budget"))
			beans = updateBeans();
	}
	
	
	public RegisterController getRegisterController() 						{ return registerController; }

	@Override public Class<CreditCardAccountBean> getBeanClazz() 			{ return CreditCardAccountBean.class; }
	@Override public GenericFactory<CreditCardAccount> getFactory() 		{ return null; }
	@Override public AbstractManager<CreditCardAccount> getManager() 		{ return manager; }
	@Override public GenericDataModel<CreditCardAccountBean> getDataModel() { return dataModel; }

	
	/**
	 * Select credit card account bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (CreditCardAccountBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}
    
}
