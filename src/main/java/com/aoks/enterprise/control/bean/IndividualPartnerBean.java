package com.aoks.enterprise.control.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.enterprise.model.entities.PartnerType;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.bean.RegisterBean;
import com.aoks.register.model.IndividualRegister;
import com.aoks.utils.webmvc.GenericFactory;
import com.restfb.types.User;

public class IndividualPartnerBean extends PartnerBean<IndividualPartner> implements Comparable<IndividualPartnerBean>{

	private static final long serialVersionUID = 1L;
	
	private String type;
	private IndividualPartner model;
	
	public IndividualPartnerBean() {
		super(new IndividualRegisterBean());
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public IndividualPartner build(GenericFactory<IndividualPartner> factory) {
		return factory.create(this);
	}

	@Override
	public IndividualPartner getModel() {
		return model;
	}
	public void setModel(IndividualPartner model) {
		this.model = model;
	}
	

	@Override
	public IndividualPartnerBean load(IndividualPartner model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getBehavior().getName();
		if (model.getType() != null)
			this.type = model.getType().name();
		
		this.register = (RegisterBean<?>) new IndividualRegisterBean().load((IndividualRegister) model.getBehavior().getRegister());
		
		return this;
	}
	
	public IndividualPartnerBean load(User user){
		
		if(user == null)
			throw new IllegalStateException();
		
		IndividualPartnerBean bean = new IndividualPartnerBean();
		IndividualRegisterBean registerBean = new IndividualRegisterBean();
		
		Calendar birth = new GregorianCalendar();
		if(user.getBirthdayAsDate() != null){
			birth.setTime(user.getBirthdayAsDate());
		}
		registerBean.setBirth(birth);
		registerBean.setName(user.getName());
		registerBean.setFirstName(user.getFirstName());
		registerBean.setLastName(user.getLastName());
		registerBean.setMiddleName(user.getMiddleName());
		registerBean.setNaturality(user.getHometownName());
		registerBean.setMaritalStatus(user.getRelationshipStatus());
		registerBean.setEmail(user.getEmail());
		registerBean.setFacebook(user.getUsername());
		
		if(user.getGender() != null){
			registerBean.setGenre(user.getGender().toUpperCase());
		}
		if(user.getRelationshipStatus() != null){
			registerBean.setMaritalStatus(user.getRelationshipStatus().toUpperCase());
		}

		bean.setRegister(registerBean);
		bean.setType(PartnerType.Simples.name());
			
		return bean;
	}

	@Override
	public int compareTo(IndividualPartnerBean _bean) {
		return (((IndividualRegisterBean) getRegister()).getName().compareTo((((IndividualRegisterBean) _bean.register).getName())));
	}

}
