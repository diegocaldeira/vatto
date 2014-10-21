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

import com.aoks.banking.bank.control.bean.datamodel.SavingsAccountDataModel;
import com.aoks.banking.operation.control.bean.SavingsAccountBean;
import com.aoks.banking.operation.model.SavingsAccount;
import com.aoks.banking.operation.service.SavingsAccountManager;
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
@Named("savingsAccountController")
@SessionScoped
public class SavingsAccountController extends AbstractController<SavingsAccount, SavingsAccountBean> {

	private static final long serialVersionUID = 1L;

	@Inject private SavingsAccountManager manager;
	@Inject private RegisterController registerController;
	
	private SavingsAccountDataModel dataModel;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<SavingsAccountBean> listBeans = new ArrayList<SavingsAccountBean>();
		List<SavingsAccount> entities = manager.list();
		
		for(SavingsAccount entity : entities){
			listBeans.add(new SavingsAccountBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new SavingsAccountDataModel(listBeans);
	}

	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra") || application.getName().equals("budget"))
			beans = updateBeans();
	}
	
	public RegisterController getRegisterController() 						{ return registerController; }

	@Override public Class<SavingsAccountBean> getBeanClazz() 				{ return SavingsAccountBean.class; }
	@Override public GenericFactory<SavingsAccount> getFactory() 			{ return null; }
	@Override public AbstractManager<SavingsAccount> getManager() 			{ return manager; }
	@Override public GenericDataModel<SavingsAccountBean> getDataModel()	{ return dataModel; }
	
	
	/**
	 * Select savings account bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (SavingsAccountBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}

}
