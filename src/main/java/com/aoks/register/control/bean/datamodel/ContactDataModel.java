package com.aoks.register.control.bean.datamodel;

import java.util.List;

import com.aoks.register.control.bean.ContactBean;
import com.aoks.utils.webmvc.GenericDataModel;


/**
 * 
 * @author Diego Pereira
 */
//@RequestScoped
public class ContactDataModel extends GenericDataModel<ContactBean>{

//	private static final long serialVersionUID = 1L;

	public ContactDataModel(){
		
	}
	
	public ContactDataModel(List<ContactBean> beans){
		super(beans);
	}

//	@Override public Class<ContactBean> getBeanClazz() { return ContactBean.class; }

}
