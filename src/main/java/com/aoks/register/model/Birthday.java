package com.aoks.register.model;

import com.aoks.security.model.AuditEntity;
import com.aoks.utils.webmvc.GenericModel;


/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
public class Birthday implements GenericModel {

	private static final long serialVersionUID = 1L;

	@Override public long getId() 		 { return 0; }
	@Override public void setId(long id) { }
	
	@Override public AuditEntity getAuditEntity() 					{ return null; }
	@Override public void setAuditEntity(AuditEntity auditEntity)	{ }
	

}
