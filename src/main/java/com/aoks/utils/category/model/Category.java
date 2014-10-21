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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;


/**
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="Category", schema="utils")
public class Category implements GenericModel{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="UtilsSequence")
	@TableGenerator(name="UtilsSequence", table="UtilsSequence", schema="utils", 
			pkColumnName="cTable", pkColumnValue="CategorySequence", valueColumnName="cNext", initialValue=1, allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cCode")
	private String code;
	
	@Column(name="cName")
	private String name;
	
	@Column(name="cDescription")
	private String description;
	
	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
	private Set<Category> childs;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cParent")
	private Category parent;
	
	@ManyToOne
	@JoinColumn(name="cDimension")
	private CategoryDimension dimension;
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	public Category() {
		childs = new HashSet<Category>();
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	
	public Set<Category> getChilds() {
		return childs;
	}
	public void setChilds(Set<Category> childs) {
		this.childs = childs;
	}
	public void addChild(Category category){
		if (childs.contains(category))
			childs.remove(category);
		childs.add(category);
	}
	
	
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	
	public CategoryDimension getDimension() {
		return dimension;
	}
	public void setDimension(CategoryDimension dimension) {
		this.dimension = dimension;
	}
	
	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Category)) return false;
		Category that = (Category) obj;
		if (this.getId() > 0 && this.getId() == that.getId())
			return true;
		else if ((this.name == null ? that.name == null : this.name.equals(that.name))
				&& (this.dimension == null ? that.dimension == null : this.dimension.equals(that.dimension)))
			return true;
		return false;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		if (getId() > 0)
			result = result * 31 + (int)(getId() ^ (getId() >>> 32));
		else {
			result = result * 31 + (name != null ? name.hashCode() : 0);
			result = result * 31 + (dimension != null ? dimension.hashCode() : 0);
		}
		return result;
	}
	
}
