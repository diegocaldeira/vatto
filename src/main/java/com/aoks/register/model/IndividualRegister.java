package com.aoks.register.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents an individual register
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Entity
@Table(name = "IndividualRegister", schema="register")
public class IndividualRegister extends Register {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="cBirthDate")
    private Calendar birth;

    @Column(name = "cFatherName")
    private String fatherName;

    @Column(name = "cMotherName")
    private String motherName;
    
    @Column(name = "cName")
    private String name;

    @Column(name = "cFirstName")
    private String firstName;

    @Column(name = "cMiddleName")
    private String middleName;

    @Column(name = "cLastName")
    private String lastName;

    @Column(name = "cNacionality")
    private String nationality;

    @Column(name = "cNatural")
    private String naturality;

    @Enumerated(EnumType.STRING)
    @Column(name = "cMarital")
    private MaritalStatusCategory maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "cSex")
    private GenreCategory genreCategory;
    
    @Column(name = "cEmail")
    private String email;
    

    public Calendar getBirth() {
		return birth;
	}
    public void setBirth(Calendar birth) {
		this.birth = birth;
	}
    
    
    public String getFatherName() {
		return fatherName;
	}
    public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
    
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getFirstName() {
		return firstName;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    
    public String getLastName() {
		return lastName;
	}
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
    public String getMiddleName() {
		return middleName;
	}
    public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
    
    
	public MaritalStatusCategory getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatusCategory maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	public String getNaturality() {
		return naturality;
	}
	public void setNaturality(String naturality) {
		this.naturality = naturality;
	}
	
	
	public GenreCategory getGenreCategory() {
		return genreCategory;
	}
	public void setGenreCategory(GenreCategory sexCategory) {
		this.genreCategory = sexCategory;
	}
    
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    @Override
    public String toString() {
        return super.toString();
    }
}
