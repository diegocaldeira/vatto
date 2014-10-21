package com.aoks.budget.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.aoks.utils.date.DateUtils;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class InstalmentsAlgorithm implements TransactionAlgorithm {

	@Override
	public List<FlowTransaction> generateTransactions(FlowItem item) {
		
		if (item == null)
			throw new IllegalArgumentException();
		
		List<FlowTransaction> ret = new ArrayList<FlowTransaction>();
		
		InstalmentsInfo info = (InstalmentsInfo) item.getInfo();
		
		Calendar start = info.getStart();
		int instalments = info.getInstalments();
		Periodicity periodicity = info.getPeriodicity();
		
		Calendar tmp = DateUtils.clone(start);
		
		int number = 0;
		while (number < instalments) {
			++number;
			
			FlowTransaction transaction = new FlowTransaction();
			transaction.setNumber(number);
			
			if (periodicity == Periodicity.MONTH)
				transaction.setOcurrence(DateUtils.getDayReferenceDate(tmp, info.getMaturityDay()));
			else 
				transaction.setOcurrence(DateUtils.clone(tmp));
			
			transaction.setAmount(item.getAmount().divide(new BigDecimal(instalments), 2, RoundingMode.UP));
			transaction.setAccount(item.getOrigin());
			transaction.setDescription(item.getDescription());
			
			transaction.setIdentity("" + periodicity.getUnit() + "_" + number);
			
			transaction.setStatus(FlowStatus.OPEN);
			transaction.setCredit(item.isCredit());
			
			tmp.add(periodicity.getUnit(), periodicity.getQuantity());
			ret.add(transaction);
		}
		
		
		return ret;
	}

}
