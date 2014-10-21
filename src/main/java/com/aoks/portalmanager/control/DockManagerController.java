package com.aoks.portalmanager.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.renderkit.CoreRenderer;

import com.aoks.portalmanager.event.ApplicationLoaded;
import com.aoks.portalmanager.event.ApplicationLoadingHandler;
import com.aoks.portalmanager.model.PortalApplication;
import com.aoks.portalmanager.model.PortalApplications;

@Named("dockController")
@SessionScoped
public class DockManagerController implements Serializable { 

	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LoggerFactory.getLogger(DockManagerController.class);
	
	private String name = "default";
	
//	@Inject
//	@PortalApplications
	private Set<PortalApplication> applications;
	
//	@Inject
//	@ApplicationLoaded
	private ApplicationLoadingHandler applicationLoadingHandler;

	
	public MenuModel getModel(){
		
		MenuModel model = new DefaultMenuModel();
//		logger.debug("DockManager: Loading model.");
		for (PortalApplication a : applications) {
//			logger.debug("DockManager: Adding applicatiom '{}' ", a.getName());
			model.addElement(createMenuItem(a.getDescription(), a.getIcon(), "applicationContainer", a.getName()));
		}
		
		return model;
	}
	
	private MenuItem createMenuItem(String value, String icon, String update, String name){
		
//		MenuItem item = new MenuItem();
//		item.setValue(value);
//		item.setIcon(icon);
//		item.setUpdate("application-container");
//		
//		FacesContext context = FacesContext.getCurrentInstance();
//		MethodExpression methodExpression = context.getApplication().getExpressionFactory()
//			.createMethodExpression(context.getELContext(), "#{dockController.load('" + name + "')}", null, new Class<?>[]{String.class});
//
//		item.addActionListener(new MethodExpressionActionListener(methodExpression));
//		
		return null;
		
	}
	
	public String navigate() throws IOException {
		
		if (name != null)
			return name;
		
		return name;
	}
	
	public String appAddress(){
		String address = "/" + name + ".xhtml";
		return address;
	}
	
	public boolean isReady(){
		return true;
	}
	
	public boolean isApp(String appName){
		return (name != null ? name.equalsIgnoreCase(appName) : false);
	}
	
	public void load(String toLoad){
		name = toLoad;
		for (PortalApplication application : applications) {
			if (application.getName().equals(name))
				applicationLoadingHandler.handleApplicationLoading(application);
		}
	}
	
	public String getPage() {
		return name;
	}

	public void setPage(String page) {
		this.name = page;
	}
	
	@PostConstruct
	void init(){
	}

}

class MockRenderer extends CoreRenderer {
	
	public void renderChild(FacesContext context, UIComponent child){
		try {
			super.renderChild(context, child);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

