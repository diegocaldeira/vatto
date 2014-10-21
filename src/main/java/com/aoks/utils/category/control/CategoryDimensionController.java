package com.aoks.utils.category.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.utils.category.control.bean.CategorizedApplicationBean;
import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.control.bean.CategoryDimensionBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.category.model.CategoryDimension;
import com.aoks.utils.category.service.CategoryDimensionManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("categoryDimensionController")
@SessionScoped
public class CategoryDimensionController extends AbstractController<CategoryDimension, CategoryDimensionBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private CategoryDimensionManager manager;
	private CategorizedApplicationBean categorizedApplication;
	
	
	public List<CategoryBean> getCategoriesFromDimension(String dimension){
		List<CategoryBean> categories = new ArrayList<CategoryBean>();
		
		// TODO this should only work within a categorized application
		CategoryDimension d = manager.loadByName(dimension);
		if (d == null){
			return categories;
		}
		
		Set<Category> categoriesDimension = d.getCategories();
		for (Category category : categoriesDimension) {
			categories.add(new CategoryBean().load(category));
		}
		
		return categories;
	}
	
	public List<CategoryDimensionBean> getDimensionsAC(String query){
		
		List<CategoryDimensionBean> suggestions = new ArrayList<CategoryDimensionBean>();  
        
        for(CategoryDimensionBean b : beans) {  
            if(b.getName().startsWith(query))  
                suggestions.add(b);  
        }  
          
        return suggestions;
		
	}
	
	@Override
	public void doPreSave() {
		bean.addCategorizedApplication(categorizedApplication);
	}
	
	
	public void addDimension(){
		
		CategoryDimensionBean newbean = new CategoryDimensionBean();
		newbean.addCategorizedApplication(categorizedApplication);
		beans.add(newbean);
		
	}
//	public void saveDimension(RowEditEvent event){
//		
//		CategoryDimensionBean object = (CategoryDimensionBean) event.getObject();
//		if (object != null){
//			bean = object;
//			save();
//		}
//		
//	}
	
	
	public void setCategorizedApplication(CategorizedApplicationBean categorizedApplication) { this.categorizedApplication = categorizedApplication; }
	public CategorizedApplicationBean getCategorizedApplication() { return categorizedApplication; }
	
	@PostConstruct
	void init(){
		if (categorizedApplication != null)
			beans = categorizedApplication.getDimensions();
	}

	
	@Override public AbstractManager<CategoryDimension> getManager()			{ return manager; }
	@Override public GenericFactory<CategoryDimension> getFactory()				{ return null; }
	@Override public Class<CategoryDimensionBean> getBeanClazz()				{ return CategoryDimensionBean.class; }

	@Override
	public GenericDataModel<CategoryDimensionBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
