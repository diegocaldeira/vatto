package com.aoks.security.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Embeddable
public class PhoneInfo {

	@Column(name="cICode")
	private String icode;
	
	@Column(name="cNCode")
	private String ncode;
	
	@Column(name="cNumber")
	private String number;
	
	@Column(name="cComplement")
	private String complement;
	
	@Column(name="cType")
	private PhoneType type;
	
	@Column(name="cPhoneNumber")
	private String phoneNumber;
	
	@Column(name="cMain")
	private boolean main;

	public String getPhone(){
		return (ncode != null && number != null) ? "(" + ncode + ")" + number : null; 
	}
	
	public boolean isMain() {
		return main;
	}
	public void setMain(boolean main) {
		this.main = main;
	}
	
	
	public String getIcode() {
		return icode;
	}
	public void setIcode(String icode) {
		this.icode = icode;
	}

	
	public String getNcode() {
		return ncode;
	}
	public void setNcode(String ncode) {
		this.ncode = ncode;
	}

	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public PhoneType getType() {
		return type;
	}
	public void setType(PhoneType type) {
		this.type = type;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof PhoneInfo)) return false;
		PhoneInfo that = (PhoneInfo) obj;
		if ((this.icode == null ? that.icode == null : this.icode.equals(that.icode))
				&& (this.ncode == null ? that.ncode == null : this.ncode.equals(that.ncode))
				&& (this.number == null ? that.number == null : this.number.equals(that.number))
				&& (this.complement == null ? that.complement == null : this.complement.equals(that.complement)))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (icode != null ? icode.hashCode() : 0);
		result = result * 31 + (ncode != null ? ncode.hashCode() : 0);
		result = result * 31 + (number != null ? number.hashCode() : 0);
		result = result * 31 + (complement != null ? complement.hashCode() : 0);
		return result;
	}
}
