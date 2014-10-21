package com.aoks.enterprise.control.bean.datamodel;

import java.util.List;

import com.aoks.enterprise.control.bean.PartyBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class PartyDataModel extends GenericDataModel<PartyBean>{

	public PartyDataModel(List<PartyBean> beans){ 
		super(beans); 
	}
	
}
