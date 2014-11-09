package com.aoks.sales.integrations.chainit.controller;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jackson.map.ObjectMapper;

import com.aoks.sales.integrations.chainit.controller.bean.ProductBean;
import com.aoks.sales.integrations.chainit.controller.bean.ProductBeanList;
import com.aoks.sales.integrations.chainit.model.Product;
import com.aoks.sales.integrations.chainit.service.ProductManager;
import com.aoks.trading.control.bean.OrderBean;
import com.aoks.trading.service.OrderManager;
import com.aoks.utils.category.control.bean.datamodel.ProductDataModel;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.AbstractWebGateway;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * Performs operations in the interface layer .
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Named("productController")
@SessionScoped
public class ProductController extends AbstractController<Product, ProductBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private ProductManager manager;
	@Inject private OrderManager orderManager;

	private List<ProductBean> orderBeans;
	private OrderBean orderBean;
	
	private ProductDataModel dataModel;
	
	
	/**
	 * Carry the type of entities from the database and transforms into beans
	 */
	public void loadBeansByType(String partnerType){
		loadBeans();
		dataModel = new ProductDataModel(beans);
		
	}

	/**
	 * Load products beans
	 */
	private void loadBeans(){
		String json = new AbstractWebGateway().get("http://localhost:8888/_ah/api/product/v1/list/agtjaGFpbml0LXNjbXIUCxIHQ29tcGFueRiAgICAgIDgCQw");

		ObjectMapper mapper = new ObjectMapper();
		
		ProductBeanList productList = new ProductBeanList();
		try {
			productList = mapper.readValue(json, ProductBeanList.class);
			
			if(!productList.getProductList().isEmpty())
				beans = productList.getProductList();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override public GenericDataModel<ProductBean> getDataModel() 	{ return dataModel; }
	@Override public AbstractManager<Product> getManager() 			{ return manager; }
	@Override public GenericFactory<Product> getFactory() 			{ return null; }
	@Override public Class<ProductBean> getBeanClazz() 				{ return ProductBean.class; }
	public OrderManager getOrderManager() 							{ return orderManager; }
	
	
	public OrderBean getOrderBean() 				{ return orderBean = (orderBean == null) ? new OrderBean() : orderBean; }
	public void setOrderBean(OrderBean orderBean) 	{ this.orderBean = orderBean; }

	/**
	 * Remove product from invoice
	 * 
	 * @param product
	 */
	public void remove(ProductBean product){
		orderBean.getProducts().getProductList().remove(product);
	}
	

	/**
	 * Assign product to order  
	 * 
	 * @param event
	 */
	public void add(ActionEvent actionEvent) { 
		getOrderBean().getProducts().getProductList().add(bean);
	}  
	
	
	public List<ProductBean> getOrderBeans() 				{ return orderBeans; }
	public void setOrderBeans(List<ProductBean> orderBeans) { this.orderBeans = orderBeans; }

}
