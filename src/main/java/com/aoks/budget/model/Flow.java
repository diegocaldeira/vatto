package com.aoks.budget.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * Represents a flow in the budget model.
 * A flow is a dimension of expenses, incomes and transactions.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="Flow", schema="budget")
public class Flow implements GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BudgetSequence")
	@TableGenerator(name="BudgetSequence", table="BudgetSequence", schema="budget", 
			pkColumnName="cTable", pkColumnValue="FlowSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

	@Column(name="cName")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cFlow")
	private List<FlowItem> itens;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cFlow")
	private List<FlowTransaction> transactions;
	
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
	
	public String getName()									 		{ return name; }
	public void setName(String name) 								{ this.name = name; }
	
	public List<FlowItem> getItens() 								{ return itens; }
	public void setItens(List<FlowItem> itens)						{ this.itens = itens; }
	
	public List<FlowTransaction> getTransactions() 					{ return transactions; }
	public void setTransactions(List<FlowTransaction> transactions) { this.transactions = transactions; }
	
}
