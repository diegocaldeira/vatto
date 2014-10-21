package com.aoks.budget.control.bean.dataModel;

import java.util.List;

import com.aoks.budget.control.bean.FlowTransactionBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class FlowTransactionDataModel extends GenericDataModel<FlowTransactionBean>  {

	public FlowTransactionDataModel(List<FlowTransactionBean> beans){
		super(beans);
	}
	
}
