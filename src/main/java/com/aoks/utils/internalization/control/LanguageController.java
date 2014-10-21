package com.aoks.utils.internalization.control;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Named("languageController")
@SessionScoped
public class LanguageController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String localeCode;
 
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Espanish", new Locale("es","ES"));
		countries.put("Portuguese", new Locale("pt","BR"));
	}
 
	public Map<String, Object> getCountriesInMap()	{ return countries; }
 
	public String getLocaleCode() 					{ return localeCode; }
	public void setLocaleCode(String localeCode)	{ this.localeCode = localeCode; }
 

	public void countryLocaleCodeChanged(ValueChangeEvent e){
		String newLocaleValue = e.getNewValue().toString();
 
		//loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
        	}
       }
	}

}
