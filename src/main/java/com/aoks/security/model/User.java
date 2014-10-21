package com.aoks.security.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.aoks.enterprise.model.entities.Company;
import com.aoks.utils.social.model.SocialInfo;
import com.aoks.utils.webmvc.GenericModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="User", schema="security")
public class User implements GenericModel {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SecuritySequence")
	@TableGenerator(name="SecuritySequence", table="SecuritySequence", schema="security", 
			pkColumnName="cTable", pkColumnValue="UserSequence", valueColumnName="cNext", allocationSize=1)
	@Column(name="cId")
	private long id;
	
	@Lob
	@Column(name="cPicture")
	private byte[] picture;
	
	@Column(name="cFirstName")
	private String firstName;
	
	@Column(name="cLastName")
	private String lastName;
	
	@Column(name="cprofession")
	private String profession;
	
	@Column(name="cStatus")
	private boolean status;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PhoneInfo", schema="security", joinColumns=@JoinColumn(name="cUser"))
	private Set<PhoneInfo> phoneInfos = new HashSet<PhoneInfo>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="EmailInfo", schema="security", joinColumns=@JoinColumn(name="cUser"))
	private Set<EmailInfo> emailInfos = new HashSet<EmailInfo>();
	
	@ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name="User_Socials", schema="security", joinColumns=@JoinColumn(name="cUser"))
    @MapKeyColumn(name="cType")
    @Column(name="socialInfo")
	private Map<String, SocialInfo> socialInfo;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<UserPrincipal> principals = new ArrayList<UserPrincipal>();
	
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cLoginInfo")
	private LoginInfo loginInfo = new LoginInfo();
	
	@OneToOne(cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="cCompany")
	private Company company;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="cAudityEntity")
	private AuditEntity auditEntity;
	
	
	public User(){
		this.socialInfo = new HashMap<String, SocialInfo>();
	}
	
	
	public String getName(){
		return (firstName != null ? firstName : "") + " - " + (lastName != null ? lastName : "");
	}

	@Override public long getId() 			{ return id; }
	@Override public void setId(long id)	{ this.id = id; }
	
	public PhoneInfo getMainPhoneInfo(){
		for (PhoneInfo pi : phoneInfos) {
			if (pi.isMain())
				return pi;
		}
		return null;
	}
	
	public EmailInfo getMainEmailInfo(){
		for (EmailInfo ei : emailInfos) {
			if (ei.isMain())
				return ei;
		}
		return null;
	}
	
	public byte[] getPicture() 									{ return picture; }
	public void setPicture(byte[] picture) 						{ this.picture = picture; }

	public String getFirstName() 								{ return firstName; }
	public void setFirstName(String firstName)					{ this.firstName = firstName; }
	
	public String getLastName() 								{ return lastName; }
	public void setLastName(String lastName) 					{ this.lastName = lastName; }
	
	public boolean isStatus() 									{ return status; }
	public void setStatus(boolean status) 						{ this.status = status; }
	
	public List<UserPrincipal> getPrincipals() 					{ return principals; }
	public void setPrincipals(List<UserPrincipal> principals)	{ this.principals = principals; }
	
	public Company getCompany() 								{ return company; }
	public void setCompany(Company company) 					{ this.company = company; }


	public void addEmailInfo(EmailInfo info){
		
		assert (info != null);
		
		if (info.isMain()) {
			EmailInfo ei = getMainEmailInfo();
			if (ei != null) 
				emailInfos.remove(ei);
		}
		
		emailInfos.add(info);
		
	}
	
	public Set<EmailInfo> getEmailInfos() 					{ return emailInfos; }
	public void setEmailInfos(Set<EmailInfo> emailInfos)	{ this.emailInfos = emailInfos; }
	
	
	public void addPhoneInfo(PhoneInfo info){
		
		assert (info != null);
		
		if (info.isMain()) {
			PhoneInfo pi = getMainPhoneInfo();
			if (pi != null)
				phoneInfos.remove(pi);
		}
		phoneInfos.add(info);
		
	}
	
	public Set<PhoneInfo> getPhoneInfos() 					{ return phoneInfos; }
	public void setPhoneInfos(Set<PhoneInfo> phoneInfos)	{ this.phoneInfos = phoneInfos; }
	
	public String getProfession() 							{ return profession; }
	public void setProfession(String profession) 			{ this.profession = profession; }

	public LoginInfo getLoginInfo() 						{ return loginInfo; }
	public void setLoginInfo(LoginInfo loginInfo)			{ this.loginInfo = loginInfo; }
	
	
	public void addSocialInfo(String type, String code){
		SocialInfo r = new SocialInfo(type, code);
		this.socialInfo.put(r.getType(), r);
    }
    public void addRegistration(SocialInfo socialInfo){
    	this.socialInfo.put(socialInfo.getType(), socialInfo);
    }
    public List<SocialInfo> getSocialInfos(){
    	return new ArrayList<SocialInfo>(this.socialInfo.values()); 
    }
    public SocialInfo getSocialInfo(String type){
    	if (this.socialInfo.containsKey(type))
    		return this.socialInfo.get(type);
    	SocialInfo socialInfo = new SocialInfo();
    	this.socialInfo.put(type, socialInfo);
    	return socialInfo;
    }

	public AuditEntity getAuditEntity() 				{ return auditEntity; }
	public void setAuditEntity(AuditEntity auditEntity) { this.auditEntity = auditEntity;	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof User)) return false;
        User that = (User) obj;
        
        if (this.getId() > 0 && this.getId() == that.getId())
        	return true;

        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        if (getId() > 0)
        	result = result * 31 + (int)(getId()^(getId()>>>32));
        return result;
    }

}