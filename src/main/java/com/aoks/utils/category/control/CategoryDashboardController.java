package com.aoks.utils.category.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.category.model.CategoryDimension;
import com.aoks.utils.category.service.CategoryDimensionManager;
import com.aoks.utils.category.service.CategoryManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("categoryDashboardController")
@SessionScoped
public class CategoryDashboardController extends AbstractController<Category, CategoryBean>{

	private static final long serialVersionUID = 1L;

    @Inject private CategoryDimensionManager dimensionManager;
    @Inject private CategoryManager manager;
    
    List<CategoryBean> categories = new ArrayList<CategoryBean>();
	private Dashboard dashboard;  

	
    public void buildDashboard(String categoryType){
    	getCategoriesFromDimension(categoryType);
    	
    	FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
 
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");
 
        DashboardModel model = new DefaultDashboardModel();
    	for(CategoryBean category : categories){
    		DashboardColumn column = new DefaultDashboardColumn();
    		
        	Panel panel = buildPanel(application, fc, category);
            dashboard.getChildren().add(panel);

            column.addWidget(panel.getId());
           
            HtmlOutputText text = buildText(category);
            panel.getChildren().add(text);
            
            model.addColumn(column);
            dashboard.setModel(model);
        }
    }
    
    private HtmlOutputText buildText(CategoryBean category){
    	HtmlOutputText text = new HtmlOutputText();
        text.setValue(category.getDescription());
        
        return text;
    }
    
    private Panel buildPanel(Application application, FacesContext fc, CategoryBean category){
    	Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
        
    	panel.setId("category_" + String.valueOf(category.getId()));
        panel.setHeader(category.getName());
        panel.setClosable(false);
        panel.setToggleable(true);
        
        return panel;
    }
    
    
    public List<CategoryBean> getCategoriesFromDimension(String dimension){
    	categories.clear();
    	
    	// TODO this should only work within a categorized application
		CategoryDimension d = dimensionManager.loadByName(dimension);
		
		if (d == null){ return categories; }
		
		Set<Category> categoriesDimension = d.getCategories();
		for (Category category : categoriesDimension) {
			categories.add(new CategoryBean().load(category));
		}
		
		return categories;
	}
      
    
    public Dashboard getDashboard() { return dashboard; } 
    public void setDashboard(Dashboard dashboard) { this.dashboard = dashboard; }
 
    
	public List<CategoryBean> getCategories() { return categories; }
	public void setCategories(List<CategoryBean> categories) { this.categories = categories; }


	public AbstractManager<CategoryDimension> getDimensionManager() { return dimensionManager; }
	
	
	@Override public Class<CategoryBean> getBeanClazz() { return CategoryBean.class; }
	@Override public AbstractManager<Category> getManager() { return manager; }
	@Override public GenericFactory<Category> getFactory() { return null; }

	@Override
	public GenericDataModel<CategoryBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
