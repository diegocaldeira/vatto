package com.aoks.security.control.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import com.aoks.security.model.EmailInfo;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("emailInfoConverter")
public class EmailInfoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		EmailInfo info = new EmailInfo();
		info.setAddress(value);
		return info;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof EmailInfo))
			return null;
		
		EmailInfo info = (EmailInfo) value;
		
		return info.getAddress();
	}

}
