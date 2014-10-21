package com.aoks.budget.model;

import java.util.List;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public interface TransactionAlgorithm {

	List<FlowTransaction> generateTransactions(FlowItem flowaccount);
	
}
