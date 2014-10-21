package com.aoks.utils.category.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aoks.security.model.AuditEntity;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="MultiCategorizableHelper", schema="utils")
public class MultiDimensionCategorizableHelper extends CategorizableHelper {

	private static final long serialVersionUID = 1L;
	
	@ElementCollection
	@CollectionTable(name="MultiCategorized_Categories", schema="utils",
			joinColumns=@JoinColumn(name="MultiCategorized"))
	private Set<DimensionCategoryPair> dimensions;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	
	public Set<DimensionCategoryPair> getDimensions() {
		return dimensions;
	}
	public void setDimensions(Set<DimensionCategoryPair> dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof MultiDimensionCategorizableHelper)) return false;
    	MultiDimensionCategorizableHelper that = (MultiDimensionCategorizableHelper) obj;
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

@Embeddable
class DimensionCategoryPair {
	
	@ManyToOne
	@JoinColumn(name="cDimension")
	private CategoryDimension dimension;
	
	@ManyToOne
	@JoinColumn(name="cCategory")
	private Category category;
	
	
	public CategoryDimension getDimension() {
		return dimension;
	}
	public void setDimension(CategoryDimension dimension) {
		this.dimension = dimension;
	}
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
