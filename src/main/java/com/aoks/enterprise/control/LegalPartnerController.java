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

import com.aoks.enterprise.control.bean.LegalPartnerBean;
import com.aoks.enterprise.control.bean.datamodel.LegalPartnerDataModel;
import com.aoks.enterprise.control.factory.LegalPartnerFactory;
import com.aoks.enterprise.model.entities.LegalPartner;
import com.aoks.enterprise.model.entities.PartnerType;
import com.aoks.enterprise.service.LegalPartnerManager;
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
@Named("legalPartnerController")
@SessionScoped
public class LegalPartnerController extends AbstractController<LegalPartner, LegalPartnerBean> {

	private static final long serialVersionUID = 1L;

	@Inject private LegalPartnerManager manager;
	@Inject private LegalPartnerFactory factory;
	@Inject private RegisterController registerController;
	
	private LegalPartnerDataModel dataModel;
	protected String partnerType;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		this.partnerType = partnerType;
		
		List<LegalPartnerBean> partnersBean = new ArrayList<LegalPartnerBean>();
		List<LegalPartner> entities = manager.findByType(this.partnerType);
		
		for(LegalPartner partner : entities){
			partnersBean.add(new LegalPartnerBean().load(partner));
		}
		
		Collections.sort(partnersBean);
		dataModel = new LegalPartnerDataModel(partnersBean);
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("erp"))
			beans = updateBeans();
	}

	
	public void create(){
		try {
			PartnerType _type = PartnerType.valueOf(partnerType);
			bean = new LegalPartnerBean();
			bean.setType(_type.name());
			beans.add(bean);
		} catch (Exception e) {}
	}
	
	
	public RegisterController getRegisterController()					{ return registerController;}

	@Override public Class<LegalPartnerBean> getBeanClazz() 			{ return LegalPartnerBean.class; }
	@Override public GenericFactory<LegalPartner> getFactory() 			{ return factory; }
	@Override public AbstractManager<LegalPartner> getManager() 		{ return manager; }
	@Override public GenericDataModel<LegalPartnerBean> getDataModel() 	{ return dataModel; }
	
	
	/**
	 * Select legal partner bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event){ 
		bean = (LegalPartnerBean) event.getObject();
		registerController.setCreate(Boolean.TRUE);
	}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) { 
    	clean(); 
    	registerController.setCreate(Boolean.FALSE);
    }

}