package com.aoks.budget.model;

import java.util.Calendar;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public enum Periodicity {
	
	TWOYEAR(Calendar.YEAR, 2), YEAR(Calendar.YEAR, 1), 
	SEMESTER(Calendar.MONTH, 6), TRIMESTRER(Calendar.MONTH, 3), BIMESTER(Calendar.MONTH, 2), MONTH (Calendar.MONTH, 1), 
	TWOWEEK(Calendar.WEEK_OF_YEAR, 2), WEEK(Calendar.WEEK_OF_YEAR, 1), 
	DAY(Calendar.DAY_OF_MONTH, 1),
	NONE(Calendar.DAY_OF_MONTH, 1);

	private int unit;
	private int quantity;
	
	private Periodicity(int unit, int quantity) {
		this.unit = unit;
		this.quantity = quantity;
	}
	
	public int getUnit() {
		return unit;
	}
	public int getQuantity() {
		return quantity;
	}
	
}
