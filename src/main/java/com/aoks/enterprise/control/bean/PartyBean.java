package com.aoks.enterprise.control.bean;

import com.aoks.enterprise.model.entities.Party;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class PartyBean extends EnterpriseEntityBean<Party> implements Comparable<PartyBean>{

	private static final long serialVersionUID = 1L;
	
	private Party model;
	
	public PartyBean() {
		super(null);
	}

	@Override
	public Party build(GenericFactory<Party> factory) {
		if (model == null)
			model = new Party();
		
		model.getBehavior().setName(name);
		
		return model;
	}

	@Override
	public Party getModel() {
		return model;
	}

	@Override
	public PartyBean load(Party model) {
	
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getBehavior().getName();
		
		return this;
	}
	

	@Override
	public int compareTo(PartyBean _bean){
		return name.compareTo(_bean.name);
	}
	
}
