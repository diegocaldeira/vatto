package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.CompanyGroupBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("companyGroupConverter")
public class CompanyGroupBeanConverter extends ModelConverter<CompanyGroupBean> {

	private static Map<Long, CompanyGroupBean> mapped = new HashMap<Long, CompanyGroupBean>();
	
	@Override
	protected Map<Long, CompanyGroupBean> mapped() {
		return mapped;
	}
	
}
