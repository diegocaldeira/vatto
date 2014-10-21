package com.aoks.utils.category.control;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.control.bean.CategoryDimensionBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.category.service.CategoryManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("categoryController")
@SessionScoped
public class CategoryController extends AbstractController<Category, CategoryBean> {

	private static final long serialVersionUID = 1L;
	
	@Inject private CategoryManager manager;
	@Inject private transient PersistenceWrapper wrapper;
	
	private CategoryDimensionBean dimension;
	private CategoryBean parent;
	
	
	public CategoryDimensionBean getDimension() {
		return dimension;
	}
	public void setDimension(CategoryDimensionBean dimension) {
		this.dimension = dimension;
	}
	public boolean hasDimension(){
		return (dimension != null);
	}
	public void handleDimensionSelect(){
		if (dimension != null){
			beans = dimension.getCategories();
		}
			
	}
	
	
	public void addCategory(){
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setDimension(dimension);
		categoryBean.setParent(parent);
		beans.add(categoryBean);
	}
//	public void saveCategory(RowEditEvent event){
//		
//		CategoryBean cat = (CategoryBean) event.getObject();
//		if (cat != null){
//			bean = cat;
//			save();
//		}
//		
//	}
	
	
	public CategoryBean getParent() {
		return parent;
	}
	public void setParent(CategoryBean parent) {
		this.parent = parent;
	}
	public boolean hasParent(){
		return (parent != null);
	}
	public void addChild(){
		
		Category category = new Category();
		Category parentModel = parent.getModel();
		
		parentModel.addChild(category);
		category.setParent(parentModel);
		
	}
//	public void saveChild(RowEditEvent event){
//		
//		Category category = null;
//		try {
//			CategoryBean bean = (CategoryBean) event.getObject();
//			category = parent.getModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (category != null)
//			wrapper.saveOrUpdate(category);
//		
//		wrapper.saveOrUpdate(parent.getModel());
//	}
	
	public List<CategoryBean> getCategoriesAC(String query){
		
		List<CategoryBean> suggestions = new ArrayList<CategoryBean>();  
        for(CategoryBean b : beans) {  
            if(b.getName().startsWith(query))  
                suggestions.add(b);  
        }  
        return suggestions;  
	}
	
	
	@Override
	public AbstractManager<Category> getManager() {
		return manager;
	}
	@Override
	public Class<CategoryBean> getBeanClazz() {
		return CategoryBean.class;
	}
	@Override
	public GenericFactory<Category> getFactory() {
		return null;
	}
	@Override
	public GenericDataModel<CategoryBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
