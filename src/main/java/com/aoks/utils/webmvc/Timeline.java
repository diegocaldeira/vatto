//package com.aoks.utils.webmvc;
//
//import java.util.Calendar;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import com.aoks.security.model.User;
//
///**
// * 
// * @author Diego Pereira
// * @site www.diegopereira.com.br
// *
// */
//@Embeddable
//public class Timeline {
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="cCreationDate")
//	private Calendar creationDate;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="cTimelineType")
//	public TimelineType timelineType;
//	
//	@Column(name="cName")
//	private String name;
//	
//	@Column(name="cDescription")
//	private String description;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="cUser")
//	private User user;
//	
//	public Timeline(TimelineType timelineType, String name, String description, User user){
//		this.creationDate = Calendar.getInstance();
//		this.timelineType = timelineType;
//		this.name = name;
//		this.description = description;
//		this.user = user;
//	}
//	
//	public Calendar getCreationDate() 						{ return creationDate; }
//	public void setCreationDate(Calendar creationDate)		{ this.creationDate = creationDate; }
//	
//	public TimelineType getTimelineType() 					{ return timelineType; }
//	public void setTimelineType(TimelineType timelineType)	{ this.timelineType = timelineType; }
//	
//	public String getName() 		 						{ return name; }
//	public void setName(String name) 						{ this.name = name; }
//	
//	public String getDescription() 							{ return description; }
//	public void setDescription(String description)			{ this.description = description; }
//	
//	public User getUser() 									{ return user; }
//	public void setUser(User user) 							{ this.user = user; }
//
//
//	public enum TimelineType{ CONTACT, FINANCIAL }
//}
