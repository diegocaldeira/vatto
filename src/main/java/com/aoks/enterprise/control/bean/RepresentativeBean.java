package com.aoks.enterprise.control.bean;

import java.util.List;

import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.entities.Regional;
import com.aoks.enterprise.model.entities.Representative;
import com.aoks.utils.webmvc.GenericFactory;

public class RepresentativeBean extends EnterpriseEntityBean<Representative> {

	private static final long serialVersionUID = 1L;
	
	private RegionalBean regional;
	private Representative model;
	
	public RepresentativeBean() {
		super(null);
	}
	
	
	public RegionalBean getRegional() {
		return regional;
	}
	public void setRegional(RegionalBean regional) {
		this.regional = regional;
	}

	
	public String getRegionalDescription(){
		return (regional != null ? regional.getDescription() : "");
	}
	

	@Override
	public Representative build(GenericFactory<Representative> factory) {
		return factory.create(this);
	}
	@Override
	public Representative getModel() {
		return model;
	}
	@Override
	public RepresentativeBean load(Representative model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		load(model, true);
		
		List<? extends EnterpriseBehavior> parents = model.getBehavior().getParent("regionalDimension", EnterpriseEntityType.REGIONAL);
		if (parents != null && parents.size() > 0)
			this.regional = new RegionalBean().load((Regional)parents.get(0).getEntity(), true);
		
		return this;
		
	}
	
	public RepresentativeBean load(Representative model, boolean onlyLazy){
		
		this.model = model;
		
		this.id = model.getId();
		this.name = model.getBehavior().getName();
		
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof RepresentativeBean)) return false;
		RepresentativeBean that = (RepresentativeBean) obj;
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
