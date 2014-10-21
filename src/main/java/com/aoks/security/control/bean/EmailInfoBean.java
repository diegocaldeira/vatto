package com.aoks.security.control.bean;

import com.aoks.security.model.EmailInfo;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class EmailInfoBean implements GenericBean<EmailInfo> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String address;
	private boolean main;
	
	private EmailInfo model;
	
	@Override
	public long getId() {
		return id;
	}

	public String getAddress() 				{ return address; }
	public void setAddress(String address)	{ this.address = address; }
	
	public boolean isMain() 				{ return main; }
	public void setMain(boolean main) 		{ this.main = main; }
	
	@Override
	public EmailInfo build(GenericFactory<EmailInfo> factory) {
		if (model == null)
			model = new EmailInfo();
		
		model.setAddress(address);
		model.setMain(main);
		
		return model;
	}
	
	@Override
	public GenericBean<EmailInfo> load(EmailInfo model) {
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.address = model.getAddress();
		this.main = model.isMain();
		
		return this;
	}

	@Override public EmailInfo getModel() { return model; }
	
}
