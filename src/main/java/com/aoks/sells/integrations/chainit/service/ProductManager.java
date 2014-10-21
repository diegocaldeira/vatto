package com.aoks.sells.integrations.chainit.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.sells.integrations.chainit.model.Product;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class ProductManager extends AbstractManager<Product>{

	private static final long serialVersionUID = 1L;
	

	@Override
	public Class<Product> getModelClazz() {
		return Product.class;
	}
	
}
