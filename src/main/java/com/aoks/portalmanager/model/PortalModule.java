package com.aoks.portalmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class PortalModule implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String description;
	
	@XmlElement(name="aggregate")
	private List<PortalAggregate> aggregates;
	
	
	@XmlTransient
	public String getName() {
		return name;
	}
	
	@XmlTransient
	public String getDescription() {
		return description;
	}
	
	@XmlTransient
	public List<PortalAggregate> getAggregates() {
		return aggregates;
	}
	
}
