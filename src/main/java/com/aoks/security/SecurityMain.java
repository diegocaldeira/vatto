package com.aoks.security;

import java.io.Serializable;

//@Startup
//@Singleton
//@DependsOn("PortalManagerMain")
public class SecurityMain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	@PostConstruct
	void prepare(){
		
		Thread.currentThread().getContextClassLoader().getResource("anySecurity.xml");
		
	}
}