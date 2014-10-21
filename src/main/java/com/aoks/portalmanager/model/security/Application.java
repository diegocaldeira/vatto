package com.aoks.portalmanager.model.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="Application", schema="portalManager")
public class Application implements GenericModel {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SecuritySequence")
	@TableGenerator(name="SecuritySequence", table="SecuritySequence", schema="portalManager", 
			pkColumnName="cTable", pkColumnValue="ApplicationSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;

	@Column(name="cName")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cApplication")
	private Set<ApplicationPermission> permissions = new HashSet<ApplicationPermission>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cApplication")
	private Set<ApplicationRole> roles = new HashSet<ApplicationRole>();
	
	/**
	 * Audit entity
	 */
//	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
//	@JoinColumn(name="cAudityEntity")
//	private AuditEntity auditEntity;
	
	
	public String getName() 			{ return name; }
	public void setName(String name) 	{ this.name = name; }
	
	public void addPermission(ApplicationPermission permission){
		permissions.add(permission);
	}
	public Set<ApplicationPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<ApplicationPermission> permissions) {
		this.permissions = permissions;
	}
	
	
	public void addRole(ApplicationRole role){
		roles.add(role);
	}
	public Set<ApplicationRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<ApplicationRole> roles) {
		this.roles = roles;
	}
	
	
	@Override public long getId() 		 { return id; }
	@Override public void setId(long id) { this.id = id; }
	
	@Override public AuditEntity getAuditEntity() 					{ return null; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ }
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Application)) return false;
		Application that = (Application) obj;
		if (this.name == null ? that.name == null : this.name.equals(that.name))
			return false;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (name != null ? name.hashCode() : 0);
		return result;
	}
	
}
