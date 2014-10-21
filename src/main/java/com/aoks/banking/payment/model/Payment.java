package com.aoks.banking.payment.model;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class Payment {

	
	// seu numero
	private String paymentCode;
	
	private Calendar date;
	
	private String currencyType;
	private String currencyQuantity;
	
	private BigDecimal value;
	
	// nosso numero
	private String paymentBankCode;
	
	private Calendar realDate;
	private BigDecimal realValue;
	
	
	// extra 
	
	private Calendar maturityDate;
	private BigDecimal documentValue;
	
	
	// 
	
}
