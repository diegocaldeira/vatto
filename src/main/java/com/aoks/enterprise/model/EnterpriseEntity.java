package com.aoks.enterprise.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * Represents an enterprise entity
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "EntepriseEntity", schema="enterprise")
public abstract class EnterpriseEntity implements GenericModel {

	private static final long serialVersionUID = 1L;

	/**
     * The database id
     */
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="EnterpriseSequence")
	@TableGenerator(name="EnterpriseSequence", table="EnterpriseSequence", schema="enterprise", 
			pkColumnName="cTable", pkColumnValue="EnterpriseEntitySequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;
	
    /**
     * Bridge with the {@link EnterpriseBehavior} for this entity.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cBehaviorId")
    protected EnterpriseBehavior behavior;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;


    public long getId() 		{ return id; }
    public void setId(long id)	{ this.id = id; }

    public EnterpriseBehavior getBehavior() 			 { return behavior; }
    public void setBehavior(EnterpriseBehavior behavior) { this.behavior = behavior; }

    public AuditEntity getAuditEntity() 				 { return auditEntity; }
	public void setAuditEntity(AuditEntity auditEntity)	 { this.auditEntity = auditEntity; }
	
	
	// Object
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EnterpriseEntity)) return false;
        EnterpriseEntity that = (EnterpriseEntity) obj;
        if (this.getId() > 0 && this.getId() == that.getId())
        	return true;
        else if (this.behavior == null ? that.behavior == null : this.behavior.equals(that.behavior))
        	return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        if (getId() > 0)
        	result = result * 31 + (int)(getId()^(getId()>>>32));
        else
        	result = result * 31 + (behavior != null ? behavior.hashCode() : 0);
        return result;
    }
}
