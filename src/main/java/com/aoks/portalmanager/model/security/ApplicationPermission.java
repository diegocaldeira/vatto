package com.aoks.portalmanager.model.security;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="ApplicationPermission", schema="portalManager")
public class ApplicationPermission implements GenericModel {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SecuritySequence")
	@TableGenerator(name="SecuritySequence", table="SecuritySequence", schema="portalManager", 
			pkColumnName="cTable", pkColumnValue="ApplicationPermissionSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Column(name="cName")
	private String name;
	
	@ElementCollection
	@CollectionTable(name="ApplicationPermission_Actions", schema="security", joinColumns=@JoinColumn(name="cApplicationPermission"))
	private Set<String> actions = new HashSet<String>();
	
	/**
	 * Audit entity
	 */
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="audityEntity")
	private AuditEntity auditEntity;
	
	
	@Transient
	private ApplicationInnerPermission innerPermission; 

	@Transient
	private boolean started;
	
	private void start() {
		if (started) return;
		
		innerPermission = new ApplicationInnerPermission(name);
		innerPermission.setActions(actions);
	}
	
	
	public boolean implies(Permission permission) {
		start();
		return innerPermission.implies(permission);
	}
	
	
	public void addAction(String action){
		actions.add(action);
	}
	public void setActions(Set<String> actions) {
		this.actions = actions;
	}
	public String getActions() {
		start();
		return innerPermission.getActions();
	}
	
	public long getId() 				{ return id; }
	public void setId(long id)			{ this.id = id; }
	
	public String getName()				{ return name; }
	public void setName(String name)	{ this.name = name; }
	
	@Override public AuditEntity getAuditEntity() 					{ return auditEntity; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ this.auditEntity = auditEntity; }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof ApplicationPermission)) return false;
		ApplicationPermission that = (ApplicationPermission) obj;
		if (this.name == null ? that.name == null : this.name.equalsIgnoreCase(that.name))
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

class ApplicationInnerPermission extends Permission {

	private static final long serialVersionUID = 1L;
	
	private Set<String> actions;
	
	public ApplicationInnerPermission(String name) {
		super(name);
	}

	@Override
	public boolean implies(Permission permission) {
		
		if (permission == null) return false;
		if (!permission.getName().equals(getName())) return false;

		// TODO
		
		return false;
	}
	
	@Override
	public String getActions() {
		String ret = "";
		for (String s : actions) {
			ret = ret.concat(" ").concat(s);
		}
		return ret;
	}
	public void setActions(Set<String> actions) {
		this.actions = actions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof ApplicationInnerPermission)) return false;
		ApplicationInnerPermission that = (ApplicationInnerPermission) obj;
		if (this.getName().equals(that.getName())
				&& this.actions == null ? that.actions == null : this.actions.equals(that.actions))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (actions != null ? actions.hashCode() : 0);
		return result;
	}
	
}
