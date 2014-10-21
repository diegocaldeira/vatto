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

import com.aoks.enterprise.control.bean.PartyBean;
import com.aoks.enterprise.control.bean.datamodel.PartyDataModel;
import com.aoks.enterprise.model.entities.Party;
import com.aoks.enterprise.service.PartyManager;
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
@SessionScoped
@Named("partyController")
public class PartyController extends AbstractController<Party, PartyBean> {

	private static final long serialVersionUID = 1L;

	@Inject private PartyManager manager;
	@Inject private RegisterController registerController;
	
	private PartyDataModel dataModel;
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		List<PartyBean> listBeans = new ArrayList<PartyBean>();
		List<Party> entities = manager.list();
		
		for(Party entity : entities){
			listBeans.add(new PartyBean().load(entity));
		}
		
		Collections.sort(listBeans);
		dataModel = new PartyDataModel(listBeans);
	}
	

	public List<PartyBean> getPartiesAC(String query) {
		loadBeansByType("Payee");
		
		List<PartyBean> _beans = dataModel.getFilteredBeans();
		List<PartyBean> suggestions = new ArrayList<PartyBean>();

		for (PartyBean b : _beans) {
			if (b.getName().startsWith(query))
				suggestions.add(b);
		}

		return suggestions;

	}
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("budget"))
			beans = updateBeans();
	}

	public RegisterController getRegisterController() 			{ return registerController; }

	@Override public Class<PartyBean> getBeanClazz() 			{ return PartyBean.class; }
	@Override public GenericFactory<Party> getFactory() 		{ return null; }
	@Override public AbstractManager<Party> getManager() 		{ return manager; }
	@Override public GenericDataModel<PartyBean> getDataModel() { return dataModel; }
	
	
	/**
	 * Select party bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {  bean = (PartyBean) event.getObject(); registerController.setCreate(Boolean.TRUE);}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean();  registerController.setCreate(Boolean.FALSE);}

}
