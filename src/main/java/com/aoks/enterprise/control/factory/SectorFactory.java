package com.aoks.enterprise.control.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.control.bean.SectorBean;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.model.entities.Department;
import com.aoks.enterprise.model.entities.Sector;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class SectorFactory implements GenericFactory<Sector> {

	private static final long serialVersionUID = 1L;

	@Inject private AccountabilityFactory accountabilityFactory;
	@Inject private PersistenceWrapper wrapper;
	
	@Override
	public Sector create(GenericBean<Sector> _bean) {

		SectorBean bean = (SectorBean)_bean;
		
		Sector model = bean.getModel();
		if (model == null) {
			model = new Sector();
			wrapper.saveOrUpdate(model);
		}
		
		model = (Sector)wrapper.loadLazyProperty(model.getBehavior(), "getParentAccountabilities").getEntity();
		
		model.getBehavior().setName(bean.getName());

		Department department = (bean.getDepartment() != null ? bean.getDepartment().getModel() : null);
		
		if (department != null){
			
			EnterpriseBehavior departmentBehavior = department.getBehavior();
			EnterpriseBehavior sectorBehavior = model.getBehavior();
			
			accountabilityFactory.handleAccountability("structureDimension", departmentBehavior, sectorBehavior);
			
		}
		
		return model;
	}

}
