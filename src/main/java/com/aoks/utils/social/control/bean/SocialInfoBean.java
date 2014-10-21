package com.aoks.utils.social.control.bean;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class SocialInfoBean {

	private String key;
	private String value;
	
	
	public SocialInfoBean() {}
	
	public SocialInfoBean(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	
	public String getKey() 				{ return key; }
	public void setKey(String key)		{ this.key = key; }
	
	public String getValue() 			{ return value; }
	public void setValue(String value)	{ this.value = value; }
	
}
