package com.aoks.enterprise.control.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.CompanyGroup;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.LegalRegister;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class CompanyBean extends EnterpriseEntityBean<Company> {
	
	private static final long serialVersionUID = 1L;
	
	private Company model;
	
	private Calendar opening;
	private CompanyGroupBean group;
	private AuditEntity auditEntity;

	public CompanyBean() {
		super(new LegalRegisterBean());
	}
	
	
	@Override
	public Company build(GenericFactory<Company> factory) {
		return factory.create(this);
	}
	@Override
	public CompanyBean load(Company model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		
		List<? extends EnterpriseBehavior> parents = model.getBehavior().getParent("corporationDimension", EnterpriseEntityType.COMPANYGROUP);
		if (parents != null && parents.size() > 0)
			this.group = new CompanyGroupBean().load((CompanyGroup)parents.get(0).getEntity());
		
		return this;
		
	}
	public CompanyBean load(Company model, boolean onlyLazy){
		
		if (!onlyLazy) return this;

		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		this.opening = model.getOpening();
		
		this.register = (RegisterBean<?>) new LegalRegisterBean().load((LegalRegister) model.getBehavior().getRegister());
		
		if(auditEntity == null){
			auditEntity = new AuditEntity();
			auditEntity.setCreationDate(GregorianCalendar.getInstance());
		}
		
		auditEntity.setCompany(model);
		model.setAuditEntity(auditEntity);
		
		return this;
	}
	
	
	@Override
	public Company getModel(){
		return model;
	}
	
	
	public Calendar getOpening() {
		return opening;
	}
	public void setOpening(Calendar opening) {
		this.opening = opening;
	}

	
	public CompanyGroupBean getGroup() {
		return group;
	}
	public void setGroup(CompanyGroupBean group) {
		this.group = group;
	}
	

	public AuditEntity getAuditEntity() {
		return auditEntity;
	}
	public void setAuditEntity(AuditEntity auditEntity) {
		this.auditEntity = auditEntity;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CompanyBean)) return false;
		CompanyBean that = (CompanyBean) obj;
		if (this.name == null ? that.name == null : this.name.equals(that.name))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result + 31 * (name != null ? name.hashCode() : 0);
		return result;
	}

}
