package com.aoks.banking.account.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public interface Account {

	BigDecimal getBalance();
	
	void addEntry(Entry entry);
	List<Entry> getEntries();
	void setEntries(List<Entry> entries);
	
	String getName();
	void setName(String name);
	
}
