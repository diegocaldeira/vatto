package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.CompanyGroupBean;
import com.aoks.enterprise.model.entities.CompanyGroup;
import com.aoks.enterprise.service.CompanyGroupManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

@Named("companyGroupController")
@SessionScoped
public class CompanyGroupController extends AbstractController<CompanyGroup, CompanyGroupBean> {

	private static final long serialVersionUID = 1L;

	@Inject private CompanyGroupManager manager;
	
	@PostConstruct
	public void init(){
		List<CompanyGroup> groups = manager.list();
		for(CompanyGroup group : groups){
			beans.add(new CompanyGroupBean().load(group));
		}
	}
	
	public List<CompanyGroupBean> getCompanyGroupsAC(String query) {

		List<CompanyGroupBean> suggestions = new ArrayList<CompanyGroupBean>();

		for (CompanyGroupBean b : beans) {
			if (b.getName().startsWith(query))
				suggestions.add(b);
		}

		return suggestions;

	}
	
	
	public void saveGroup(RowEditEvent event){
		CompanyGroupBean object = (CompanyGroupBean) event.getObject();
		if (object != null)
			getManager().load(object.build(null)).save();
	}
	public void addGroup(){
		CompanyGroupBean cg = new CompanyGroupBean();
		beans.add(cg);
	}
	
	
	@Override
	public AbstractManager<CompanyGroup> getManager() {
		return manager;
	}
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}


	@Override
	public Class<CompanyGroupBean> getBeanClazz() {
		return CompanyGroupBean.class;
	}


	@Override
	public GenericFactory<CompanyGroup> getFactory() {
		return null;
	}


	@Override
	public GenericDataModel<CompanyGroupBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
