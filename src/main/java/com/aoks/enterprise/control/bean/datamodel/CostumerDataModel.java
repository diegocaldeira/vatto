package com.aoks.enterprise.control.bean.datamodel;

import java.util.List;

import com.aoks.enterprise.control.bean.CostumerBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CostumerDataModel extends GenericDataModel<CostumerBean>{

	public CostumerDataModel(List<CostumerBean> beans){ 
		super(beans); 
	}

}
