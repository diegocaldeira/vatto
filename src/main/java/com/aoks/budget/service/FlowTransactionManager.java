package com.aoks.budget.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.aoks.budget.model.FlowTransaction;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class FlowTransactionManager extends AbstractManager<FlowTransaction> {
	
	private static final long serialVersionUID = 1L;
	
	Double incomePercentage = new Double(0);
	Double expensePercentage = new Double(0);
	
	Double totalCreditFuture = new Double(0);
	Double totalDebitFuture = new Double(0);
	
	Double totalCreditMonthly = new Double(0);
	Double totalDebitMonthly = new Double(0);
	
	Double totalCreditToday = new Double(0);
	Double totalDebitToday = new Double(0);
	
	
	public void getTotalPercentageTransactions() throws ParseException{
		List<Calendar> currentMonth = getRangeMonth(0);
		Calendar currentMonthStart = currentMonth.get(0);
		Calendar currentMonthEnd = currentMonth.get(1);

		List<BigDecimal> currentMonthIncomeAmountList  = listTransactionByOccurrence(currentMonthStart, currentMonthEnd, Boolean.TRUE);
		List<BigDecimal> currentMonthExpenseAmountList = listTransactionByOccurrence(currentMonthStart, currentMonthEnd, Boolean.FALSE);
		
		List<Calendar> lastMonth = getRangeMonth(-1);
		Calendar lastMonthStart = lastMonth.get(0);
		Calendar lastMonthEnd = lastMonth.get(1);
		
		List<BigDecimal> lastMonthIncomeAmountList  = listTransactionByOccurrence(lastMonthStart, lastMonthEnd, Boolean.TRUE);
		List<BigDecimal> lastMonthExpenseAmountList = listTransactionByOccurrence(lastMonthStart, lastMonthEnd, Boolean.FALSE);
		
		Double currentIncomeMonth = new Double(0);
		Double currentExpenseMonth = new Double(0);

		Double lastIncomeMonth = new Double(0);
		Double lastExpenseMonth = new Double(0);
		
		for(BigDecimal amount : currentMonthIncomeAmountList)	currentIncomeMonth  += amount.doubleValue();
		for(BigDecimal amount : currentMonthExpenseAmountList)	currentExpenseMonth += amount.doubleValue();
		for(BigDecimal amount : lastMonthIncomeAmountList)		lastIncomeMonth 	+= amount.doubleValue();
		for(BigDecimal amount : lastMonthExpenseAmountList)		lastExpenseMonth	+= amount.doubleValue();
		
		if(currentIncomeMonth.intValue() != 0 && lastIncomeMonth.intValue() != 0)
			incomePercentage = ((currentIncomeMonth / lastIncomeMonth) -1) * 100;
		if(currentIncomeMonth.intValue() > 0 && lastIncomeMonth.intValue() == 0)
			incomePercentage = 100D;
		
		if(currentExpenseMonth.intValue() != 0 && lastExpenseMonth.intValue() != 0)
			expensePercentage = ((currentExpenseMonth / lastExpenseMonth) -1) * 100;
		if(currentExpenseMonth.intValue() > 0 && lastExpenseMonth.intValue() == 0)
			expensePercentage = 100D;
	}
	
	
	public List<Calendar> getRangeMonth(Integer range) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, range);
		calendar.set(Calendar.DATE, 1);

		Calendar start = Calendar.getInstance();
		start.setTime(df.parse(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())));

		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		Calendar end = Calendar.getInstance();
		end.setTime(df.parse(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())));
		
		List<Calendar> calendars = new ArrayList<Calendar>();
		calendars.add(start);
		calendars.add(end);
		
		return calendars;
	}
	
	public void getTotalFutureTransactions() throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today  = Calendar.getInstance();
		today.setTime(df.parse(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
		
		List<BigDecimal> incomeAmountList  = listTransactionByOccurrence(today, Boolean.TRUE);
		List<BigDecimal> expenseAmountList = listTransactionByOccurrence(today, Boolean.FALSE);
		
		for(BigDecimal amount : incomeAmountList)
			totalCreditFuture+= amount.doubleValue();

		for(BigDecimal amount : expenseAmountList)
			totalDebitFuture += amount.doubleValue();
	}
	
	
	
	/**
	 * 
	 * @throws ParseException
	 */
	public void getTotalTransactionMonthly()throws ParseException{
		List<Calendar> currentMonth = getRangeMonth(0);

		Calendar start = currentMonth.get(0);
		Calendar end = currentMonth.get(1);

		List<BigDecimal> incomeAmountList  = listTransactionByOccurrence(start, end, Boolean.TRUE);
		List<BigDecimal> expenseAmountList = listTransactionByOccurrence(start, end, Boolean.FALSE);

		for(BigDecimal amount : incomeAmountList)
			totalCreditMonthly += amount.doubleValue();

		for(BigDecimal amount : expenseAmountList)
			totalDebitMonthly += amount.doubleValue();
	}
	
	
	/**
	 * @throws ParseException 
	 *  
	 */
	public void getTotalTransactionToday()throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today  = Calendar.getInstance();
		today.setTime(df.parse(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
		
		List<BigDecimal> incomeAmountList  = listTransactionByOccurrence(today, today, Boolean.TRUE);
		List<BigDecimal> expenseAmountList = listTransactionByOccurrence(today, today, Boolean.FALSE);

		for(BigDecimal amount : incomeAmountList)
			totalCreditToday += amount.doubleValue();

		for(BigDecimal amount : expenseAmountList)
			totalDebitToday += amount.doubleValue();
		
	}

	/**
	 * List transaction by occurrence.
	 *  
	 * @param ocurrence
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BigDecimal> listTransactionByOccurrence(Calendar start, Calendar end, Boolean isCredit){
		Query query = wrapper.getEm().createQuery("select x.amount from FlowTransaction x where x.credit = :isCredit and x.occurrence between :start and :end");
		query.setParameter("start", start, TemporalType.DATE);
		query.setParameter("end", end, TemporalType.DATE);
		query.setParameter("isCredit", isCredit);
		return query.getResultList();
	}
	
	
	/**
	 * List transaction by occurrence.
	 *  
	 * @param ocurrence
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BigDecimal> listTransactionByOccurrence(Calendar start, Boolean isCredit){
		Query query = wrapper.getEm().createQuery("select x.amount from FlowTransaction x where x.credit = :isCredit and x.occurrence >= :start");
		query.setParameter("start", start, TemporalType.DATE);
		query.setParameter("isCredit", isCredit);
		return query.getResultList();
	}

	
	public Double getIncomePercentage() 	{ return incomePercentage; }
	public Double getExpensePercentage()	{ return expensePercentage; }

	public Double getTotalDebitFuture() 	{ return totalDebitFuture;  }
	public Double getTotalCreditFuture()	{ return totalCreditFuture; }
	
	public Double getTotalDebitMonthly()  	{ return totalDebitMonthly;  }
	public Double getTotalCreditMonthly()	{ return totalCreditMonthly; }
	
	public Double getTotalDebitToday() 		{ return totalDebitToday;  }
	public Double getTotalCreditToday()		{ return totalCreditToday; }

	
	@Override public Class<FlowTransaction> getModelClazz() { return FlowTransaction.class; }

}
