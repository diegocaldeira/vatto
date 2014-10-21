package com.aoks.register.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.enterprise.service.IndividualPartnerManager;

@RequestScoped
public class BirthdayManager{

	@Inject private IndividualPartnerManager individualPartnerManager;
	
	public List<IndividualPartner> buildIndividualBirthdays() { return individualPartnerManager.list(); }
	public IndividualPartnerManager getIndividualPartnerManager() { return individualPartnerManager; }

}
