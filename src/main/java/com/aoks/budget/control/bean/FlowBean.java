package com.aoks.budget.control.bean;

import com.aoks.budget.model.Flow;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class FlowBean implements GenericBean<Flow> {
	
	private static final long serialVersionUID = 1L;
	
	private long id;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Flow build(GenericFactory<Flow> factory) {
		return null;
	}

	@Override
	public Flow getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericBean<Flow> load(Flow model) {
		// TODO Auto-generated method stub
		return null;
	}

}
