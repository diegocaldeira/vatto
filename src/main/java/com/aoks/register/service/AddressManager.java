package com.aoks.register.service;

import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.register.model.Address;
import com.aoks.register.model.Register;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class AddressManager extends AbstractManager<Address> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class<Address> getModelClazz() {
		return Address.class;
	}
	
	public BigInteger nextIdValid(){
		BigInteger id = (BigInteger) wrapper.get("register.registersequence", "ctable", "AddressSequence", "cnext");
		
		if(id == null){
			id = new BigInteger("1");
		}
		
		return id;
	}
	
	public AddressManager saveEntity(EnterpriseEntity register){
		wrapper.saveOrUpdate(register);
		return this;
	}
	public AddressManager saveRegister(Register register){
		wrapper.saveOrUpdate(register);
		return this;
	}

}
