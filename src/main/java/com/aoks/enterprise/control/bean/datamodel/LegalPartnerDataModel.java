package com.aoks.enterprise.control.bean.datamodel;

import java.util.List;

import com.aoks.enterprise.control.bean.LegalPartnerBean;
import com.aoks.utils.webmvc.GenericDataModel;


/**
 * 
 * @author Diego Pereira
 */
public class LegalPartnerDataModel extends GenericDataModel<LegalPartnerBean>{
	
	public LegalPartnerDataModel(List<LegalPartnerBean> beans){
		super(beans);
	}

}
