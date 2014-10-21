package com.aoks.budget.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.enterprise.model.entities.Party;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="FlowTransaction", schema="budget")
public class FlowTransaction implements GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BudgetSequence")
	@TableGenerator(name="BudgetSequence", table="BudgetSequence", schema="budget", 
			pkColumnName="cTable", pkColumnValue="FlowTransactionSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="cOccurrence")
	private Calendar occurrence;
	
	@Column(name="cIdentity")
	private String identity;
	
	@Column(name="cAmount")
	private BigDecimal amount;
	
	@Column(name="cNumber")
	private int number;
	
	@Column(name="cDescription")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="cAccount")
	private OperationAccount account;
	
	@ManyToOne
	@JoinColumn(name="cFlowItem")
	private FlowItem item;
	
	@Column(name="cCredit")
	private boolean credit;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cStatus")
	private FlowStatus status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cCategory")
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cSubCategory")
	private Category subCategory;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cPayee")
	private Party payee;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;

	@Embedded
	private FlowTransactionAccount txAccount;
	
	
	public FlowTransaction() {
		txAccount = new FlowTransactionAccount();
	}
	
	
	public boolean isIdenticalTo(FlowTransaction that){
		return (this.identity.equals(that.identity));
	}
	public void copy(FlowTransaction that){
		
		this.account = that.account;
		this.amount = that.amount;
		this.credit = that.credit;
		this.description = that.description;
		this.item = that.item;
		this.number = that.number;
		this.occurrence = that.occurrence;
	 	this.status = that.status;
		 
	}
	
	@Override public long getId() 									{ return id; }
	@Override public void setId(long id)							{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	public Category getCategory() 									{ return category; }
	public void setCategory(Category category)						{ this.category = category; }
	
	public Category getSubCategory() 								{ return subCategory; }
	public void setSubCategory(Category subCategory)				{ this.subCategory = subCategory; }
	
	public Party getPayee()											{ return payee; }
	public void setPayee(Party payee)								{ this.payee = payee; }
	
	public Calendar getOcurrence() 									{ return occurrence; }
	public void setOcurrence(Calendar occurrence) 					{ this.occurrence = occurrence; }
	
	public BigDecimal getAmount() 									{ return amount; }
	public void setAmount(BigDecimal amount) 						{ this.amount = amount; }
	
	public int getNumber() 											{ return number; }
	public void setNumber(int number) 								{ this.number = number; }
	
	public String getDescription() 									{ return description; }
	public void setDescription(String description) 					{ this.description = description; }
	
	public OperationAccount getAccount() 							{ return account; }
	public void setAccount(OperationAccount origin) 				{ this.account = origin; }
	
	public boolean isCredit() 										{ return credit; }
	public void setCredit(boolean credit) 							{ this.credit = credit; }
	
	public FlowItem getItem() 										{ return item; }
	public void setItem(FlowItem item) 								{ this.item = item; }
	
	public FlowStatus getStatus() 									{ return status; }
	public void setStatus(FlowStatus status) 						{ this.status = status; }
	
	public String getIdentity()  									{ return identity; }
	public void setIdentity(String identity) 						{ this.identity = identity; }
	
	public FlowTransactionAccount getTxAccount() 					{ return txAccount; }
	public void setTxAccount(FlowTransactionAccount txAccount) 		{ this.txAccount = txAccount; }
	
	public boolean isOpen()		 { return (status == FlowStatus.OPEN); }
	public boolean isClosed()	 { return (status == FlowStatus.CLOSED); }
	public boolean isCancelled() { return (status == FlowStatus.CANCELED); }
	
}
