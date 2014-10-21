package com.aoks.enterprise.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * A type for {@link Accountability}.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 */
@Entity
@Table(name = "AccountabilityType", schema="enterprise")
public class AccountabilityType implements GenericModel {

	private static final long serialVersionUID = 1L;

	/**
     * Database id
     */
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="EnterpriseSequence")
	@TableGenerator(name="EnterpriseSequence", table="EnterpriseSequence", schema="enterprise", 
			pkColumnName="cTable", pkColumnValue="AccountabilityTypeSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;
	
    /**
     * Encapsulates 'name' and 'description' attributes for this object
     */
	@Column(name="cName")
    private String name;
	
    /**
     * Defines a collection of {@link EnterpriseEntityTypePair} that defines the allowed
     * {@link EnterpriseEntityType}'s that can be a parent and a child in the {@link Accountability}'s
     * with this type.
     */
    @ElementCollection
    @JoinTable(name = "AccType_Pairs", schema="enterprise",
        joinColumns = {@JoinColumn(name = "cAccTypeId")})
    @Column(name = "cPairValue")
    private Set<EnterpriseEntityTypePair> allowedPairs = new HashSet<EnterpriseEntityTypePair>();
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;

    public AccountabilityType() {
    }

    public AccountabilityType(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    public void addAllowedPair(EnterpriseEntityType child, EnterpriseEntityType parent) {
        allowedPairs.add(new EnterpriseEntityTypePair(child, parent));
    }
    public boolean isAllowedPair(EnterpriseEntityType child, EnterpriseEntityType parent) {
        return allowedPairs.contains(new EnterpriseEntityTypePair(child, parent));
    }

    
    @Override public long getId() 			{ return id; }
    @Override public void setId(long id)	{ this.id = id; }
    
    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
    
    
    // Object
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof AccountabilityType)) return false;
        AccountabilityType that = (AccountabilityType) obj;
        if (this.name == null ? that.name == null : this.name.equals(that.name))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = result * 31 + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
