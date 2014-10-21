package com.aoks.banking.bank.control.bean;

import com.aoks.banking.bank.model.Bank;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class BankBean implements GenericBean<Bank>, Comparable<BankBean>{

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private Bank model;
	
	private String code;
	private String name;
	private String description;
	
	private AuditEntity auditEntity;

	public String getCode() 							{ return code; }
	public void setCode(String code)	 				{ this.code = code; }

	public String getName() 							{ return name; }
	public void setName(String name) 					{ this.name = name; }
	
	public String getDescription() 						{ return description; }
	public void setDescription(String description)		{ this.description = description; }
	
	public AuditEntity getAuditEntity() 				{ return auditEntity; }
	public void setAuditEntity(AuditEntity auditEntity) { this.auditEntity = auditEntity; }
	
	
	@Override
	public long getId() {
		return id;
	}
	@Override
	public Bank build(GenericFactory<Bank> factory) {
		
		if (model == null)
			model = new Bank();
		
		model.setId(id);
		model.setCode(code);
		model.setName(name);
		model.setDescription(description);
		
		return model;
	}
	@Override
	public Bank getModel() {
		return model;
	}
	@Override
	public BankBean load(Bank model) {
		
		if (model == null)
			throw new IllegalArgumentException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getName();
		this.code = model.getCode();
		this.description = model.getDescription();
		
		if(model.getAuditEntity() != null)
			this.auditEntity = model.getAuditEntity();
		
		return this;
	}
	
	@Override
	public int compareTo(BankBean _bean) {
		return code.compareTo(_bean.code);
	}
}
