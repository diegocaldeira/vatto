package com.aoks.portalmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="application")
public class PortalApplication implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String description;
	
	@XmlAttribute
	private String icon;
	
	@XmlAttribute
	private String page;
	
	@XmlElement(name="module")
	private List<PortalModule> modules;
	
	@XmlTransient
	public String getName() {
		return name;
	}

	@XmlTransient
	public String getDescription() {
		return description;
	}
	
	@XmlTransient
	public String getIcon() {
		return icon;
	}
	
	@XmlTransient
	public String getPage() {
		return page;
	}
	
	@XmlTransient
	public List<PortalModule> getModules() {
		return modules;
	}
	
}
