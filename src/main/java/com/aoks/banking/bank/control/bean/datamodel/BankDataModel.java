package com.aoks.banking.bank.control.bean.datamodel;

import java.util.List;

import com.aoks.banking.bank.control.bean.BankBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class BankDataModel extends GenericDataModel<BankBean>{
	
	public BankDataModel(List<BankBean> beans){
		super(beans);
	}

}
