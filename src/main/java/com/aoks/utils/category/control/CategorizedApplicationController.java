package com.aoks.utils.category.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.utils.category.control.bean.CategorizedApplicationBean;
import com.aoks.utils.category.model.CategorizedApplication;
import com.aoks.utils.category.service.CategorizedApplicationManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;


@Named("categorizedApplicationController")
@SessionScoped
public class CategorizedApplicationController extends AbstractController<CategorizedApplication, CategorizedApplicationBean>{

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryDimensionController dimensionController;
	
	@Inject
	private CategorizedApplicationManager manager;
	
	
	public void loadByName(String name){
		
		CategorizedApplicationManager manager = (CategorizedApplicationManager) getManager();
		CategorizedApplication categorized = manager.loadByName(name);

		if (categorized == null){
			bean = new CategorizedApplicationBean();
			bean.setName(name);
			save();
		} else {
			bean = new CategorizedApplicationBean().load(categorized);
		}
		
	}
	
	public CategoryDimensionController getDimensionController() {
		if (dimensionController.getCategorizedApplication() == null){
			dimensionController.setCategorizedApplication(bean);
			dimensionController.init();
		}
		return dimensionController;
	}
	public void setDimensionController(CategoryDimensionController dimensionController) {
		this.dimensionController = dimensionController;
	}

	@Override
	public GenericFactory<CategorizedApplication> getFactory() {
		return null;
	}
	@Override
	public AbstractManager<CategorizedApplication> getManager() {
		return manager;
	}
	@Override
	public Class<CategorizedApplicationBean> getBeanClazz() {
		return CategorizedApplicationBean.class;
	}

	@Override
	public GenericDataModel<CategorizedApplicationBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
