package com.dp.app.menu;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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

//	private static final Logger logger = LoggerFactory.getLogger(ApplicationMenuController.class);
	private static final long serialVersionUID = 1L;

	private String module;
	private String name;
	
	private Boolean create;
	private Boolean view;
	

	public Boolean isModuleLoaded(String module) { 
		return (this.getModule() == null ? false : (module == null ? false : this.getModule().equals(module))); 
	}
	public void loadModule(String module) { 
//		logger.info(" Loading module '{}'... ", module);
		this.module = module; 
	}
	
	
	public String getModule()				{ return module;}
	public String getName() 				{ return name; }
	public Boolean getCreate() 				{ return create; }
	public void setCreate(Boolean create) 	{ this.create = create; }
	
	public Boolean getView() 				{ return view; }
	public void setView(Boolean view) 		{ this.view = view; }
	
}
