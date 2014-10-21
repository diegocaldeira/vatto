package com.aoks.register.control.factory;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.aoks.register.control.bean.ContactBean;
import com.aoks.register.control.bean.EmailBean;
import com.aoks.register.control.bean.PhoneBean;
import com.aoks.register.model.Contact;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

@RequestScoped
public class ContactFactory implements GenericFactory<Contact> {

	private static final long serialVersionUID = 1L;

	@Override
	public Contact create(GenericBean<Contact> _bean) {
		
		ContactBean bean = (ContactBean)_bean;
		
		Contact model = bean.getModel();
		if (model == null)
			model = new Contact();
		
		model.setFirstName(bean.getFirstName());
		model.setLastName(bean.getLastName());
		model.setContactType(bean.getContactType());
		model.setFacebook(bean.getFacebook());
		model.setTwitter(bean.getTwitter());
		
		List<EmailBean> emails = bean.getEmails();
		for (EmailBean emailBean : emails) {
			model.addEmail(emailBean.build(null));
		}
		
		List<PhoneBean> phones = bean.getPhones();
		for (PhoneBean phoneBean : phones) {
			model.addPhone(phoneBean.build(null));
		}
		
//		List<CategoryBean> categories = bean.getCategories();
//		for (CategoryBean categoryBean : categories) {
//			model.getCategorizedHelper().addCategory(categoryBean.getModel());
//		}
		
		return model;
	}

}
