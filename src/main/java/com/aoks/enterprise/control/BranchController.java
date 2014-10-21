package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.BranchBean;
import com.aoks.enterprise.control.factory.BranchFactory;
import com.aoks.enterprise.model.entities.Branch;
import com.aoks.enterprise.service.BranchManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("branchController")
@SessionScoped
public class BranchController extends AbstractController<Branch, BranchBean>{

	private static final long serialVersionUID = 1L;
	
	@Inject private BranchManager manager;
	@Inject private BranchFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<BranchBean> updateBeans() {
		
		List<BranchBean> list = new ArrayList<BranchBean>();
		
		List<Branch> listM = getManager().list();
		for (Branch branch : listM) {
			
			branch = (Branch) wrapper.loadLazyProperty(branch.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new BranchBean().load(branch));
			
		}
		
		return list;
		
	}
	
	
	public void saveBranch(RowEditEvent event){
		BranchBean object = (BranchBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addBranch(){
		BranchBean rep = new BranchBean();
		beans.add(rep);
	}
	

	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}
	

	@Override
	public AbstractManager<Branch> getManager() {
		return manager;
	}

	@Override
	public Class<BranchBean> getBeanClazz() {
		return BranchBean.class;
	}

	@Override
	public GenericFactory<Branch> getFactory() {
		return factory;
	}
	
	@Override
	public GenericDataModel<BranchBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
