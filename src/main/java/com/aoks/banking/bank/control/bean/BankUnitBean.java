package com.aoks.banking.bank.control.bean;

import com.aoks.banking.bank.model.BankUnit;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class BankUnitBean implements GenericBean<BankUnit>{

	private static final long serialVersionUID = 1L;

	private long id;
	
	private BankUnit model;
	
	private String name;
	private String number;
	private String digit;
	
	@Override
	public BankUnit build(GenericFactory<BankUnit> factory) {
		
		if (model == null)
			model = new BankUnit();
		
		model.setId(id);
		model.setName(name);
		model.setNumber(number);
		model.setDigit(digit);

		return model;
	}
	
	@Override
	public BankUnit getModel() {
		return model;
	}

	@Override
	public GenericBean<BankUnit> load(BankUnit model) {
		if (model == null)
			throw new IllegalArgumentException();
		
		this.id = model.getId();
		this.setName(model.getName());
		this.setNumber(model.getNumber());
		this.setDigit(model.getDigit());
		
		return this;
	}

	
	@Override
	public long getId() { return id; }

	
	public String getDigit() {
		return digit;
	}
	public void setDigit(String digit) {
		this.digit = digit;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
