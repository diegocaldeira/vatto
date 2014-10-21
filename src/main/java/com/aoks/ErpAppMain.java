package com.aoks;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aoks.portalmanager.control.PortalManagerMain;

@Startup
@Singleton
@DependsOn("PortalManagerMain")
public class ErpAppMain implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(ErpAppMain.class);

	private static final long serialVersionUID = 1L;


	@Inject PortalManagerMain manager;
	
	@PostConstruct
	void prepare() throws IOException {
		
		logger.info("Budget application starting!");

//		InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("erp-aokapp.xml");
//		
//		manager.loadAppStream(resource);
		
	}
	
}