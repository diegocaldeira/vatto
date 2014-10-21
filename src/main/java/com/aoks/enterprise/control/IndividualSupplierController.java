package com.aoks.enterprise.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.enterprise.control.bean.IndividualSupplierBean;
import com.aoks.enterprise.model.entities.IndividualSupplier;
import com.aoks.enterprise.service.IndividualSupplierManager;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

@Named("individualSupplierController")
@SessionScoped
public class IndividualSupplierController extends AbstractController<IndividualSupplier, IndividualSupplierBean> {

	private static final long serialVersionUID = 1L;

	@Inject
	private IndividualSupplierManager manager;

	@Override
	public AbstractManager<IndividualSupplier> getManager() {
		return manager;
	}

	@Override
	public Class<IndividualSupplierBean> getBeanClazz() {
		return IndividualSupplierBean.class;
	}

	@Override
	public GenericFactory<IndividualSupplier> getFactory() {
		return null;
	}


	@Override
	public GenericDataModel<IndividualSupplierBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
