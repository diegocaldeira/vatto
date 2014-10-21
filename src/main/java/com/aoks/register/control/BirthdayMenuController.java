package com.aoks.register.control;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("birthdayMenuController")
@SessionScoped
public class BirthdayMenuController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int month;
	
	@PostConstruct
	public void init(){
		configureMonth();
	}
	
	public void configureMonth(){
		Calendar c = Calendar.getInstance();
		month = c.get(Calendar.MONTH);
	}
	
	
	public Boolean isLoaded(int month) {
		return (this.month == 0 ? false : (month == 0 ? false : this.month == month));
	}
	public void load(int month) {
		this.month = month;
	}


	public int getMonth() { return month; }
	public void setMonth(int month) { this.month = month; }
}
