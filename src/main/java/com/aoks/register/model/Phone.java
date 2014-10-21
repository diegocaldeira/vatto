package com.aoks.register.model;

import javax.persistence.CascadeType;
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
import com.aoks.utils.category.model.SingleDimensionCategorizableHelper;
import com.aoks.utils.webmvc.GenericModel;

/**
 * Represents a phone number.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "Phone", schema="register")
public class Phone implements GenericModel {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="RegisterSequence")
	@TableGenerator(name="RegisterSequence", table="RegisterSequence", schema="register", 
			pkColumnName="cTable", pkColumnValue="PhoneSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

    @Column(name = "cCountryCode")
    private String countryCode;

    @Column(name = "cAreaCode")
    private String areaCode;

    @Column(name = "cExtension")
    private String extension;

    @Column(name = "cPhoneNumber")
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cCategorizedHelper")
    private SingleDimensionCategorizableHelper categorizedHelper;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;

    public Phone() {}
    
    
    public SingleDimensionCategorizableHelper getCategorizedHelper() {
		return categorizedHelper;
	}
    public void setCategorizedHelper(
			SingleDimensionCategorizableHelper categorizedHelper) {
		this.categorizedHelper = categorizedHelper;
	}

    @Override public long getId() 			{ return id; }
    @Override public void setId(long id)	{ this.id = id; }
    
    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
    
    public String getAreaCode() 			 { return areaCode; }
    public void setAreaCode(String areaCode) { this.areaCode = areaCode; }
    
    public String getCountryCode()					{ return countryCode; }
    public void setCountryCode(String countryCode)	{ this.countryCode = countryCode; }
    
    public String getExtension() 					{ return extension; }
    public void setExtension(String extension) 		{ this.extension = extension; }
    
    public String getNumber() 				{ return number; }
    public void setNumber(String number)	{ this.number = number; }
    
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof Phone)) return false;
    	Phone that = (Phone) obj;
    	if (this.getId() > 0 && this.getId()== that.getId())
    		return true;
    	else if ((this.areaCode == null ? that.areaCode == null : this.areaCode.equals(that.areaCode)) &&
    			(this.number == null ? that.number == null : this.number.equals(that.number)) &&
    			(this.extension == null ? that.extension == null : this.extension.equals(that.extension)))
    		return true;
    	return false;
    }
    
    @Override
    public int hashCode() {
    	int result = 17;
    	if (getId() > 0)
    		result = result * 31 + (int)(getId()^(getId()>>>32));
    	else {
    		result = result * 31 + (areaCode != null ? areaCode.hashCode() : 0);
    		result = result * 31 + (number != null ? number.hashCode() : 0);
    		result = result * 31 + (extension != null ? extension.hashCode() : 0);
    	}
    	return result;
    }
    
    
}
