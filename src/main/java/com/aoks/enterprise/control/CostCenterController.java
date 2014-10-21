package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.CostCenterBean;
import com.aoks.enterprise.control.factory.CostCenterFactory;
import com.aoks.enterprise.model.entities.CostCenter;
import com.aoks.enterprise.service.CostCenterManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("costCenterController")
@SessionScoped
public class CostCenterController extends AbstractController<CostCenter, CostCenterBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private CostCenterManager manager;
	@Inject private CostCenterFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<CostCenterBean> updateBeans() {
		
		List<CostCenterBean> list = new ArrayList<CostCenterBean>();
		
		List<CostCenter> listM = getManager().list();
		for (CostCenter costCenter : listM) {
			
			costCenter = (CostCenter) wrapper.loadLazyProperty(costCenter.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new CostCenterBean().load(costCenter));
			
		}
		
		return list;
		
	}
	
	
	public void saveCostCenter(RowEditEvent event){
		CostCenterBean object = (CostCenterBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addCostCenter(){
		CostCenterBean rep = new CostCenterBean();
		beans.add(rep);
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}

	@Override
	public AbstractManager<CostCenter> getManager() {
		return manager;
	}


	@Override
	public Class<CostCenterBean> getBeanClazz() {
		return CostCenterBean.class;
	}


	@Override
	public GenericFactory<CostCenter> getFactory() {
		return factory;
	}


	@Override
	public GenericDataModel<CostCenterBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
