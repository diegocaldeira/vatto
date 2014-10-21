package com.aoks.budget.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.banking.operation.control.bean.OperationEntryBean;
import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.banking.operation.model.OperationEntry;
import com.aoks.budget.control.bean.FlowTransactionBean;
import com.aoks.budget.control.bean.dataModel.FlowTransactionDataModel;
import com.aoks.budget.model.FlowStatus;
import com.aoks.budget.model.FlowTransaction;
import com.aoks.budget.service.FlowTransactionManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;
import com.aoks.utils.webmvc.RegisterController;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@SessionScoped
@Named("flowTransactionController")
public class FlowTransactionController extends AbstractController<FlowTransaction, FlowTransactionBean>{

	private static final long serialVersionUID = 1L;
	
	@Inject private FlowTransactionManager manager;
	@Inject private transient PersistenceWrapper wrapper;
	@Inject private RegisterController registerController;
	
	private FlowTransactionDataModel dataModel;
	
	private List<OperationEntryBean> entries;
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<FlowTransactionBean> listBeans = new ArrayList<FlowTransactionBean>();
		List<FlowTransaction> entities = manager.list();
		
		for(FlowTransaction entity : entities){
			listBeans.add((FlowTransactionBean) new FlowTransactionBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new FlowTransactionDataModel(listBeans);
	}
	
	public void addEntry(){
		
		if (bean == null) return;
		OperationEntryBean entry = new OperationEntryBean();
		entry.setAccount(bean.getAccount());
		entry.setDescription(bean.getDescription());
		entry.setOcurrence(bean.getOcurrence());
		entry.setAmount(bean.getAmount());
		entries.add(entry);
		
	}
	
	public void saveEntry(RowEditEvent event) {
		
		OperationEntryBean entry = (OperationEntryBean) event.getObject();
		closeEntry(entry.build(null));
		save();
		
	}
	
	
	public void closeTransaction(){
		if (bean == null)
			return;
		
		FlowTransaction tx = bean.getModel();
		
		if (tx.isClosed())
			return;
		
		if (tx.getTxAccount().getEntries().size() == 0){
			
			if (!(tx.getAccount() == null)){
				
				OperationEntry entry = new OperationEntry();
				entry.setAccount(tx.getAccount());
				entry.setDescription(tx.getDescription());
				entry.setOcurrence(tx.getOcurrence());
				entry.setAmount(tx.getAmount());
				tx.getTxAccount().addEntry(entry);
				
				closeEntry(entry);
				
			} else 
				return;

		} 
		
		tx.setStatus(FlowStatus.CLOSED);
		save();
		
	}

	private void closeEntry(OperationEntry entry){
		
		OperationAccount account = (OperationAccount) entry.getAccount();
		account.addEntry(entry);
		FlowTransaction tx = bean.getModel();
		tx.getTxAccount().addEntry(entry);
		
		wrapper.saveOrUpdate(entry);
		saveCore();
		
	}
	
	
	public List<OperationEntryBean> getEntries() 				{ return entries; }
	public void setEntries(List<OperationEntryBean> entries)	{ this.entries = entries; }

	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("budget"))
			beans = updateBeans();
	}
	
	public RegisterController getRegisterController()						{ return registerController; }
	
	@Override public Class<FlowTransactionBean> getBeanClazz()				{ return FlowTransactionBean.class; }
	@Override public GenericFactory<FlowTransaction> getFactory() 			{ return null; }
	@Override public AbstractManager<FlowTransaction> getManager() 			{ return manager; }
	@Override public GenericDataModel<FlowTransactionBean> getDataModel()	{ return dataModel; }
	
	
	/**
	 * 
	 * @param event
	 */
	public void onRowToggle(ToggleEvent event) {  
       bean = (FlowTransactionBean) event.getData();  
       entries = bean.getEntries();
    }
	
	/**
	 * Select flow transaction item bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (FlowTransactionBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}
	
}
