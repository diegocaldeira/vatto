package com.aoks.enterprise.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.aoks.enterprise.model.entities.LegalPartner;
import com.aoks.utils.webmvc.AbstractManager;

@RequestScoped
public class LegalPartnerManager extends AbstractManager<LegalPartner> {

	private static final long serialVersionUID = 1L;
	
	public List<LegalPartner> findByType(String partnerType){
		return wrapper.getList(LegalPartner.class, "type", partnerType);
	}
	
	@Override
	public Class<LegalPartner> getModelClazz() {
		return LegalPartner.class;
	}

}
