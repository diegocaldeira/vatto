package com.aoks.enterprise.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;

import com.aoks.enterprise.control.bean.CompanyBean;
import com.aoks.enterprise.control.bean.datamodel.CompanyDataModel;
import com.aoks.enterprise.control.factory.CompanyFactory;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.service.CompanyManager;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.register.exporter.ExportContacts;
import com.aoks.security.control.UserController;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;
import com.aoks.utils.webmvc.RegisterController;


/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@SessionScoped
@Named("companyController")
public class CompanyController extends AbstractController<Company, CompanyBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private CompanyManager manager;
	@Inject private CompanyFactory factory;
	@Inject private RegisterController registerController;
	@Inject private UserController userController;
	
	@Inject private transient PersistenceWrapper wrapper;
	
	private CompanyDataModel dataModel;
	
	
	/**
	 * Carry the entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		
		List<CompanyBean> companyBeans = new ArrayList<CompanyBean>();
		List<Company> entities = getManager().list();
		
		for (Company company : entities) {
			company = (Company) wrapper.loadLazyProperty(company.getBehavior(), "getParentAccountabilities").getEntity();
			companyBeans.add(new CompanyBean().load(company));
		}
		
		dataModel = new CompanyDataModel(companyBeans);
	}


	/**
	 * Exports contacts to excel
	 * 
	 * @return
	 */
	public StreamedContent export() {
		ExportContacts export = new ExportContacts();
		return export.exportCompanyBeans(beans);
	}
	
	
	public UserController getUserController() 					  { return userController; }
	public RegisterController getRegisterController()			  { return registerController; 	}
	
	
	@Override public Class<CompanyBean> getBeanClazz() 			  { return CompanyBean.class; 	}
	@Override public GenericFactory<Company> getFactory() 		  { return factory; 			}
	@Override public AbstractManager<Company> getManager() 		  { return manager; 			}
	@Override public GenericDataModel<CompanyBean> getDataModel() { return dataModel; }
	
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("erp"))
			beans = updateBeans();
	}
	
	
	/**
	 * Select company bean from view list and assign to this bean  
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event)	{ bean = (CompanyBean) event.getObject();  registerController.setCreate(Boolean.TRUE);	}  
	
	
	/**
	 * Setting bean as null
	 * 
	 * @param event
	 */
    public void onRowUnselect(UnselectEvent event) {  clean(); registerController.setCreate(Boolean.FALSE);	}

}