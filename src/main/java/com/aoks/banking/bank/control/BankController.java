package com.aoks.banking.bank.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.banking.bank.control.bean.BankBean;
import com.aoks.banking.bank.control.bean.datamodel.BankDataModel;
import com.aoks.banking.bank.model.Bank;
import com.aoks.banking.bank.service.BankManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.RegisterController;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("bankController")
@SessionScoped
public class BankController extends AbstractController<Bank, BankBean> {

	private static final long serialVersionUID = 1L;

	@Inject private BankManager manager;
	@Inject private RegisterController registerController;
	
	private BankDataModel dataModel;

	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<BankBean> listBeans = new ArrayList<BankBean>();
		List<Bank> entities = manager.list();
		
		for(Bank entity : entities){
			listBeans.add(new BankBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new BankDataModel(listBeans);
	}
	
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<BankBean> getBanksAC(String query){
		List<BankBean> suggestions = new ArrayList<BankBean>();  
		List<BankBean> banks = dataModel.getFilteredBeans();
        
		for (BankBean b : banks) {
			if (b.getName().startsWith(query))
				suggestions.add(b);
		}
          
        return suggestions;  
	}
	
	
	@Override public Class<BankBean> getBeanClazz() 			{ return BankBean.class; }
	@Override public GenericFactory<Bank> getFactory()			{ return null; }
	@Override public AbstractManager<Bank> getManager()			{ return manager; }
	@Override public GenericDataModel<BankBean> getDataModel()	{ return dataModel; }

	
	/**
	 * Select bank bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (BankBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}

}