package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.ResultCenterBean;
import com.aoks.enterprise.control.factory.ResultCenterFactory;
import com.aoks.enterprise.model.entities.ResultCenter;
import com.aoks.enterprise.service.ResultCenterManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("resultCenterController")
@SessionScoped
public class ResultCenterController extends AbstractController<ResultCenter, ResultCenterBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private ResultCenterManager manager;
	@Inject private ResultCenterFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<ResultCenterBean> updateBeans() {
		
		List<ResultCenterBean> list = new ArrayList<ResultCenterBean>();
		
		List<ResultCenter> listM = getManager().list();
		for (ResultCenter resultCenter : listM) {
			
			resultCenter = (ResultCenter) wrapper.loadLazyProperty(resultCenter.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new ResultCenterBean().load(resultCenter));
			
		}
		
		return list;
		
	}
	

	public void saveResultCenter(RowEditEvent event){
		ResultCenterBean object = (ResultCenterBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addResultCenter(){
		ResultCenterBean rep = new ResultCenterBean();
		beans.add(rep);
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}
	

	@Override
	public AbstractManager<ResultCenter> getManager() {
		return manager;
	}

	@Override
	public Class<ResultCenterBean> getBeanClazz() {
		return ResultCenterBean.class;
	}
	@Override
	public GenericFactory<ResultCenter> getFactory() {
		return factory;
	}


	@Override
	public GenericDataModel<ResultCenterBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
