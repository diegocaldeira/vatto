package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.SectorBean;
import com.aoks.enterprise.control.factory.SectorFactory;
import com.aoks.enterprise.model.entities.Sector;
import com.aoks.enterprise.service.SectorManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@Named("sectorController")
@SessionScoped
public class SectorController extends AbstractController<Sector, SectorBean> {

	private static final long serialVersionUID = 1L;

	@Inject private SectorManager manager;
	@Inject private SectorFactory factory;
	@Inject private transient PersistenceWrapper wrapper;
	
	
	@Override
	public List<SectorBean> updateBeans() {
		
		List<SectorBean> list = new ArrayList<SectorBean>();
		
		List<Sector> listM = getManager().list();
		for (Sector sector : listM) {
			
			sector = (Sector) wrapper.loadLazyProperty(sector.getBehavior(), "getParentAccountabilities").getEntity();
			list.add(new SectorBean().load(sector));
			
		}
		
		return list;
		
	}


	public void saveSector(RowEditEvent event){
		SectorBean object = (SectorBean) event.getObject();
		if (object != null) {
			bean = object;
			save();
		}
		
	}
	public void addSector(){
		SectorBean rep = new SectorBean();
		beans.add(rep);
	}
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra"))
			beans = updateBeans();
	}


	@Override
	public AbstractManager<Sector> getManager() {
		return manager;
	}


	@Override
	public Class<SectorBean> getBeanClazz() {
		return SectorBean.class;
	}


	@Override
	public GenericFactory<Sector> getFactory() {
		return factory;
	}

	@Override
	public GenericDataModel<SectorBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
