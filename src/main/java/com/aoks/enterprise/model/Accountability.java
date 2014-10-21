package com.aoks.enterprise.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.temporal.AbstractEffectivity;
import com.aoks.utils.temporal.Effectivity;
import com.aoks.utils.webmvc.GenericModel;

/**
 *  Represents a typed and hierarchical relationship between two enterprise entities.
 *  Accountability is a implementation of Martin Fowler's analysis pattern with the same
 *  name.
 * 
 * <p>Entities are here represented by its {@link EnterpriseBehavior}.</p>
 *   
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "Accountability", schema="enterprise")
public class Accountability implements Effectivity, GenericModel{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="EnterpriseSequence")
	@TableGenerator(name="EnterpriseSequence", table="EnterpriseSequence", schema="enterprise", 
			pkColumnName="cTable", pkColumnValue="AccountabilitySequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;
    
    /**
     * The child in this relationship.
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "cChild")
    private EnterpriseBehavior child;
    
    /**
     * The parent in this relationship.
     */
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "cParent")
    private EnterpriseBehavior parent;
    
    /**
     * The type of this accountability.
     */
    @OneToOne
    @JoinColumn(name = "cType")
    private AccountabilityType type;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
    
    /**
     * Object that encapsulates the period when this relationship is effective
     */
    @Embedded
    private AbstractEffectivity effectivity = new AbstractEffectivity();

    Accountability() {}

    private Accountability(Builder builder) {

        this.type = builder.type;
        this.child = builder.child;
        this.parent = builder.parent;

        if (builder.start != null)
            effectivity.create(builder.start);
        else
            effectivity.create(new GregorianCalendar());

        if (builder.end != null)
            effectivity.end(builder.end);

        if (this.child != null)
            this.child.addAccountability(this);

        if (this.parent != null)
            this.parent.addAccountability(this);

    }

    
    // Effectivity
    @Override
    public void create(Calendar start) {
        effectivity.create(start);
    }

    @Override
    public void end(Calendar end) {
        effectivity.end(end);
    }

    @Override
    public boolean isEffectiveOnDate(Calendar calendar) {
        return effectivity.isEffectiveOnDate(calendar);
    }
    
    public void destroy(){
    	parent.removeAccountability(this);
    	child.removeAccountability(this);
    }

    // getters and setters
    
    public long getId()			{ return id; }
    public void setId(long id)	{ this.id = id; }
    
    @Override public AuditEntity getAuditEntity() 				{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity) { this.auditEntity = auditEntity; }
    
    public EnterpriseBehavior getChild() {
        return child;
    }

    public EnterpriseBehavior getParent() {
        return parent;
    }

    public AccountabilityType getType() {
        return type;
    }

    public static class Builder {

        EnterpriseBehavior child;
        EnterpriseBehavior parent;
        AccountabilityType type;
        Calendar start;
        Calendar end;

        public Builder(AccountabilityType type, EnterpriseBehavior parent, EnterpriseBehavior child) {

            if (type == null || child == null || parent == null)
                throw new IllegalArgumentException("Cannot accept null value on constructor");
            
            this.type = type;
            this.child = child;
            this.parent = parent;

        }

        public final Builder start(Calendar when) {
            start = when;
            return this;
        }

        public final Builder end(Calendar when) {
            end = when;
            return this;
        }

        public Accountability build() {
            return new Accountability(this);
        }
    }
}
