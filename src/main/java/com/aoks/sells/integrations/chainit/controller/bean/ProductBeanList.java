package com.aoks.sells.integrations.chainit.controller.bean;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class ProductBeanList {

	@JsonProperty("products")
	private List<ProductBean> productList;


	public List<ProductBean> getProductList() 					{ return productList = (productList == null) ? new ArrayList<ProductBean>() : productList; }
	public void setProductList(List<ProductBean> productList)	{ this.productList = productList; }
	
}
