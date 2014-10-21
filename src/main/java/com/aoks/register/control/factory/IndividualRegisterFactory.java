package com.aoks.register.control.factory;

import java.util.Set;

import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.bean.RegistrationBean;
import com.aoks.register.model.GenreCategory;
import com.aoks.register.model.IndividualRegister;
import com.aoks.register.model.MaritalStatusCategory;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 *
 * @author Diego Pereira
 *
 */
public class IndividualRegisterFactory implements GenericFactory<IndividualRegister> {

	private static final long serialVersionUID = 1L;
	
	// NAO CHAMAR ESSE METODO! NAO ESTA PRONTO! USAR O BUILD DO BEAN!!!
	@Override
	public IndividualRegister create(GenericBean<IndividualRegister> _bean) {

		IndividualRegisterBean bean = (IndividualRegisterBean) _bean;
		IndividualRegister model = bean.getModel();
		
		if (model == null)
			model = new IndividualRegister();

		bean.setModel(model);
		
		Set<RegistrationBean> registrations = bean.getRegistrations();
		for (RegistrationBean registrationBean : registrations) {
			model.addRegistration(registrationBean.getKey(), registrationBean.getValue());
		}
		
		model.setBirth(bean.getBirth());
		model.setName(bean.getName());
		model.setFatherName(bean.getFatherName());
		model.setFirstName(bean.getFirstName());
		try {
			model.setGenreCategory(GenreCategory.valueOf(bean.getGenre()));	
		} catch (Exception e) {}
		
		model.setLastName(bean.getLastName());
		
		try {
			model.setMaritalStatus(MaritalStatusCategory.valueOf(bean.getMaritalStatus()));
		} catch (Exception e) {}
		
		model.setMiddleName(bean.getMiddleName());
		model.setMotherName(bean.getMotherName());
		model.setNationality(bean.getNationality());
		model.setNaturality(bean.getNaturality());
		model.setPhone(bean.getPhone().build(null));
		model.setEmail(bean.getEmail());
		model.setFacebook(bean.getFacebook());
		model.setTwitter(bean.getTwitter());
		
		return model;
	}

}
