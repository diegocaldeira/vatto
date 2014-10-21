package com.aoks.enterprise.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.register.model.Register;
import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * An entity that encapsulates the entity behavior.
 * 
 * <p>An application of the GoF bridge pattern, making composition between {@link EnterpriseEntity} 
 * and {@link EnterpriseBehavior} (that should belong to the same hierarchy) in order to allow each one 
 * to evolve independently.</p>
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "EnterpriseBehavior", schema="enterprise")
@DiscriminatorColumn(name = "cBehaviorType", discriminatorType = DiscriminatorType.STRING)
public abstract class EnterpriseBehavior implements GenericModel {

	private static final long serialVersionUID = 1L;

	/**
     * Database id
     */
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="EnterpriseSequence")
	@TableGenerator(name="EnterpriseSequence", table="EnterpriseSequence", schema="enterprise", 
			pkColumnName="cTable", pkColumnValue="BehaviorSequence", valueColumnName="cNext", allocationSize=1, initialValue=1)
	@Column(name="cId")
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=false, fetch=FetchType.EAGER)
	protected Register register;

    /**
     * The accountabilities to witch this EnterpriseBehavior is a parent.
     */
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
    protected Set<Accountability> childAcc;
    /**
     * The accountabilites to witch this EnterpriseBehavior is a child
     */
    @OneToMany(cascade=CascadeType.ALL, mappedBy="child")
    protected Set<Accountability> parentAcc;
    /**
     * 'Bridge' connection with the {@link EnterpriseEntity}
     */
    @OneToOne(mappedBy="behavior", fetch=FetchType.EAGER)
    protected EnterpriseEntity entity;
    /**
     * The type for this behavior
     */
    @Enumerated(EnumType.STRING)
    @Column(name="cEntityType")
    protected EnterpriseEntityType type;

    @Column(name="cName")
    protected String name;
    
    /**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
    
    public EnterpriseBehavior() {
    	if (parentAcc == null) parentAcc = new HashSet<Accountability>();
    	if (childAcc == null) childAcc = new HashSet<Accountability>();
	}
    
    @Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }

    public void addAccountability(Accountability acc) {
        if (acc.getChild() != null && acc.getChild().equals(this)) {
            parentAcc.add(acc);
        } else if (acc.getParent() != null && acc.getParent().equals(this)) {
            childAcc.add(acc);
        }
    }

    // get accoutabilities regardless of 'type'
	public List<Accountability> getAccountabilities() {
	    Set<Accountability> toReturn = new HashSet<Accountability>();
	    toReturn.addAll(getChildAccountabilities());
	    toReturn.addAll(getParentAccountabilities());
	    return new ArrayList<Accountability>(toReturn);
	}
	public List<Accountability> getAccountabilities(Calendar when) {
	    Set<Accountability> toReturn = new HashSet<Accountability>();
	    toReturn.addAll(getChildAccountabilities(when));
	    toReturn.addAll(getParentAccountabilities(when));
	    return new ArrayList<Accountability>(toReturn);
	}
	public List<Accountability> getChildAccountabilities() {
	    return new ArrayList<Accountability>(getChildAccountabilities(Calendar.getInstance()));
	}
	public List<Accountability> getChildAccountabilities(Calendar when) {
		Set<Accountability> toReturn = new HashSet<Accountability>();
		for (Accountability accountability : childAcc) {
			if (accountability.isEffectiveOnDate(when))
				toReturn.add(accountability);
		}
	    return new ArrayList<Accountability>(toReturn);
	}
	public List<Accountability> getParentAccountabilities() {
	    return new ArrayList<Accountability>(getParentAccountabilities(Calendar.getInstance()));
	}
	public List<Accountability> getParentAccountabilities(Calendar when) {
	    Set<Accountability> toReturn = new HashSet<Accountability>();
	    for (Accountability accountability : parentAcc) {
			if (accountability.isEffectiveOnDate(when))
				toReturn.add(accountability);
		}
	    return new ArrayList<Accountability>(toReturn);
	}
    
    
	// get accountabilities by type string
    public List<Accountability> getAccountabilities(String type){
    	List<Accountability> toReturn = new ArrayList<Accountability>();
    	toReturn.addAll(getParentAccountabilities(type));
    	toReturn.addAll(getChildAccountabilities(type));
    	return toReturn;
    }
    public List<Accountability> getAccountabilities(String type, Calendar when){
    	List<Accountability> toReturn = new ArrayList<Accountability>();
    	toReturn.addAll(getParentAccountabilities(type, when));
    	toReturn.addAll(getChildAccountabilities(type, when));
    	return toReturn;
    }
    public List<Accountability> getChildAccountabilities(String type){
    	return getChildAccountabilities(type, Calendar.getInstance());
    }
    public List<Accountability> getChildAccountabilities(String type, Calendar when){
    	List<Accountability> toReturn = new ArrayList<Accountability>();
    	for (Accountability acc : childAcc) {
    		if (acc.getType().getName().equals(type) && acc.isEffectiveOnDate(when))
    			toReturn.add(acc);
		}
    	return toReturn;
    }
    public List<Accountability> getParentAccountabilities(String type){
    	return getParentAccountabilities(type, Calendar.getInstance());
    }
    public List<Accountability> getParentAccountabilities(String type, Calendar when){
    	List<Accountability> toReturn = new ArrayList<Accountability>();
    	for (Accountability acc : parentAcc) {
    		if (acc.getType().getName().equals(type) && acc.isEffectiveOnDate(when))
    			toReturn.add(acc);
		}
    	return toReturn;
    }
    
    
    // get accountabilities by accountability type
    public List<Accountability> getAccountabilities(AccountabilityType type){
    	return getAccountabilities(type, Calendar.getInstance());
    }
    public List<Accountability> getAccountabilities(AccountabilityType type, Calendar when){
    	if (type == null)
    		throw new IllegalArgumentException();
    	return getAccountabilities(type.getName());
    }
    public List<Accountability> getChildAccountabilities(AccountabilityType type){
    	return getChildAccountabilities(type, Calendar.getInstance());
    }
    public List<Accountability> getChildAccountabilities(AccountabilityType type, Calendar when){
    	if (type == null)
    		throw new IllegalArgumentException();
    	return getChildAccountabilities(type.getName());
    }
    public List<Accountability> getParentAccountabilities(AccountabilityType type){
    	return getParentAccountabilities(type, Calendar.getInstance());
    }
    public List<Accountability> getParentAccountabilities(AccountabilityType type, Calendar when){
    	if (type == null)
    		throw new IllegalArgumentException();
    	return getParentAccountabilities(type.getName());
    }
    
  
    // get childs
    public List<? extends EnterpriseBehavior> getChilds(AccountabilityType type) {
    	return getChilds(type, Calendar.getInstance());
    }
    public List<? extends EnterpriseBehavior> getChilds(AccountabilityType type, Calendar when) {
    	if (type == null)
    		throw new IllegalArgumentException();
    	return getChilds(type.getName(), when);
    }
    public List<? extends EnterpriseBehavior> getChilds(String type) {
    	return getChilds(type, Calendar.getInstance());
    }
    public List<? extends EnterpriseBehavior> getChilds(String type, Calendar when) {
    	List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : childAcc) {
            if (acc.isEffectiveOnDate(when)
                    && acc.getType().getName().equals(type)) {
            	toReturn.add(acc.getChild());
            }
        }
        return toReturn;
    }
    public List<? extends EnterpriseBehavior> getChilds(Calendar when) {
        List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : childAcc) {
            if (acc.isEffectiveOnDate(when)) {
                toReturn.add(acc.getChild());
            }
        }
        return toReturn;
    }
    

    // get parents
    public List<? extends EnterpriseBehavior> getParents(AccountabilityType type) {
        return getParents(type, Calendar.getInstance());
    }
    public List<? extends EnterpriseBehavior> getParents(AccountabilityType type, Calendar when) {
    	if (type == null)
    		throw new IllegalArgumentException();
        return getParents(type.getName(), when);
    }
    public List<? extends EnterpriseBehavior> getParents(String type) {
        return getParents(type, Calendar.getInstance());
    }
    public List<? extends EnterpriseBehavior> getParents(String type, Calendar when) {
    	System.out.println("parents >> " + parentAcc.size() + " childs >> " + childAcc.size());
    	List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : parentAcc) {
            if (acc.isEffectiveOnDate(when) && acc.getType().getName().equalsIgnoreCase(type)) {
                toReturn.add(acc.getParent());
            }
        }
        return toReturn;
    }
    public List<? extends EnterpriseBehavior> getParents(Calendar when) {
        List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : parentAcc) {
            if (acc.isEffectiveOnDate(when)) {
                toReturn.add(acc.getParent());
            }
        }
        return toReturn;
    }

    
    // metodos com deep search
    public List<? extends EnterpriseBehavior> getChild(AccountabilityType type, EnterpriseEntityType etype) {
    	if (type == null)
    		throw new IllegalArgumentException();
        return getChild(type.getName(), etype);
    }

    public List<? extends EnterpriseBehavior> getChild(String type, EnterpriseEntityType etype) {
    	List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : childAcc) {
            if (acc.isEffectiveOnDate(Calendar.getInstance())
                    && acc.getType().getName().equalsIgnoreCase(type)) {

                EnterpriseBehavior child = acc.getChild();
                if (child.getType().equals(etype)) {
                	toReturn.add(child);
                } else {
                    return child.getChild(type, etype);
                }

            }
        }
        return toReturn;
    }
    public List<? extends EnterpriseBehavior> getParent(AccountabilityType type, EnterpriseEntityType etype) {
    	if (type == null)
    		throw new IllegalArgumentException();
        return getParent(type.getName(), etype);
    }
    public List<? extends EnterpriseBehavior> getParent(String type, EnterpriseEntityType etype) {
    	
    	List<EnterpriseBehavior> toReturn = new ArrayList<EnterpriseBehavior>();
        for (Accountability acc : parentAcc) {
            if (acc.isEffectiveOnDate(Calendar.getInstance())
                    && acc.getType().getName().equalsIgnoreCase(type)) {

                EnterpriseBehavior parent = acc.getParent();

                if (parent.getType() == etype) {
                    toReturn.add(parent);
                } else {
                    return parent.getParent(type, etype);
                }

            }
        }
        return toReturn;
    }
    

    // other adder methods
    // TODO
    public void addChild(String type, EnterpriseEntity child) {

        AccountabilityType a = new AccountabilityType(type);

        Accountability acc = new Accountability.Builder(a, child.getBehavior(), this).build();

        this.addAccountability(acc);

    }

    public void addChild(String type, EnterpriseEntity child, Calendar date) {
    }

    public void addParent(String type, EnterpriseEntity parent) {
    }

    public void addParent(String type, EnterpriseEntity parent, Calendar date) {
    }

    
    // remove methods
    // TODO
    public void removeAccountability(Accountability acc) {
        if (childAcc.contains(acc))
            childAcc.remove(acc);
        if (parentAcc.contains(acc))
            parentAcc.remove(acc);
    }
    
    public void removeAccountability(AccountabilityType type){
    	
    }

    public EnterpriseEntity getEntity() {
        return entity;
    }
    public void setEntity(EnterpriseEntity entity) {
        this.entity = entity;
    }
    

    public long getId() {
        return id;
    }
    public void setId(long id){
    	this.id = id;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    

    public EnterpriseEntityType getType() {
        return type;
    }
    public void setType(EnterpriseEntityType type) {
        this.type = type;
    }

    
    // Object
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EnterpriseBehavior)) return false;
        EnterpriseBehavior that = (EnterpriseBehavior) obj;
        if (this.name == null ? that.name == null : this.name.equals(that.name)
                && this.type == that.type)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = result * 31 + (name != null ? name.hashCode() : 0);
        result = result * 31 + (type != null ? type.hashCode() : 0);
        return result;
    }

    public abstract void setRegister(Register register);
    public abstract Register getRegister();
    
}
