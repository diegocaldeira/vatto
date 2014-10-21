package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.IndividualPartnerBean;


@FacesConverter("individualPartnerConverter")
public class IndividualPartnerBeanConverter implements Converter {

	private static Map<Long, IndividualPartnerBean> mapped = new HashMap<Long, IndividualPartnerBean>();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
	
		if (submittedValue.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                long number = Long.parseLong(submittedValue);  
                
                if (mapped.containsKey(number))
    				return mapped.get(number);  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid individualPartner"));  
            }  
        }  
  
        return null;  
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
        	IndividualPartnerBean bean = (IndividualPartnerBean) value;
    		mapped.put(bean.getId(), bean);
            return String.valueOf(bean.getId());  
        }
		
	}
	
}
