package com.aoks.banking.bank.control.bean.datamodel;

import java.util.List;

import com.aoks.banking.operation.control.bean.CheckingAccountBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CheckingAccountDataModel extends GenericDataModel<CheckingAccountBean>{
	
	public CheckingAccountDataModel(List<CheckingAccountBean> beans){
		super(beans);
	}

}
