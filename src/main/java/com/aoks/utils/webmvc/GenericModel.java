package com.aoks.utils.webmvc;

import java.io.Serializable;

import com.aoks.security.model.AuditEntity;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public interface GenericModel extends Serializable {
	
	public long getId();
	public void setId(long id);
	
	public AuditEntity getAuditEntity();
	public void setAuditEntity(AuditEntity auditEntity);

}
