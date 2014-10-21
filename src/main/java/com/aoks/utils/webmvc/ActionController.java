package com.aoks.utils.webmvc;

import java.io.Serializable;

/**
 * 
 * @author Diego Pereira
 *
 */
public interface ActionController extends Serializable {

	void save();
	void delete();
	void create();
	void clean();
	
	boolean has();
	
}
