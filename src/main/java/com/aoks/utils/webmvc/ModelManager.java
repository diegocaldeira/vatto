package com.aoks.utils.webmvc;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Diego Pereira
 *
 * @param <T>
 */
public interface ModelManager<T extends GenericModel> extends Serializable {
	
	ModelManager<T> load(T t) throws Exception;
	ModelManager<T> load(long id) throws Exception;
	ModelManager<T> remove() throws Exception;
	ModelManager<T> save() throws Exception;
	ModelManager<T> create();
	
	
	List<T> list();
	List<T> list(int maxresults);
	
	T getModel();
	
	T createModel();
	
	Class<T> getModelClazz();
	
	void verifyState();
	
}
