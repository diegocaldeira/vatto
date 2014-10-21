package com.aoks.budget.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * Sets additional information to FlowItem to inform the transaction algorithms how
 * to process the transactions for the item
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class FlowItemInfo implements GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="BudgetSequence")
	@TableGenerator(name="BudgetSequence", table="BudgetSequence", schema="budget", 
			pkColumnName="cTable", pkColumnValue="FlowItemInfoSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="cStart")
	protected Calendar start;
	
	@Column(name="cOperateSaturday")
	protected boolean operateSaturday;
	
	@Column(name="cOperateSunday")
	protected boolean operateSunday;
	
	@Column(name="cOperateHolliday")
	protected boolean operateHolliday;
	
	@Column(name="cMaturityDay")
	protected int maturityDay;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cPeriodicity")
	protected Periodicity periodicity;
	
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
	
	
	public Calendar getStart() 								{ return start; }
	public void setStart(Calendar start)					{ this.start = start; }
	
	public boolean isOperateHolliday() 						{ return operateHolliday; }
	public void setOperateHolliday(boolean operateHolliday) { this.operateHolliday = operateHolliday; }
	
	public boolean isOperateSaturday() 						{ return operateSaturday; }
	public void setOperateSaturday(boolean operateSaturday) { this.operateSaturday = operateSaturday; }
	
	public boolean isOperateSunday() 						{ return operateSunday; }
	public void setOperateSunday(boolean operateSunday) 	{ this.operateSunday = operateSunday; }
	
	public Periodicity getPeriodicity() 					{ return periodicity; }
	public void setPeriodicity(Periodicity periodicity) 	{ this.periodicity = periodicity; }
	
	public int getMaturityDay() 							{ return maturityDay; }
	public void setMaturityDay(int maturityDay) 			{ this.maturityDay = maturityDay; }
	
}
