package com.aoks.enterprise.control.factory;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.aoks.enterprise.model.Accountability;
import com.aoks.enterprise.model.AccountabilityType;
import com.aoks.enterprise.model.EnterpriseBehavior;
import com.aoks.enterprise.service.AccountabilityTypeManager;
import com.aoks.utils.webmvc.PersistenceWrapper;

@RequestScoped
public class AccountabilityFactory {
	
	@Inject
	private AccountabilityTypeManager accManager;
	
	@Inject
	private PersistenceWrapper wrapper;
	
	public void handleAccountability(String type, EnterpriseBehavior parent, EnterpriseBehavior child){
		
		// ensure lazy properties are fetched
		parent = wrapper.loadLazyProperty(parent, "getAccountabilities");
		child = wrapper.loadLazyProperty(child, "getAccountabilities");
		
		// first, the type
		AccountabilityType accType = accManager.loadByName(type);
		if (accType == null){
			accType = new AccountabilityType(type);
			accManager.load(accType).save();
		}
		
		// then set the end the old parent
		List<Accountability> parentAccountabilities = child.getParentAccountabilities(accType);
		if (parentAccountabilities.size() > 1)
			throw new IllegalStateException("More than one parent in this enterprise model - this should never had happened!");

		if (parentAccountabilities.size() == 1){
			Accountability accountability = parentAccountabilities.get(0);
			accountability.end(Calendar.getInstance());
			wrapper.saveOrUpdate(accountability);
		}
		
		// create the new accountability
		Accountability acc = new Accountability.Builder(accType, parent, child).build();
		child.addAccountability(acc);
		parent.addAccountability(acc);
		
		wrapper.saveOrUpdate(acc);
		
		
	}
	
}
