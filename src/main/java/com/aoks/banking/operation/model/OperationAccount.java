package com.aoks.banking.operation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.aoks.banking.account.model.Account;
import com.aoks.banking.account.model.Entry;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class OperationAccount implements Account, GenericModel{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BankingSequence")
	@TableGenerator(name="BankingSequence", table="BankingSequence", schema="banking", 
			pkColumnName="cTable", pkColumnValue="OperationSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	protected long id;
	
	/**
	 * A user-friendly description for the account.
	 */
	@Column(name="cDescription")
	protected String description;

	/**
	 * Set of entries
	 */
	@OneToMany(targetEntity=OperationEntry.class, fetch=FetchType.EAGER, mappedBy="account")
	protected Set<Entry> entries = new HashSet<Entry>();
	
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public long getId() 									{ return id; }
	@Override public void setId(long id) 							{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	public String getDescription() 									{ return description;	}
	public void setDescription(String description) 					{ this.description = description; }
	
	
	@Override
	public BigDecimal getBalance() {
		
		BigDecimal ret = new BigDecimal(0);
		
		for (Entry entry : entries) {
			if (entry.isCredit())
				ret = ret.add(entry.getAmount());
			else if (entry.isCredit())
				ret = ret.subtract(entry.getAmount());
		}
		
		return ret;
	}
	
	
	@Override
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	@Override
	public List<Entry> getEntries() {
		return new ArrayList<Entry>(entries);
	}
	@Override
	public void setEntries(List<Entry> entries) {
		this.entries = new HashSet<Entry>(entries);
	}
	
	@Override public String getName() 			{ return description; }
	@Override public void setName(String name)	{ this.description = name; }
	
}


