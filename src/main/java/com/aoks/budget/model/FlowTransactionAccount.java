package com.aoks.budget.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.aoks.banking.account.model.Account;
import com.aoks.banking.account.model.Entry;
import com.aoks.banking.operation.model.OperationEntry;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Embeddable
public class FlowTransactionAccount implements Account {

	@OneToMany(targetEntity=OperationEntry.class, fetch=FetchType.EAGER)
	@JoinTable(name="FlowTransactionAccount", schema="budget",
			joinColumns=@JoinColumn(name="cTransaction"),
			inverseJoinColumns=@JoinColumn(name="cEntry"))
	private Set<Entry> entries = new HashSet<Entry>();
	
	@Column(name="cName")
	private String name;
	
	
	public FlowTransactionAccount() {
		entries = new HashSet<Entry>();
	}
	
	
	@Override
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	@Override
	public BigDecimal getBalance() {
		
		BigDecimal balance = new BigDecimal(0);
		
		for (Entry entry : entries) {
			if (entry.isCredit())
				balance = balance.add(entry.getAmount());
			else if (entry.isDebit())
				balance = balance.subtract(entry.getAmount());
		}
		
		return balance;
	}

	@Override
	public List<Entry> getEntries() {
		return new ArrayList<Entry>(entries);
	}

	@Override
	public void setEntries(List<Entry> entries) {
		this.entries = new HashSet<Entry>(entries);
	}

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
