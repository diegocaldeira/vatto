package com.aoks.enterprise.control.bean;

import java.util.List;

import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.entities.Department;
import com.aoks.enterprise.model.entities.Sector;
import com.aoks.utils.webmvc.GenericFactory;

public class SectorBean extends EnterpriseEntityBean<Sector> {

	private static final long serialVersionUID = 1L;

	private IndividualPartnerBean responsible;
	private DepartmentBean department;
	
	private Sector model;
	
	public SectorBean() {
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
	
	
	public DepartmentBean getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	public String getDepartmentDescription(){
		return (department != null ? department.getName() : "Não informado");
	}


	@Override
	public Sector build(GenericFactory<Sector> factory) {
		return factory.create(this);
	}


	@Override
	public Sector getModel() {
		return model;
	}


	@Override
	public SectorBean load(Sector model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		
		List<? extends EnterpriseBehavior> parents = model.getBehavior().getParent("structureDimension", EnterpriseEntityType.DEPARTMENT);
		if (parents != null && parents.size() > 0)
			this.department = new DepartmentBean().load((Department)parents.get(0).getEntity(), true);
		
		return this;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof SectorBean)) return false;
		SectorBean that = (SectorBean) obj;
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
