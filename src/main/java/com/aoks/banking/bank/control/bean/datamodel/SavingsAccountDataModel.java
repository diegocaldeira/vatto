package com.aoks.banking.bank.control.bean.datamodel;

import java.util.List;

import com.aoks.banking.operation.control.bean.SavingsAccountBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class SavingsAccountDataModel extends GenericDataModel<SavingsAccountBean>{
	
	public SavingsAccountDataModel(List<SavingsAccountBean> beans){
		super(beans);
	}

}
