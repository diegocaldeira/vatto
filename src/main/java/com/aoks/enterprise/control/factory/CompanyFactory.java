package com.aoks.enterprise.control.factory;

import java.util.GregorianCalendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.CompanyBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.CompanyGroup;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.factory.LegalRegisterFactory;
import com.aoks.register.model.LegalRegister;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class CompanyFactory implements GenericFactory<Company> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private LegalRegisterFactory legalFactory;
	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Company create(GenericBean<Company> _bean) {
		
		CompanyBean bean = (CompanyBean)_bean;
		
		Company model = bean.getModel();
		if (model == null) {
			model = new Company();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Company)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.setOpening(bean.getOpening());
		model.getBehavior().setName(bean.getName());

		LegalRegister legalRegister = legalFactory.create((LegalRegisterBean) bean.getRegister());
		model.getBehavior().setRegister(legalRegister);
		
		CompanyGroup group = (bean.getGroup() != null ? bean.getGroup().getModel() : null);
		
		if (group != null){
			
			EnterpriseBehavior companyBehavior = model.getBehavior();
			EnterpriseBehavior groupBehavior = group.getBehavior();
			
			accountabilityFactory.handleAccountability("corporationDimension", groupBehavior, companyBehavior);
			
		}
		
		if(bean.getAuditEntity() == null){
			bean.setAuditEntity(new AuditEntity());
			bean.getAuditEntity().setCreationDate(GregorianCalendar.getInstance());
		}
		
		bean.getAuditEntity().setCompany(model);
		
		return model;
	}

}
