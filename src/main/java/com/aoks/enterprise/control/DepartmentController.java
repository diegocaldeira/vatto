package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.DepartmentBean;
import com.aoks.enterprise.control.factory.DepartmentFactory;
import com.aoks.enterprise.model.entities.Department;
import com.aoks.enterprise.service.DepartmentManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("departmentController")
@SessionScoped
public class DepartmentController extends AbstractController<Department, DepartmentBean> {

	private static final long serialVersionUID = 1L;

	@Inject private DepartmentManager manager;
	@Inject private DepartmentFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<DepartmentBean> updateBeans() {
		
		List<DepartmentBean> list = new ArrayList<DepartmentBean>();
		
		List<Department> listM = getManager().list();
		for (Department department : listM) {
			
			department = (Department) wrapper.loadLazyProperty(department.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new DepartmentBean().load(department));
			
		}
		
		return list;
		
	}


	public void saveDepartment(RowEditEvent event){
		DepartmentBean object = (DepartmentBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addDepartment(){
		DepartmentBean rep = new DepartmentBean();
		beans.add(rep);
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}
	

	@Override
	public AbstractManager<Department> getManager() {
		return manager;
	}

	@Override
	public Class<DepartmentBean> getBeanClazz() {
		return DepartmentBean.class;
	}

	@Override
	public GenericFactory<Department> getFactory() {
		return factory;
	}


	@Override
	public GenericDataModel<DepartmentBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
