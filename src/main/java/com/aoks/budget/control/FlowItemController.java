package com.aoks.budget.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aoks.budget.control.bean.FlowItemBean;
import com.aoks.budget.control.bean.dataModel.FlowItemInstalmentsDataModel;
import com.aoks.budget.control.bean.dataModel.FlowItemRecurrentDataModel;
import com.aoks.budget.control.bean.dataModel.FlowItemTransferDataModel;
import com.aoks.budget.control.bean.factory.FlowItemDataModelFactory;
import com.aoks.budget.model.FlowItem;
import com.aoks.budget.service.FlowItemManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.security.control.SecureSessionController;
import com.aoks.utils.category.control.bean.CategoryBean;
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
@Named("flowController")
@SessionScoped
public class FlowItemController extends AbstractController<FlowItem, FlowItemBean> {

	private static final long serialVersionUID = 1L;

	@Inject private FlowItemManager manager;
	@Inject private FlowTransactionController transactionController;
	@Inject private RegisterController registerController;
	@Inject private FlowItemDataModelFactory dataModelFactory;
	@Inject private SecureSessionController secureSessionController;
	
	private FlowItemRecurrentDataModel recurrentDataModel;
	private FlowItemInstalmentsDataModel instalmentsDataModel;
	private FlowItemTransferDataModel transferDataModel;
	
	private String flowType;
	private String flowItemType;
	
	private List<CategoryBean> subCategories;
	
	public FlowItemController() {
		subCategories = new ArrayList<CategoryBean>();
	}
	
	/**
	 * 
	 * @param flowType
	 */
	public void loadType(String flowType){
		this.flowType = flowType;
	}
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String flowItemType){
		this.flowItemType = flowItemType;
		
		List<FlowItemBean> listBeans = new ArrayList<FlowItemBean>();
		List<FlowItem> entities = manager.listByFlowType(flowType, flowItemType);
		
		for(FlowItem entity : entities){
			listBeans.add(new FlowItemBean().load(entity));
		}
		
		Collections.sort(listBeans);
		verifyDataModelType(dataModelFactory.loadByType(flowItemType, listBeans));		
	}
	
	
	/**
	 * 
	 * @param dataModel
	 * @return
	 */
	private GenericDataModel<FlowItemBean> verifyDataModelType(GenericDataModel<FlowItemBean> dataModel) {
		if(dataModel instanceof FlowItemRecurrentDataModel){ 
			recurrentDataModel = (FlowItemRecurrentDataModel) dataModel;
			return recurrentDataModel;
		}
		if(dataModel instanceof FlowItemInstalmentsDataModel){
			instalmentsDataModel = (FlowItemInstalmentsDataModel) dataModel;
			return instalmentsDataModel;
		}
		if(dataModel instanceof FlowItemTransferDataModel){
			transferDataModel = (FlowItemTransferDataModel) dataModel;
			return transferDataModel;
		}
		
		return null;
	}

	public void searchSubCategories(){
		if (bean == null) return;
		CategoryBean category = bean.getCategory();
		if (category == null) return;
		subCategories = category.getChilds();
	}
	public List<CategoryBean> getSubCategories(){
		searchSubCategories();
		return subCategories;
	}
	
	
	public void createCredit(){
		super.create();
		bean.setCredit(true);
	}
	public boolean hasCredit(){
		return (bean != null && bean.isCredit());
	}
	
	
	public void createDebit(){
		super.create();
		bean.setCredit(false);
	}
	public boolean hasDebit(){
		return (bean != null && !bean.isCredit());
	}
	
	
	public void createTransfer(){
		super.create();
		bean.setTransfer(true);
		bean.setType("TRANSFER");
	}
	public boolean hasTransfer(){
		return (bean != null && bean.isTransfer());
	}

	
	public boolean isRecurrent()	{ return (bean != null && bean.isRecurrentType()   ? true : false); }
	public boolean isInstalments()	{ return (bean != null && bean.isInstalmentsType() ? true : false); }
	
	
	@Override
	public void save() {
		
		manager.load(getAsModel(bean));
		FlowItem item = bean.getModel();
		item.generateTransactions();
		manager.save();
		
		generatesTimeline();
		
		doPostSave();
	}

	private void generatesTimeline() {
//		if(manager.getModel().getId() != 0){
//			User user = secureSessionController.getLoggedBean().getModel();
//			Timeline timeline = new Timeline(TimelineType.FINANCIAL, bean.getName(), bean.getDescription(), user);
//			
//			FlowItemTimeline flowItemTimeline = new FlowItemTimeline();
//			flowItemTimeline.setTimeline(timeline);
//			manager.getWrapper().saveOrUpdate(flowItemTimeline);
//		}
	}
	
	@Override
	protected void doPostSave() {
		super.doPostSave();
		if (transactionController != null)
			transactionController.updateBeans();
		clean();
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("budget"))
			beans = updateBeans();
	}
	
	
	public RegisterController getRegisterController() 				{ return registerController; }
	
	@Override public Class<FlowItemBean> getBeanClazz() 			{ return FlowItemBean.class; }
	@Override public GenericFactory<FlowItem> getFactory() 			{ return null; }
	@Override public AbstractManager<FlowItem> getManager() 		{ return manager; }
	@Override public GenericDataModel<FlowItemBean> getDataModel() 	{ 
		switch(flowItemType){
			case "RECURRENT"	 : return recurrentDataModel;
			case "INSTALMENTS"	 : return instalmentsDataModel;
			case "TRANSFER"  	 : return transferDataModel;
		}
		return null;
	}
	
	
	public FlowItemDataModelFactory getDataModelFactory() 			{ return dataModelFactory; }
	public FlowItemTransferDataModel getTransferDataModel() 		{ return transferDataModel; }
	public FlowItemRecurrentDataModel getRecurrentDataModel()		{ return recurrentDataModel; }
	public FlowItemInstalmentsDataModel getInstalmentsDataModel()	{ return instalmentsDataModel; }

	
	/**
	 * Select flow item bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (FlowItemBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}
	
}
