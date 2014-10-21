package com.aoks.register.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.category.model.SingleDimensionCategorizableHelper;
import com.aoks.utils.webmvc.GenericModel;

/**
 * A super type for all registers.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "Register", schema="register")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Register implements GenericModel {

	private static final long serialVersionUID = 1L;

	/**
     * Database id
     */
    @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="RegisterSequence")
	@TableGenerator(name="RegisterSequence", table="RegisterSequence", schema="register", 
			pkColumnName="cTable", pkColumnValue="RegisterSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name = "cRegister")
    protected Set<Address> addresses;
    
    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, fetch= FetchType.LAZY, orphanRemoval=true)
    protected Set<Contact> contacts;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name="Register_Registration", schema="register", joinColumns=@JoinColumn(name="cRegister"))
    @MapKeyColumn(name="cType")
    @Column(name="cRegistration")
    private Map<String, Registration> registrations;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @JoinColumn(name = "cCategorizedHelper")
    private SingleDimensionCategorizableHelper categorizedHelper;
    
    @Column(name = "cSite")
    protected String site;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cPhone")
	protected Phone phone;
    
    @Column(name = "cFacebook")
    protected String facebook;
    
    @Column(name = "cTwitter")
	protected String twitter;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;

    
    public Register() {
		categorizedHelper = new SingleDimensionCategorizableHelper();
		registrations = new HashMap<String, Registration>();
		contacts = new HashSet<Contact>();
		addresses = new HashSet<Address>();
    }
    
    
    public SingleDimensionCategorizableHelper getCategorizedHelper() {
		return categorizedHelper;
	}
    public void setCategorizedHelper(SingleDimensionCategorizableHelper categorizedHelper) {
		this.categorizedHelper = categorizedHelper;
	}
    
    @Override public long getId() 		 { return id; }
    @Override public void setId(long id) { this.id = id; }
    
    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }

    public void addRegistration(String type, String code){
    	Registration r = new Registration(type, code);
    	registrations.put(r.getType(), r);
    }
    public void addRegistration(Registration registration){
    	registrations.put(registration.getType(), registration);
    }
    public List<Registration> getRegistrations(){
    	return new ArrayList<Registration>(registrations.values()); 
    }
    public Registration getRegistration(String type){
    	if (registrations.containsKey(type))
    		return registrations.get(type);
    	Registration registration = new Registration();
    	registrations.put(type, registration);
    	return registration;
    }
    
    public void removeAddress(Address address) 		 { addresses.remove(address); }
    public void addAddress(Address address) {
    	if (addresses.contains(address)){
            addresses.remove(address);
        }
        addresses.add(address);
    }
    public List<? extends Address> getAddresses() 	 { return new ArrayList<Address>(addresses); }
    public void setAddresses(Set<Address> addresses) { this.addresses = addresses; }
    
    public void removeContact(Contact contact) 		{ contacts.remove(contact); }
    public void addContact(Contact contact) {
    	if (contacts.contains(contact))
    		contacts.remove(contact);
    	contacts.add(contact);
    }
    public List<? extends Contact> getContacts()	{ return new ArrayList<Contact>(contacts); }	
    public void setContacts(Set<Contact> contacts)	{ this.contacts = contacts; }

	public String getSite() 					{ return site; }
	public void setSite(String site) 			{ this.site = site; }

	public Phone getPhone() 					{ return phone; }
	public void setPhone(Phone phone) 			{ this.phone = phone;	}
	
	public String getFacebook() 				{ return facebook; }
	public void setFacebook(String facebook) 	{ this.facebook = facebook; }

	public String getTwitter() 					{ return twitter; }
	public void setTwitter(String twitter)		{ this.twitter = twitter; }
    
}
