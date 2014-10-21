package com.aoks.security.control.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class RoleBean {

	@XmlAttribute
	private String name;
	
	@XmlElement(name="permission")
	private List<PermissionBean> permissions;
	
	
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@XmlTransient
	public List<PermissionBean> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBean> permissions) {
		this.permissions = permissions;
	}
	
}
