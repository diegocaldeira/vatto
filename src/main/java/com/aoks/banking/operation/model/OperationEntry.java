package com.aoks.banking.operation.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="Entry", schema="banking")
public class OperationEntry implements Entry, GenericModel{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BankingSequence")
	@TableGenerator(name="BankingSequence", table="BankingSequence", schema="banking", 
			pkColumnName="cTable", pkColumnValue="EntrySequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;

	@Column(name="cAmount")
	private BigDecimal amount;
	
	@Column(name="cCredit")
	private boolean credit;
	
	@Temporal(TemporalType.DATE)
	@Column(name="cOcurrence")
	private Calendar ocurrence;
	
	@Column(name="cDescription")
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=OperationAccount.class)
	@JoinColumn(name="cOperationAccount")
	private Account account;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public long getId()									{ return id; }
	@Override public void setId(long id)							{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	@Override public BigDecimal getAmount() 				 { return amount; }
	@Override public void setAmount(BigDecimal amount)		 { this.amount = amount; }

	@Override public boolean isDebit()						 { return (!credit); }
	@Override public void setDebit()						 { credit = false; }
	
	@Override public boolean isCredit() 					 { return credit; }
	@Override public void setCredit()						 { credit = true; }
	
	@Override public Account getAccount() 					 { return account; }
	@Override public void setAccount(Account account)		 { this.account = account; }

	@Override public String getDescription() 				 { return description; }
	@Override public void setDescription(String description) { this.description = description; }

	@Override public Calendar getOcurrence() 				 { return ocurrence; }
	@Override public void setOcurrence(Calendar when) 		 { ocurrence = when; }

}
