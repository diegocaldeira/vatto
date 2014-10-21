package com.dp.app.menu;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aoks.utils.category.control.CategorizedApplicationController;
import com.aoks.utils.webmvc.MenuController;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("applicationMenuController")
@SessionScoped
public class ApplicationMenuController extends MenuController implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(ApplicationMenuController.class);
	private static final long serialVersionUID = 1L;

	private String module;
	private String name;
	
	private Boolean create;
	private Boolean view;
	
	@Inject
	private CategorizedApplicationController categorizedApplicationController;

	@PostConstruct
	void init() {
		categorizedApplicationController.loadByName("erpApplication");
	}
	
	public ApplicationMenuController(){
		setModule("DASHBOARD");
	}
	
	public ApplicationMenuController(String loadModule, String name){
		setModule(loadModule);
		setName(name);
	} 
	
	public Boolean isModuleLoaded(String module) { 
		return (this.getModule() == null ? false : (module == null ? false : this.getModule().equals(module))); 
	}
	public void loadModule(String module) { 
		logger.info(" Loading '{}'... ", module);
		this.module = module; 
	}
	

	public String getModule()				{ return module;}
	public void setModule(String module) 	{ this.module = module; }

	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

	public Boolean getCreate() 				{ return create; }
	public void setCreate(Boolean create) 	{ this.create = create; }
	
	public Boolean getView() 				{ return view; }
	public void setView(Boolean view) 		{ this.view = view; }
	
}
