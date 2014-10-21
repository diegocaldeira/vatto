package com.aoks.enterprise.control;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.enterprise.control.bean.CostumerBean;
import com.aoks.enterprise.control.bean.datamodel.CostumerDataModel;
import com.aoks.enterprise.control.factory.CostumerFactory;
import com.aoks.enterprise.model.entities.Costumer;
import com.aoks.enterprise.service.CostumerManager;
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
@SessionScoped
@Named("costumerController")
public class CostumerController extends AbstractController<Costumer, CostumerBean> {
	
	private static final long serialVersionUID = 1L;

	@Inject private CostumerManager manager;
	@Inject private CostumerFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	private CostumerDataModel dataModel;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeans() {
		beans.clear();

		List<Costumer> entities = getManager().list();
		for (Costumer costumer : entities) {
			costumer = (Costumer) wrapper.loadLazyProperty(costumer.getBehavior(), "getParentAccountabilities").getEntity();
			beans.add(new CostumerBean().load(costumer));
		}
		
		dataModel = new CostumerDataModel(beans);
	}
	

	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}

	
	@Override public Class<CostumerBean> getBeanClazz() 			{ return CostumerBean.class; }
	@Override public GenericFactory<Costumer> getFactory() 			{ return factory; }
	@Override public AbstractManager<Costumer> getManager() 		{ return manager; }


	@Override
	public GenericDataModel<CostumerBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
