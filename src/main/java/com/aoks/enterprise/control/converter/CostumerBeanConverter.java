package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.CostumerBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("costumerConverter")
public class CostumerBeanConverter extends ModelConverter<CostumerBean> {
	
	private static Map<Long, CostumerBean> mapped = new HashMap<Long, CostumerBean>();

	@Override
	protected Map<Long, CostumerBean> mapped() {
		return mapped;
	}
	
}
