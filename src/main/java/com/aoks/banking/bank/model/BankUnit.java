package com.aoks.banking.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="BankUnit", schema="banking")
public class BankUnit implements GenericModel {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BankingSequence")
	@TableGenerator(name="BankingSequence", table="BankingSequence", schema="banking", 
			pkColumnName="cTable", pkColumnValue="BankUnitSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;

	
	@Column(name="cNumber")
	private String number;
	
	@Column(name="cDigit")
	private String digit;
	
	
	@Column(name="cName")
	private String name;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public long getId() 									{ return id; }
	@Override public void setId(long id)							{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
	
	public String getDigit() 				{ return digit; }
	public void setDigit(String digit) 		{ this.digit = digit; }
	
	public String getNumber() 				{ return number; }
	public void setNumber(String number)	{ this.number = number; }
	
}
