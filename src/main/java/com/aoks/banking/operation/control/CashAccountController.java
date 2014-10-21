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

import com.aoks.banking.bank.control.bean.datamodel.CashAccountDataModel;
import com.aoks.banking.operation.control.bean.CashAccountBean;
import com.aoks.banking.operation.model.CashAccount;
import com.aoks.banking.operation.service.CashAccountManager;
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
@Named("cashAccountController")
@SessionScoped
public class CashAccountController extends AbstractController<CashAccount, CashAccountBean> {

	private static final long serialVersionUID = 1L;

	@Inject private CashAccountManager manager;
	@Inject private RegisterController registerController;
	
	private CashAccountDataModel dataModel;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<CashAccountBean> listBeans = new ArrayList<CashAccountBean>();
		List<CashAccount> entities = manager.list();
		
		for(CashAccount entity : entities){
			listBeans.add(new CashAccountBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new CashAccountDataModel(listBeans);
	}


	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra") || application.getName().equals("budget"))
			beans = updateBeans();
	}

	
	public RegisterController getRegisterController() 					{ return registerController; }

	@Override public Class<CashAccountBean> getBeanClazz()				{ return CashAccountBean.class; }
	@Override public GenericFactory<CashAccount> getFactory() 			{ return null; }
	@Override public AbstractManager<CashAccount> getManager() 			{ return manager; }
	@Override public GenericDataModel<CashAccountBean> getDataModel()	{ return dataModel; }
	
	
	/**
	 * Select cash account bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (CashAccountBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}

	
}
