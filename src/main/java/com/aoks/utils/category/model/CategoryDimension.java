package com.aoks.utils.category.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name="CategoryDimension", schema="utils")
public class CategoryDimension implements GenericModel{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="UtilsSequence")
	@TableGenerator(name="UtilsSequence", table="UtilsSequence", schema="utils", allocationSize=1,
			pkColumnName="cTable", pkColumnValue="CategoryDimensionSequence", valueColumnName="cNext", initialValue=1)
	@Column(name="cId")
	private long id;

	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="CategorizedApplication_CategoryDimension", schema="utils",
		joinColumns=@JoinColumn(name="cCategoryDimension"),
		inverseJoinColumns=@JoinColumn(name="cCategorizedApplication"))
	private Set<CategorizedApplication> categorizedApplications;

	@OneToMany(mappedBy="dimension", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Category> categories;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	public CategoryDimension() {
		categorizedApplications = new HashSet<CategorizedApplication>();
		categories = new HashSet<Category>();
	}
	
	
	public Set<CategorizedApplication> getCategorizedApplications() {
		return categorizedApplications;
	}
	public void setCategorizedApplications(
			Set<CategorizedApplication> categorizedApplications) {
		this.categorizedApplications = categorizedApplications;
	}
	public void addCategorizedApplication(CategorizedApplication app){
		categorizedApplications.add(app);
	}
	
	
	public Set<Category> getCategories(){
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public void addCategory(Category category){
		categories.add(category);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	@Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;
    	if (!(obj instanceof CategoryDimension)) return false;
    	CategoryDimension that = (CategoryDimension) obj;
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
