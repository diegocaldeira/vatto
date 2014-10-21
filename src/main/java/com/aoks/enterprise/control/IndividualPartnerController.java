package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;

import com.aoks.enterprise.control.bean.IndividualPartnerBean;
import com.aoks.enterprise.control.bean.datamodel.IndividualPartnerDataModel;
import com.aoks.enterprise.control.factory.IndividualPartnerFactory;
import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.enterprise.model.entities.PartnerType;
import com.aoks.enterprise.service.IndividualPartnerManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.register.exporter.ExportContacts;
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
@Named("individualPartnerController")
@SessionScoped
public class IndividualPartnerController extends AbstractController<IndividualPartner, IndividualPartnerBean> {

	private static final long serialVersionUID = 1L;

	@Inject private IndividualPartnerManager manager;
	@Inject private IndividualPartnerFactory factory;
	@Inject private RegisterController registerController;
	
	private IndividualPartnerDataModel dataModel;
	protected String partnerType;


	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		this.partnerType = partnerType;
		
		List<IndividualPartnerBean> partnersBean = new ArrayList<IndividualPartnerBean>();
		List<IndividualPartner> entities = manager.findByType(this.partnerType);
		
		for(IndividualPartner partner : entities){
			partnersBean.add(new IndividualPartnerBean().load(partner));
		}
		
		Collections.sort(partnersBean);
		dataModel = new IndividualPartnerDataModel(partnersBean);
	}

	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("erp"))
			beans = updateBeans();
	}
	
	
	public void create(){
		try {
			PartnerType _type = PartnerType.valueOf(partnerType);
			bean = new IndividualPartnerBean();
			bean.setType(_type.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public StreamedContent export() {
		ExportContacts export = new ExportContacts();
		return export.exportIndividualBeans(beans);
	}	
	
	
	public RegisterController getRegisterController()							{ return registerController; }
	
	@Override public Class<IndividualPartnerBean> getBeanClazz() 				{ return IndividualPartnerBean.class; }
	@Override public GenericFactory<IndividualPartner> getFactory()				{ return factory; }
	@Override public AbstractManager<IndividualPartner> getManager() 			{ return manager; }
	@Override public GenericDataModel<IndividualPartnerBean> getDataModel()		{ return dataModel; }
	

	/**
	 * Select individual partner bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (IndividualPartnerBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}


	
}
