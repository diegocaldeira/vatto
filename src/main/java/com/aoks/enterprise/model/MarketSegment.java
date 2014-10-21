package com.aoks.enterprise.model;

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
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name = "MarketSegment", schema="enterprise")
public class MarketSegment implements GenericModel {

	private static final long serialVersionUID = 1L;


	/**
     * Database id
     */
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="EnterpriseSequence")
	@TableGenerator(name="EnterpriseSequence", table="EnterpriseSequence", schema="enterprise", 
			pkColumnName="cTable", pkColumnValue="MarketSegmentSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;

	
	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	

    public String getName() 			{ return name; }
    public void setName(String name)	{ this.name = name; }
    
    public String getDescription() 					{ return description; }
    public void setDescription(String description)	{ this.description = description; }
    
    @Override public long getId()			{ return id; }
    @Override public void setId(long id)	{ this.id = id; }

    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
    
	
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof MarketSegment)) return false;
        MarketSegment that = (MarketSegment) obj;
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
}
