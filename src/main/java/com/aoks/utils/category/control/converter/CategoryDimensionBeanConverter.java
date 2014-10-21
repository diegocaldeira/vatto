package com.aoks.utils.category.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.utils.category.control.bean.CategoryDimensionBean;
import com.aoks.utils.webmvc.ModelConverter;


/**
 * 
 * @author Diego Pereira
 *
 */
@FacesConverter("categoryDimensionConverter")
public class CategoryDimensionBeanConverter extends ModelConverter<CategoryDimensionBean> {
	
	private static Map<Long, CategoryDimensionBean> mapped = new HashMap<Long, CategoryDimensionBean>();

	@Override
	protected Map<Long, CategoryDimensionBean> mapped() {
		return mapped;
	}
}
