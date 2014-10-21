package com.aoks.utils.category.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;


/**
 * A class that represents an application that uses the Category Model.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="CategorizedApplication", schema="utils")
public class CategorizedApplication implements GenericModel{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="UtilsSequence")
	@TableGenerator(name="UtilsSequence", table="UtilsSequence", schema="utils", 
			pkColumnName="cTable", pkColumnValue="CategorizedApplicationSequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cName")
	private String name;
	
	@ManyToMany(mappedBy="categorizedApplications", fetch=FetchType.EAGER)
	public Set<CategoryDimension> dimensions;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	public CategorizedApplication() {
		dimensions = new HashSet<CategoryDimension>();
	}
	
	
	public Set<CategoryDimension> getDimensions() {
		return dimensions;
	}
	public void setDimensions(Set<CategoryDimension> dimensions) {
		this.dimensions = dimensions;
	}
	public void addDimensions(CategoryDimension dimension){
		dimensions.add(dimension);
	}
	
	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }


	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof CategorizedApplication)) return false;
    	CategorizedApplication that = (CategorizedApplication) obj;
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
