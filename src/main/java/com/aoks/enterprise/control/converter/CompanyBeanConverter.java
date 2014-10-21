package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.CompanyBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("companyConverter")
public class CompanyBeanConverter extends ModelConverter<CompanyBean> {

	private static Map<Long, CompanyBean> mapped = new HashMap<Long, CompanyBean>();

	@Override
	protected Map<Long, CompanyBean> mapped() {
		return mapped;
	}
	
}
