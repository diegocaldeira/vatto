package com.aoks.security.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.security.control.bean.UserBean;
import com.aoks.utils.webmvc.ModelConverter;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@FacesConverter("userConverter")
public class UserBeanConverter extends ModelConverter<UserBean> {
	
	private static Map<Long, UserBean> mapped = new HashMap<Long, UserBean>();

	@Override
	protected Map<Long, UserBean> mapped() {
		return mapped;
	}
	
}
