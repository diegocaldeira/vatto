package com.aoks.enterprise.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class IndividualPartnerManager extends AbstractManager<IndividualPartner> {

	private static final long serialVersionUID = 1L;
	
	public List<IndividualPartner> findByType(String partnerType){
		return wrapper.getList(IndividualPartner.class, "type", partnerType);
	}
	
	@Override
	public Class<IndividualPartner> getModelClazz() {
		return IndividualPartner.class;
	}

}
