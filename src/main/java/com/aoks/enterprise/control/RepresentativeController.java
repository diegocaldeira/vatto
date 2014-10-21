package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.RepresentativeBean;
import com.aoks.enterprise.control.factory.RepresentativeFactory;
import com.aoks.enterprise.model.entities.Representative;
import com.aoks.enterprise.service.RepresentativeManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("representativeController")
@SessionScoped
public class RepresentativeController extends AbstractController<Representative, RepresentativeBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private RepresentativeManager manager;
	@Inject private RepresentativeFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<RepresentativeBean> updateBeans() {
		
		List<RepresentativeBean> list = new ArrayList<RepresentativeBean>();
		
		List<Representative> listM = getManager().list();
		for (Representative rep : listM) {
			
			rep = (Representative) wrapper.loadLazyProperty(rep.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new RepresentativeBean().load(rep));
			
		}
		
		return list;
		
	}
	
	
	public List<RepresentativeBean> getRepresentativesAC(String query) {

		List<RepresentativeBean> suggestions = new ArrayList<RepresentativeBean>();

		for (RepresentativeBean b : beans) {
			if (b.getName().startsWith(query))
				suggestions.add(b);
		}

		return suggestions;

	}
	
	
	public void saveRepresentative(RowEditEvent event){
		RepresentativeBean object = (RepresentativeBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addRepresentative(){
		RepresentativeBean rep = new RepresentativeBean();
		beans.add(rep);
	}


	
	@Override
	public AbstractManager<Representative> getManager() {
		return manager;
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}
	

	@Override
	public Class<RepresentativeBean> getBeanClazz() {
		return RepresentativeBean.class;
	}

	@Override
	public GenericFactory<Representative> getFactory() {
		return factory;
	}


	@Override
	public GenericDataModel<RepresentativeBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
