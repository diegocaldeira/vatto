package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.RegionalBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("regionalConverter")
public class RegionalBeanConverter extends ModelConverter<RegionalBean> {
	
	private static Map<Long, RegionalBean> mapped = new HashMap<Long, RegionalBean>();

	@Override
	protected Map<Long, RegionalBean> mapped() {
		return mapped;
	}
	
}
