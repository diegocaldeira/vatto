package com.aoks.register.model;

import java.io.Serializable;

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
 * A class that represents a geographical address of an entity. 
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "Address", schema="register")
public class Address implements GenericModel, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Database id
     */
    @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="RegisterSequence")
	@TableGenerator(name="RegisterSequence", table="RegisterSequence", schema="register", 
			pkColumnName="cTable", pkColumnValue="AddressSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;
    
    /**
     * City name
     */
    @Column(name = "cCity")
    private String city;
    
    /**
     * Address completion
     */
    @Column(name = "cCompletion")
    private String completion;
    
    /**
     * Neighborhood name
     */
    @Column(name = "cNeighborhood")
    private String neighborhood;
    
    /**
     * Street number
     */
    @Column(name = "cNumber")
    private String number;
    
    /**
     * Country
     */
    @Column(name = "cCountry")
    private String country;
    
    /**
     * Country state or province
     */
    @Column(name = "cState")
    private String state;
    
    /**
     * Street name
     */
    @Column(name = "cStreet")
    private String street;
    
    /**
     * Zip code.
     */
    @Column(name = "cZipCode")
    private String zipCode;
    
    /**
     * Address Type
     */
    @Column(name="cAddressType")
    private String addressType;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
    
    /**
     * Category
     */
    
// Were commented 'cause was changed by addressType
    
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cCategorizedHelper")
//    private SingleDimensionCategorizableHelper categorizedHelper;

    
    public Address() {
//    	if (categorizedHelper == null)
//    		categorizedHelper = new SingleDimensionCategorizableHelper();
	}
    
    
    public String getAddressType() { return addressType; }
	public void setAddressType(String addressType) { this.addressType = addressType; }
	
    
//    public SingleDimensionCategorizableHelper getCategorizedHelper() {
//		return categorizedHelper;
//	}
//    public void setCategorizedHelper(SingleDimensionCategorizableHelper categorizedHelper) {
//		this.categorizedHelper = categorizedHelper;
//	}
    
    
    public String getAddressDescription(){
    	return street + ", " + number + " " + completion;
    }

    
    @Override public long getId() 		 { return id; }
    @Override public void setId(long id) { this.id = id; }
    
    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
    
    public String getCity() {
		return city;
	}
    public void setCity(String city) {
		this.city = city;
	}
    
    
    public String getCompletion() {
		return completion;
	}
    public void setCompletion(String completion) {
		this.completion = completion;
	}
    
    
    public String getNeighborhood() {
		return neighborhood;
	}
    public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
    
    
    public String getNumber() {
		return number;
	}
    public void setNumber(String number) {
		this.number = number;
	}
    
    
    public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}
    public void setState(String state) {
		this.state = state;
	}
    
    
    public String getStreet() {
		return street;
	}
    public void setStreet(String street) {
		this.street = street;
	}
    
    
    public String getZipCode() {
		return zipCode;
	}
    public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    
    
    @Override
    public String toString() {
        return "Address " + this.street + ", " + this.number + " - " + this.city + "/" + this.state;
    }
    
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof Address)) return false;
    	Address that = (Address) obj;
    	if (this.getId() > 0 && this.getId() == that.getId())
    		return true;
    	return false;
    }
    
    @Override
    public int hashCode() {
    	int result = 17;
    	result = result * 31 + (int)(getId()^(getId()>>>32));
    	return result;
    }
    
}
