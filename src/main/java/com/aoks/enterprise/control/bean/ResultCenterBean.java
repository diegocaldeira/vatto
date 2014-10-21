package com.aoks.enterprise.control.bean;

import java.util.List;

import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.entities.Company;
import com.aoks.enterprise.model.entities.ResultCenter;
import com.aoks.utils.webmvc.GenericFactory;

public class ResultCenterBean extends EnterpriseEntityBean<ResultCenter> {

	private static final long serialVersionUID = 1L;
	
	private IndividualPartnerBean responsible;
	private CompanyBean company;

	private ResultCenter model;
	
	
	public ResultCenterBean() {
		super(null);
	}
	
	
	public IndividualPartnerBean getResponsible() {
		return responsible;
	}
	public void setResponsible(IndividualPartnerBean responsible) {
		this.responsible = responsible;
	}
	public String getResponsibleDescription(){
		return (responsible != null ? responsible.getName() : "Não informado");
	}
	
	
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public String getCompanyDescription(){
		return (company != null ? company.getName() : "Não informado");
	}
	

	@Override
	public ResultCenter build(GenericFactory<ResultCenter> factory) {
		return factory.create(this);
	}

	@Override
	public ResultCenter getModel() {
		return model;
	}

	@Override
	public ResultCenterBean load(ResultCenter model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		
		List<? extends EnterpriseBehavior> parents = model.getBehavior().getParent("resultDimension", EnterpriseEntityType.COMPANY);
		if (parents != null && parents.size() > 0)
			this.company = new CompanyBean().load((Company)parents.get(0).getEntity(), true);
		
		return this;
		
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof ResultCenterBean)) return false;
		ResultCenterBean that = (ResultCenterBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if (this.name == null ? that.name == null : this.name.equals(that.name))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else 
			result = result * 31 + (name != null ? name.hashCode() : 0);
		return result;
	}
	
}
