package com.aoks.banking.bank.control.bean.datamodel;

import java.util.List;

import com.aoks.banking.operation.control.bean.CashAccountBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CashAccountDataModel extends GenericDataModel<CashAccountBean>{
	
	public CashAccountDataModel(List<CashAccountBean> beans){
		super(beans);
	}

}
