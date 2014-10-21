package com.aoks.enterprise.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.aoks.enterprise.control.bean.IndividualSupplierBean;
import com.aoks.enterprise.control.bean.LegalSupplierBean;
import com.aoks.enterprise.control.bean.SupplierBean;
import com.aoks.enterprise.control.factory.IndividualSupplierFactory;
import com.aoks.enterprise.control.factory.LegalSupplierFactory;
import com.aoks.enterprise.model.entities.IndividualSupplier;
import com.aoks.enterprise.model.entities.LegalSupplier;
import com.aoks.enterprise.service.IndividualSupplierManager;
import com.aoks.enterprise.service.LegalSupplierManager;
import com.aoks.portalmanager.model.PortalApplication;

@SessionScoped
@Named("supplierController")
public class SupplierController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private LegalSupplierManager legalManager;
	@Inject private IndividualSupplierManager individualManager;
	
	@Inject private LegalSupplierFactory legalFactory;
	@Inject private IndividualSupplierFactory individualFactory;
	
	private List<SupplierBean<?>> beans = new ArrayList<SupplierBean<?>>();
	private SupplierBean<?> bean;
	
	
	public void addSupplier(String type){
	
		if (type != null && type.equalsIgnoreCase("legal")){
			bean = new LegalSupplierBean();
		}
		else if (type != null && type.equalsIgnoreCase("individual")){
			bean = new IndividualSupplierBean();
		}
			
		
		beans.add(bean);
		
	}
	
	public void saveSupplier(RowEditEvent event){
		
		SupplierBean<?> b = (SupplierBean<?>) event.getObject();
		bean = b;
		saveIndividual();
		saveLegal();

	}
	
	public boolean has(){
		return (bean != null);
	}
	public SupplierBean<?> getBean() {
		return bean;
	}
	public void setBean(SupplierBean<?> bean) {
		this.bean = bean;
	}
	
	public void saveIndividual(){
		if (bean != null && (bean instanceof IndividualSupplierBean)){
			individualManager.load((IndividualSupplier) ((IndividualSupplierBean)bean).build(individualFactory)).save();
			if (!beans.contains(bean))
				beans.add(bean);
			bean = null;
		}
	}
	public void saveLegal(){
		if (bean != null && (bean instanceof LegalSupplierBean)){
			legalManager.load((LegalSupplier) ((LegalSupplierBean)bean).build(legalFactory)).save();
			if (!beans.contains(bean))
				beans.add(bean);
			bean = null;
		}
	}
	
	
	public void clean(){
		bean = null;
	}
	
	
	public List<SupplierBean<?>> getBeans() {
		return beans;
	}
	public void setBeans(List<SupplierBean<?>> beans) {
		this.beans = beans;
	} 
	
	public void listApplicationLoading(@Observes PortalApplication application){
		if (application.getName().equals("zebra")){
			
			beans = new ArrayList<SupplierBean<?>>();
			
			List<LegalSupplier> legalList = legalManager.list();
			for (LegalSupplier legalSupplier : legalList) {
				beans.add(new LegalSupplierBean().load(legalSupplier));
			}
			
			List<IndividualSupplier> individualList = individualManager.list();
			for (IndividualSupplier individualSupplier : individualList) {
				beans.add(new IndividualSupplierBean().load(individualSupplier));
			}
			
		}
	}
	
}