package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.RepresentativeBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("representativeConverter")
public class RepresentativeBeanConverter extends ModelConverter<RepresentativeBean> {
	
	private static Map<Long, RepresentativeBean> mapped = new HashMap<Long, RepresentativeBean>();

	@Override
	protected Map<Long, RepresentativeBean> mapped() {
		return mapped;
	}
	
}
