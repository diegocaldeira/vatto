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

import com.aoks.banking.bank.control.bean.datamodel.CheckingAccountDataModel;
import com.aoks.banking.operation.control.bean.CheckingAccountBean;
import com.aoks.banking.operation.model.CheckingAccount;
import com.aoks.banking.operation.service.CheckingAccountManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.RegisterController;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("checkingAccountController")
@SessionScoped
public class CheckingAccountController extends AbstractController<CheckingAccount, CheckingAccountBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private CheckingAccountManager manager;
	@Inject private RegisterController registerController;

	private CheckingAccountDataModel dataModel;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<CheckingAccountBean> listBeans = new ArrayList<CheckingAccountBean>();
		List<CheckingAccount> entities = manager.list();
		
		for(CheckingAccount entity : entities){
			listBeans.add(new CheckingAccountBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new CheckingAccountDataModel(listBeans);
	}

	
	/**
	 * 
	 * @param application
	 */
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("erp") || application.getName().equals("budget"))
			beans = updateBeans();
	}

	
	public RegisterController getRegisterController() { return registerController; }


	@Override public Class<CheckingAccountBean> getBeanClazz() 				{ return CheckingAccountBean.class; }
	@Override public GenericFactory<CheckingAccount> getFactory() 		  	{ return null; }
	@Override public AbstractManager<CheckingAccount> getManager()			{ return manager; }
	@Override public GenericDataModel<CheckingAccountBean> getDataModel()	{ return dataModel; }
	

	/**
	 * Select checking account bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (CheckingAccountBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}

	
}