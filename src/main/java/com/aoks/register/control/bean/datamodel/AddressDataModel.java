package com.aoks.register.control.bean.datamodel;

import java.util.List;

import com.aoks.register.control.bean.AddressBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 */
public class AddressDataModel extends GenericDataModel<AddressBean>{
	
	public AddressDataModel(List<AddressBean> beans){
		super(beans);
	}

}
