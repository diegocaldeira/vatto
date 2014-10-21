package com.aoks.utils.category.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aoks.security.model.AuditEntity;

/**
 * 
 * @author Diego Pereira
 *
 */
@Entity
@Table(name="SingleCategorizableHelper", schema="utils")
public class SingleDimensionCategorizableHelper extends CategorizableHelper {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="cDimension")
	private CategoryDimension dimension;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SingleCategorized_Category", schema="utils",
			joinColumns=@JoinColumn(name="SingleCategorized"),
			inverseJoinColumns=@JoinColumn(name="Category"))
	private Set<Category> categories;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	
	public SingleDimensionCategorizableHelper() {
		if (categories == null)
			categories = new HashSet<Category>();
	}
	
	
	public CategoryDimension getDimension() {
		return dimension;
	}
	public void setDimension(CategoryDimension dimension) {
		this.dimension = dimension;
	}
	
	
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public void addCategory(Category category){
		categories.add(category);
	}
	
	
	public Category getCategory(){
		return (categories != null && categories.size() > 0 ? new ArrayList<Category>(categories).get(0) : null);
	}
	public void setCategory(Category category){
		categories.add(category);
	}
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof SingleDimensionCategorizableHelper)) return false;
    	SingleDimensionCategorizableHelper that = (SingleDimensionCategorizableHelper) obj;
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
