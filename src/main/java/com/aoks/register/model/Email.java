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
 * Represents an email object.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "Email", schema="register")
public class Email implements GenericModel {

	private static final long serialVersionUID = 1L;

	 /**
     * Database id
     */
    @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="RegisterSequence")
	@TableGenerator(name="RegisterSequence", table="RegisterSequence", schema="register", 
			pkColumnName="cTable", pkColumnValue="EmailSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

    @Column(name = "cEmail")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cCategorizedHelper")
    private SingleDimensionCategorizableHelper categorizedHelper;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
    
    public Email() {
    	if (categorizedHelper == null)
    		categorizedHelper = new SingleDimensionCategorizableHelper();
	}
    
    
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
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof Email)) return false;
    	Email that = (Email) obj;
    	if (this.getId() > 0 && this.getId() == that.getId())
    		return true;
    	else if (this.email == null ? that.email == null : this.email.equals(that.email))
    		return true;
    	return false;
    }
    
    @Override
    public int hashCode() {
    	int result = 17;
    	if (getId() > 0)
    		result = result * 31 + (int)(getId()^(getId()>>>32));
    	else 
    		result = result * 31 + (email != null ? email.hashCode() : 0);
    	return result;
    }
    
    
    @Override
    public String toString() {
        return "E-Mail: " + email;
    }
    
}
