package com.aoks.security.control;

import java.io.File;

import javax.ejb.Local;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Local
public interface LoaderService {

	public void read(File file);
	public void read(String content);
	
}
