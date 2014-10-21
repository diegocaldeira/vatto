package com.aoks.register.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * Represents a individual contact associated with a legal entity or a person.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "Contact", schema="register")
public class Contact implements GenericModel {

	private static final long serialVersionUID = 1L;
	
    /**
     * Database id
     */
    @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="RegisterSequence")
	@TableGenerator(name="RegisterSequence", table="RegisterSequence", schema="register", 
			pkColumnName="cTable", pkColumnValue="ContactSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

    /**
     * First Name
     */
	@Column(name = "cFirstName")
    private String firstName;
	
	/**
     * Last Name
     */
	@Column(name = "cLastName")
    private String lastName;
    
	
	/**
     * Register
     */
    @ManyToOne
    @JoinColumn(name = "cRegister")
    private Register register;
    
    /**
     * Contact Type
     */
    @Column(name="cContactType")
    private String contactType;
    
    /**
     * Emails
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name = "cContact")
    private Set<Email> emails;

    /**
     * Phones
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name = "cContact")
    private Set<Phone> phones;
    
    /**
     * Facebook
     */
    @Column(name = "cFacebook")
    protected String facebook;
    
    /**
     * Twitter
     */
    @Column(name = "cTwitter")
	protected String twitter;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
    
    
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
//    @JoinColumn(name = "cCategorizedHelper")
//    private SingleDimensionCategorizableHelper categorizedHelper;
    
    
    public Contact() {
//		categorizedHelper = new SingleDimensionCategorizableHelper();
		emails = new HashSet<Email>();
		phones = new HashSet<Phone>();
	}
    
    
//    public SingleDimensionCategorizableHelper getCategorizedHelper() {
//		return categorizedHelper;
//	}
//    public void setCategorizedHelper(
//			SingleDimensionCategorizableHelper categorizedHelper) {
//		this.categorizedHelper = categorizedHelper;
//	}
    
    public String getFirstName() {
		return firstName;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    
    public String getLastName() {
		return lastName;
	}
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
    public Register getRegister() {
		return register;
	}
    public void setRegister(Register register) {
		this.register = register;
	}
    
    
    public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}


	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
    
    public void addEmail(Email email) {
        if (emails.contains(email))
            emails.remove(email);
        emails.add(email);
    }
    public Set<? extends Email> getEmails() {
        return emails;
    }
    public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}
    public void removeEmail(Email email) {
        emails.remove(email);
    }


    public void addPhone(Phone phone) {
        if (phones.contains(phone))
        	phones.remove(phone);
        phones.add(phone);
    }
    public Set<? extends Phone> getPhones() {
        return phones;
    }
    public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
    public void removePhone(Phone phone) {
        phones.remove(phone);
    }
    
    
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Contact)) return false;
		Contact that = (Contact) obj;
		if (this.getId() > 0 && this.getId() == that.getId())
			return true;
		else if ((this.firstName == null ? that.firstName == null : this.firstName.equals(that.firstName)) &&
				(this.lastName == null ? that.lastName == null : this.lastName.equals(that.lastName)))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		if (getId() > 0)
			result = result * 31 + (int)(getId()^(getId()>>>32));
		else {
			result = result + 31 * (firstName != null ? firstName.hashCode() : 0);
			result = result + 31 * (lastName != null ? lastName.hashCode() : 0);
		}
		return result;
	}
	
    @Override
    public String toString() {
        return "Contact, first name is '" + firstName + "', last name is '" + lastName + "'";
    }
	
}
