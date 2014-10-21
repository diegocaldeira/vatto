package com.aoks.register.control.factory;

import java.util.Set;

import javax.enterprise.context.RequestScoped;

import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.control.bean.RegistrationBean;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
@RequestScoped
public class LegalRegisterFactory implements GenericFactory<LegalRegister> {

	private static final long serialVersionUID = 1L;

	@Override
	public LegalRegister create(GenericBean<LegalRegister> _bean) {

		LegalRegisterBean bean = (LegalRegisterBean) _bean;
		LegalRegister model = bean.getModel();
		
		if (model == null)
			model = new LegalRegister();

		bean.setModel(model);
		model.setCompanyName(bean.getCompanyName());
		model.setFancyName(bean.getFancyName());
		model.setSite(bean.getSite());
		model.setTwitter(bean.getTwitter());
		model.setFacebook(bean.getFacebook());
		model.setAreaActuation(bean.getAreaActuation());
		
		model.setPhone(bean.getPhone().build(null));
		
		Set<RegistrationBean> registrations = bean.getRegistrations();
		for (RegistrationBean registrationBean : registrations) {
			model.addRegistration(registrationBean.getKey(), registrationBean.getValue());
		}
		
		return model;
	}

}
