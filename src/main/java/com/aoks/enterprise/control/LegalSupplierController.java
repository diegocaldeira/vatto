package com.aoks.enterprise.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.enterprise.control.bean.LegalSupplierBean;
import com.aoks.enterprise.model.entities.LegalSupplier;
import com.aoks.enterprise.service.LegalSupplierManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

@Named("legalSupplierController")
@SessionScoped
public class LegalSupplierController extends AbstractController<LegalSupplier, LegalSupplierBean> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LegalSupplierManager manager;
	
	@Override
	public AbstractManager<LegalSupplier> getManager() {
		return manager;
	}

	@Override
	public Class<LegalSupplierBean> getBeanClazz() {
		return LegalSupplierBean.class;
	}

	@Override
	public GenericFactory<LegalSupplier> getFactory() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public GenericDataModel<LegalSupplierBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
