package com.aoks.budget.control.bean.factory;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.aoks.budget.control.bean.FlowItemBean;
import com.aoks.budget.control.bean.dataModel.FlowItemInstalmentsDataModel;
import com.aoks.budget.control.bean.dataModel.FlowItemRecurrentDataModel;
import com.aoks.budget.control.bean.dataModel.FlowItemTransferDataModel;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 *
 */
@RequestScoped
public class FlowItemDataModelFactory {
	
	/**
	 * 
	 * @param flowItemType
	 * @param beans
	 * @return
	 */
	public GenericDataModel<FlowItemBean> loadByType(String flowItemType, List<FlowItemBean> beans){
		switch(flowItemType){
			case "RECURRENT"	 : return new FlowItemRecurrentDataModel(beans);
			case "INSTALMENTS"	 : return new FlowItemInstalmentsDataModel(beans);
			case "TRANSFER"  	 : return new FlowItemTransferDataModel(beans);
		}
		return null;
	}

}
