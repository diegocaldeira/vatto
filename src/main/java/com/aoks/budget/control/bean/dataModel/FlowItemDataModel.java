package com.aoks.budget.control.bean.dataModel;

import java.util.List;

import com.aoks.budget.control.bean.FlowItemBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class FlowItemDataModel extends GenericDataModel<FlowItemBean>  {

	public FlowItemDataModel(List<FlowItemBean> beans){
		super(beans);
	}
	
}
