package com.aoks.enterprise.model.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aoks.enterprise.model.EnterpriseEntity;
import com.aoks.enterprise.model.EnterpriseEntityType;
import com.aoks.enterprise.model.LegalOrganizationBehavior;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="Company", schema="enterprise")
public class Company extends EnterpriseEntity{

	private static final long serialVersionUID = 1L;
	

	@Column(name="cOpening")
	@Temporal(TemporalType.DATE)
	private Calendar opening;
	
	
	public Company() {
		if (behavior == null){
			behavior = new LegalOrganizationBehavior(EnterpriseEntityType.COMPANY);
			behavior.setEntity(this);
		}
	}
	
	public Calendar getOpening() 			 { return opening; }
	public void setOpening(Calendar opening) { this.opening = opening; }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Company)) return false;
		Company that = (Company) obj;
		if (this.getId() > 0 && this.getId() == that.getId())
			return true;
		else if (this.getBehavior().getName() == null ? that.getBehavior().getName() == null : this.getBehavior().getName().equals(that.getBehavior().getName()))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		if (this.getId() > 0)
			result = result * 31 + (int)(this.getId() ^ (this.getId() >>> 32));
		else 
			result = result * 31 + (behavior.getName() != null ? behavior.getName().hashCode() : 0);
		return result;
	}
	
}
