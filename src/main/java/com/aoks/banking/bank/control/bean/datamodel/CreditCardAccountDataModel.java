package com.aoks.banking.bank.control.bean.datamodel;

import java.util.List;

import com.aoks.banking.operation.control.bean.CreditCardAccountBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class CreditCardAccountDataModel extends GenericDataModel<CreditCardAccountBean>{
	
	public CreditCardAccountDataModel(List<CreditCardAccountBean> beans){
		super(beans);
	}

}
