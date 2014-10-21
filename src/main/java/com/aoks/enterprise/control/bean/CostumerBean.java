package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.Costumer;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericFactory;

public class CostumerBean extends EnterpriseEntityBean<Costumer>{

	private static final long serialVersionUID = 1L;

	private Costumer model;
	
	public CostumerBean() {
		super(new LegalRegisterBean());
	}

	@Override
	public Costumer build(GenericFactory<Costumer> factory) {
		return factory.create(this);
	}

	@Override
	public Costumer getModel() {
		return model;
	}

	@Override
	public CostumerBean load(Costumer model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getBehavior().getName(); 
		
		this.register = (RegisterBean<?>) new LegalRegisterBean().load((LegalRegister) model.getBehavior().getRegister());
		
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CostumerBean)) return false;
		CostumerBean that = (CostumerBean) obj;
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
