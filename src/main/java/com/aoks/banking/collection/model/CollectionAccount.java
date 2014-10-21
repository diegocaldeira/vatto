package com.aoks.banking.collection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.aoks.banking.operation.model.BankAccount;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
public class CollectionAccount implements GenericModel {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BankingSequence")
	@TableGenerator(name="BankingSequence", table="BankingSequence", schema="banking", 
			pkColumnName="cTable", pkColumnValue="CollectionSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	@Column(name="cLine")
	private String line;
	
	@Column(name="cOperation")
	private String operation;
	
	@Column(name="cAgreement")
	private String agreement;
	
	@ManyToOne
	@JoinColumn(name="cBankAccount")
	private BankAccount bankAccount;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="audityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public long getId() 		 							{ return id; }
	@Override public void setId(long id) 							{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	
	public String getLine()			 					{ return line; }
	public void setLine(String line) 					{ this.line = line; }
	
	public String getOperation()						{ return operation; }
	public void setOperation(String operation)			{ this.operation = operation; }

	public BankAccount getBankAccount()					{ return bankAccount; }
	public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }
	
	public String getAgreement() 						{ return agreement; }
	public void setAgreement(String agreement) 			{ this.agreement = agreement; }
	
	public String getDescription()						{ return description; }
	public void setDescription(String description)		{ this.description = description; }
	
	public String getName()								{ return name; }
	public void setName(String name)					{ this.name = name; }

}
