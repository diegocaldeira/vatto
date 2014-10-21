package com.aoks.enterprise.control.bean.datamodel;

import java.util.List;

import com.aoks.enterprise.control.bean.CompanyBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class CompanyDataModel extends GenericDataModel<CompanyBean>{

	public CompanyDataModel(List<CompanyBean> beans){
		super(beans);
	}
	
}
