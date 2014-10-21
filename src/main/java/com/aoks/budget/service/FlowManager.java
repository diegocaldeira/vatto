package com.aoks.budget.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.budget.model.Flow;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class FlowManager extends AbstractManager<Flow> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Flow> getModelClazz() {
		return Flow.class;
	}

	
	public Flow getFromName(String name){
		Flow user = wrapper.get(Flow.class, "name", name);
		return user;
		
	}

}
