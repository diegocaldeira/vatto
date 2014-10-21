package com.aoks.banking;

import java.io.Serializable;

//@Startup
//@Singleton
//@DependsOn("PortalManagerMain")
public class BankingMain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	@PostConstruct
	void prepare(){
		
		Thread.currentThread().getContextClassLoader().getResource("anyBanking.xml");
		
	}
	
}