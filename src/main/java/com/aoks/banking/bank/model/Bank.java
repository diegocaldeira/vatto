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
 * Represents a Bank institution.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="Bank", schema="banking")
public class Bank implements GenericModel {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BankingSequence")
	@TableGenerator(name="BankingSequence", table="BankingSequence", schema="banking", 
			pkColumnName="cTable", pkColumnValue="BankSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;
	

	/**
	 * The Bank code as it is set in the national system for bank identification
	 */
	@Column(name="cCode")
	protected String code;
	
	/**
	 * A user-friendly text that describes the Bank
	 */
	@Column(name="cName")
	protected String name;
	

	/**
	 * A brief description of the Bank
	 */
	@Column(name="cDescription")
	protected String description;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	

	public long getId() 								{ return id; }
	public void setId(long id) 							{ this.id = id; }
	
	public String getCode() 							{ return code; }
	public void setCode(String code) 					{ this.code = code; }
	
	public String getName() 							{ return name; }
	public void setName(String name) 					{ this.name = name; }
	
	public String getDescription() 						{ return description; }
	public void setDescription(String description) 		{ this.description = description; }

	public AuditEntity getAuditEntity() 				{ return auditEntity; }
	public void setAuditEntity(AuditEntity auditEntity) { this.auditEntity = auditEntity; }
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Bank)) return false;
		Bank that = (Bank) obj;
		if (this.code == null ? that.code == null : this.code.equals(that.code))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (code != null ? code.hashCode() : 0);
		return result;
	}
	
}

