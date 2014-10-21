package com.aoks.utils.category.control.bean.datamodel;

import java.util.List;

import com.aoks.sells.integrations.chainit.controller.bean.ProductBean;
import com.aoks.utils.webmvc.GenericDataModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class ProductDataModel extends GenericDataModel<ProductBean>{

	public ProductDataModel(List<ProductBean> beans){
		super(beans);
	}

}
