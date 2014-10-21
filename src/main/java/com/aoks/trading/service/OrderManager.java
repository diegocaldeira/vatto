package com.aoks.trading.service;

import javax.enterprise.context.RequestScoped;

import com.aoks.trading.model.Order;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class OrderManager extends AbstractManager<Order>  {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Order> getModelClazz() {
		return Order.class;
	}

}
