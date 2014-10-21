package com.aoks.banking.operation.control.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.convert.FacesConverter;

import com.aoks.banking.operation.control.bean.CheckingAccountBean;
import com.aoks.utils.webmvc.ModelConverter;


@FacesConverter("checkingAccountConverter")
public class CheckingAccountBeanConverter extends ModelConverter<CheckingAccountBean> {
	
	private static Map<Long, CheckingAccountBean> mapped = new HashMap<Long, CheckingAccountBean>();

	@Override
	protected Map<Long, CheckingAccountBean> mapped() {
		return mapped;
	}
	
}
