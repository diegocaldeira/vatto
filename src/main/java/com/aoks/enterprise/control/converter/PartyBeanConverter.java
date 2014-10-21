package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.PartyBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("partyConverter")
public class PartyBeanConverter extends ModelConverter<PartyBean> {
	
	private static Map<Long, PartyBean> mapped = new HashMap<Long, PartyBean>();

	@Override
	protected Map<Long, PartyBean> mapped() {
		return mapped;
	}
	
}
