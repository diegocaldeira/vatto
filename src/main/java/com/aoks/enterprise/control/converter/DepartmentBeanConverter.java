package com.aoks.enterprise.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.enterprise.control.bean.DepartmentBean;
import com.aoks.utils.webmvc.ModelConverter;

@FacesConverter("departmentConverter")
public class DepartmentBeanConverter extends ModelConverter<DepartmentBean> {

	private static Map<Long, DepartmentBean> mapped = new HashMap<Long, DepartmentBean>();

	@Override
	protected Map<Long, DepartmentBean> mapped() {
		return mapped;
	}
	
}
