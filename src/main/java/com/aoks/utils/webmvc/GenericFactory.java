package com.aoks.utils.webmvc;

import java.io.Serializable;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 * @param <T>
 */
public interface GenericFactory<T extends GenericModel> extends Serializable {

	T create(GenericBean<T> bean);
	
}
