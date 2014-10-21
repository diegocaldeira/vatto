package com.aoks.register.control.factory;

import javax.enterprise.context.RequestScoped;

import com.aoks.register.control.bean.AddressBean;
import com.aoks.register.model.Address;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

@RequestScoped
public class AddressFactory implements GenericFactory<Address> {

	private static final long serialVersionUID = 1L;

	@Override
	public Address create(GenericBean<Address> _bean) {
		
		AddressBean bean = (AddressBean)_bean;
		
		Address model = bean.getModel();
		if (model == null)
			model = new Address();
		
		model.setCity(bean.getCity());
		model.setCompletion(bean.getCompletion());
		model.setNeighborhood(bean.getNeighborhood());
		model.setNumber(bean.getNumber());
		model.setState(bean.getState());
		model.setStreet(bean.getStreet());
		model.setZipCode(bean.getZipCode());
		model.setAddressType(bean.getAddressType());
		
//		List<CategoryBean> categories = bean.getCategories();
//		for (CategoryBean categoryBean : categories) {
//			model.getCategorizedHelper().addCategory(categoryBean.getModel());
//		}
		
		return model;
	}
	

}
