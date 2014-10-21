package com.aoks.utils.webmvc;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 * @param <M>
 * @param <V>
 */
public interface GenericFacade<M extends GenericModel, V extends GenericBean<M>> extends Serializable {
	
	List<M> list();
	List<V> listBeans();
	
	M add(M model);
	M add(V bean);

}
