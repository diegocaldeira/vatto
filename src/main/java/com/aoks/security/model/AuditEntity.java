package com.aoks.security.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aoks.enterprise.model.entities.Company;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "AuditEntity", schema="security")
public class AuditEntity {


	/**
	 * DataBase id
	 */
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="AuditEntitySequence")
	@TableGenerator(name="AuditEntitySequence", table="AuditEntitySequence", schema="security", 
			pkColumnName="cTable", pkColumnValue="AuditEntitySequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	/**
	 * User author
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @JoinColumn(name = "cAuthor")
	private User user;
	
	/**
	 * Users who have modified
	 */
//    @Column(name="cAudityUsers")
	
//	@ElementCollection
//	@CollectionTable(name = "cModifiedUsers", joinColumns = @JoinColumn(name = "cId"))
//	@Column(name = "cModifyUsers")
//	@IndexColumn(name="cId")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="auditEntity", fetch=FetchType.LAZY)
	protected Set<User> users = new HashSet<User>();
	
	/**
	 * Company author
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinColumn(name = "cCompany")
	private Company company;
	
	/**
	 * Creation date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cCreationDate")
    private Calendar creationDate;
	
	/**
	 * Modify date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="cModifyDates")
    private Set<Calendar> modifyDates = new HashSet<Calendar>();
	
	
	public AuditEntity(){
		this.creationDate = GregorianCalendar.getInstance();
	}
	
	
	public long getId() 									{ return id; }
	public void setId(long id) 								{ this.id = id; }
	
	public User getUser() 									{ return user; }
	public void setUser(User user) 							{ this.user = user; }

	public Set<User> getUsers() 							{ return users; }
	public void setUsers(HashSet<User> users) 					{ this.users = users; }

	public Calendar getCreationDate() 						{ return creationDate; }
	public void setCreationDate(Calendar creationDate)		{ this.creationDate = GregorianCalendar.getInstance(); }

	public Set<Calendar> getModifyDates() 					{ return modifyDates; }
	public void setModifyDates(Set<Calendar> modifyDates) 	{ this.modifyDates = modifyDates; }

	public Company getCompany() 							{ return company; }
	public void setCompany(Company company) 				{ this.company = company; }

}
