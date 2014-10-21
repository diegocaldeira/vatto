package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.CompanyGroup;
import com.aoks.utils.webmvc.GenericFactory;

public class CompanyGroupBean extends EnterpriseEntityBean<CompanyGroup> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private CompanyGroup model;
	
	public CompanyGroupBean() {
		super(null);
	}

	
	@Override
	public long getId() {
		return id;
	}
	@Override
	public CompanyGroup build(GenericFactory<CompanyGroup> factory) {
		
		if (model == null)
			model = new CompanyGroup();
		
		model.getBehavior().setName(name);
		
		return model;
	}
	@Override
	public CompanyGroup getModel() {
		return model;
	}
	@Override
	public CompanyGroupBean load(CompanyGroup model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CompanyGroupBean)) return false;
		CompanyGroupBean that = (CompanyGroupBean) obj;
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
