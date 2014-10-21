package com.aoks.utils.webmvc;

import java.io.Serializable;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 * @param <T>
 */
public interface GenericBean<T extends GenericModel> extends Serializable {

	long getId();
	
	GenericBean<T> load(T model);
	
	T build(GenericFactory<T> factory);
	T getModel();
	
}
