//package com.aoks.budget.model;
//
//import javax.persistence.Column;
//import javax.persistence.Embedded;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.TableGenerator;
//
//import com.aoks.utils.webmvc.GenericModel;
//import com.aoks.utils.webmvc.Timeline;
//
///**
// * 
// * @author Diego Pereira
// * @site www.diegopereira.com.br
// *
// */
//@Entity
//@Table(name="FinancialTimeline", schema="utils")
//public class FlowItemTimeline implements GenericModel {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="FinancialTimelineSequence")
//	@TableGenerator(name="FinancialTimelineSequence", table="FinancialTimelineSequence", schema="utils", 
//			pkColumnName="cTable", pkColumnValue="FinancialTimelineSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
//	@Column(name="cId")
//	private long id;
//	
//	@Embedded
//	private Timeline timeline;
//	
//	public long getId() 		{ return id; }
//	public void setId(long id)	{ this.id = id; }
//	
//	public Timeline getTimeline() 				{ return timeline; }
//	public void setTimeline(Timeline timeline)	{ this.timeline = timeline; }
//	
//}
