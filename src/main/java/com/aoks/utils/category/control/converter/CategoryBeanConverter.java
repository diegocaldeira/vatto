package com.aoks.utils.category.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.webmvc.ModelConverter;

/**
 * Converter to Category
 * 
 * @author Diego Pereira
 *
 */
@FacesConverter("categoryConverter")
public class CategoryBeanConverter extends ModelConverter<CategoryBean> {

	private static Map<Long, CategoryBean> mapped = new HashMap<Long, CategoryBean>();
	
	@Override
	protected Map<Long, CategoryBean> mapped() {
		return mapped;
	}
	
}
