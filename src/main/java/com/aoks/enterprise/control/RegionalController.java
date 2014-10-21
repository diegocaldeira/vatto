package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.RegionalBean;
import com.aoks.enterprise.control.factory.RegionalFactory;
import com.aoks.enterprise.model.entities.Regional;
import com.aoks.enterprise.service.RegionalManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("regionalController")
@SessionScoped
public class RegionalController extends AbstractController<Regional, RegionalBean> {

	private static final long serialVersionUID = 1L;

	@Inject private RegionalManager manager;
	@Inject private RegionalFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<RegionalBean> updateBeans() {
		
		List<RegionalBean> list = new ArrayList<RegionalBean>();
		
		List<Regional> listM = getManager().list();
		for (Regional regional : listM) {
			
			regional = (Regional) wrapper.loadLazyProperty(regional.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new RegionalBean().load(regional));
			
		}
		
		return list;
		
	}
	
	
	public void saveRegional(RowEditEvent event){
		RegionalBean object = (RegionalBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
	}
	public void addRegional(){
		RegionalBean r = new RegionalBean();
		beans.add(r);
	}

	public List<RegionalBean> getRegionalsAC(String query) {

		List<RegionalBean> suggestions = new ArrayList<RegionalBean>();

		for (RegionalBean b : beans) {
			if (b.getName().startsWith(query))
				suggestions.add(b);
		}

		return suggestions;

	}
	
	@Override
	public AbstractManager<Regional> getManager() {
		return manager;
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}


	@Override
	public Class<RegionalBean> getBeanClazz() {
		return RegionalBean.class;
	}


	@Override
	public GenericFactory<Regional> getFactory() {
		return factory;
	}


	@Override
	public GenericDataModel<RegionalBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
