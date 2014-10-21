package com.aoks.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="LoginInfo", schema="security")
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cUsername")
	private String username;
	
	@Column(name="cPassword")
	private String password;
	
	
	public String getUsername() 				{ return username; }
	public void setUsername(String username)	{ this.username = username; }
	
	public String getPassword() 				{ return password; }
	public void setPassword(String password)	{ this.password = password; }
	
}
