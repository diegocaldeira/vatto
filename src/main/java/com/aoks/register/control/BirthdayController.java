package com.aoks.register.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aoks.enterprise.model.entities.IndividualPartner;
import com.aoks.register.control.bean.BirthdayBean;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.model.IndividualRegister;
import com.aoks.register.service.BirthdayManager;
import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.AbstractController;
import com.aoks.utils.webmvc.AbstractManager;
import com.aoks.utils.webmvc.GenericDataModel;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * Performs operations in the interface layer .
 * 
 * @author Diego Pereira
 * 
 */
@Named("birthdayController")
@SessionScoped
public class BirthdayController extends AbstractController<Category, CategoryBean>{
	
	private static final long serialVersionUID = 1L;

	@Inject private BirthdayManager birthdayManager;

	private List<BirthdayBean> birthdaysMonth; 

	public void loadBirthdays() {
		birthdaysMonth = new ArrayList<BirthdayBean>();
		
		List<IndividualPartner> individualPartners = birthdayManager.buildIndividualBirthdays();
		for (IndividualPartner individualPartner : individualPartners) {
			IndividualRegisterBean register = (IndividualRegisterBean) new IndividualRegisterBean().load((IndividualRegister) individualPartner.getBehavior().getRegister());

			BirthdayBean bean = new BirthdayBean();
			bean.setName(register.getFirstName() + " " + register.getLastName());
			bean.setAge(estimatedAge(register.getBirth()));
			bean.setDate(extractBirth(register.getBirth()));
			bean.setCategory(individualPartner.getType().name());
			
			birthdaysMonth.add(bean);
		}

	}
	
	
	private String extractBirth(Calendar calendar) {
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		month++;
		
		String birth = day + "/" + month;

		return birth;
	}


	public int estimatedAge(Calendar dataNasc){
		Calendar dateOfBirth = dataNasc;
		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}

		return age;
	}
	
	
	public List<BirthdayBean> getBirthdaysMonth() { return birthdaysMonth; }
	public BirthdayManager getBirthdayManager() { return birthdayManager; }


	@Override public AbstractManager<Category> getManager() 			{ return null; }
	@Override public GenericFactory<Category> getFactory() 				{ return null; }
	@Override public Class<CategoryBean> getBeanClazz() 				{ return null; }


	@Override
	public GenericDataModel<CategoryBean> getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
