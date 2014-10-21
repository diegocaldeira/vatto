package com.aoks.budget.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.aoks.budget.model.FlowItem;
import com.aoks.budget.model.FlowItemType;
import com.aoks.utils.webmvc.AbstractManager;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@RequestScoped
public class FlowItemManager extends AbstractManager<FlowItem> {

	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * @param flowType
	 * @param flowItemType
	 * @return
	 */
	public List<FlowItem> listByFlowType(String flowType, String flowItemType){
		FlowItemType type = FlowItemType.valueOf(flowItemType);
		switch(flowType){
			case "EXPENSE"	 : return listDebit(type);
			case "INCOME"	 : return listCredit(type);
			case "TRANSFER"  : return listTransfer(type);
		}
		return null;
	}
	

	/**
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FlowItem> listDebit(FlowItemType type){
		Query query = wrapper.getEm().createQuery("from FlowItem x where x.type = :type and x.credit = false");
		query.setParameter("type", type);
		return query.getResultList();
	}
	
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FlowItem> listCredit(FlowItemType type){
		Query query = wrapper.getEm().createQuery("from FlowItem x where x.type = :type and x.credit = true");
		query.setParameter("type", type);
		return query.getResultList();
	}
	
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FlowItem> listTransfer(FlowItemType type){
		Query query = wrapper.getEm().createQuery("from FlowItem x where x.type = :type");
		query.setParameter("type", type);
		return query.getResultList();
	}
	
	
	@Override
	public Class<FlowItem> getModelClazz() {
		return FlowItem.class;
	}
	
}