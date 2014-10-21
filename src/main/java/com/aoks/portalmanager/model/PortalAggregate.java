package com.aoks.portalmanager.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class PortalAggregate {

	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String description;
	
	
	@XmlTransient
	public String getName() {
		return name;
	}
	
	@XmlTransient
	public String getDescription() {
		return description;
	}
	
}
