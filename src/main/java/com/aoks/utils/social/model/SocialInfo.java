package com.aoks.utils.social.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Defines a social network.
 * 
 * Ex: type='linkedin' and value='usr_to_acess'
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Embeddable
public class SocialInfo{
	
	@Column(name="cKey")	private String type;
	@Column(name="cValue")	private String value;
	
	public SocialInfo() {}
	
	public SocialInfo(String type, String value){
		this.type = type;
		this.value = value;
	}

	
	public String getType()				{ return type; }
	public void setType(String type)	{ this.type = type; }
	
	public String getValue() 			{ return value; }
	public void setValue(String value)	{ this.value = value; }
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof SocialInfo)) return false;
		SocialInfo that = (SocialInfo) obj;
		if (this.type == null ? that.type == null : this.type.equals(that.type))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (type != null ? type.hashCode() : 0);
		return result;
	}
	
}
