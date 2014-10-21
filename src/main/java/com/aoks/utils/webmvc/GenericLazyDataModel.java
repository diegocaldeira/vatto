package com.aoks.utils.webmvc;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 * @param <U>
 * @param <M>
 */
public abstract class GenericLazyDataModel<U extends GenericBean<M>, M extends GenericModel> extends LazyDataModel<U> implements SelectableDataModel<U>{

	private static final long serialVersionUID = 1L;
	
	protected List<U> filteredBeans = new ArrayList<U>();
    private String partnerType;
	
	
	public GenericLazyDataModel(){

	}
	
//	  @Override
//    public List<U> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
//
//		List<U> beans = new ArrayList<U>();
//		List<M> entities = (List<M>) getManager().getWrapper().list(getModelClazz(), "type", partnerType, first, first + pageSize);
//		
//		for(M partner : entities){
//			try {
//				U bean = getBeanClazz().newInstance();
//				beans.add((U) bean.load(partner));
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch(IllegalStateException e){
//				e.printStackTrace();
//			}
//		}
//		
////		Collections.sort(beans);
//		filteredBeans = beans;
//		
//        setRowCount(getManager().getWrapper().countTotalRecord(getModelClazz()));
//        return filteredBeans;
//    }
	

	@Override public Object getRowKey(U bean) {	return bean.getId(); } 
	
	
    @Override
    public U getRowData(String rowKey) {
        if(filteredBeans == null)
            return null;
       for(U t : filteredBeans) {
           if(String.valueOf(t.getId()).equals(rowKey))
           return t;
       }
       return null;
    }

    
	public List<U> getFilteredBeans() 					{ return filteredBeans; }
	public void setFilteredBeans(List<U> filteredBeans) { this.filteredBeans = filteredBeans; }
	

	public String getPartnerType() 						{ return partnerType; }
	public void setPartnerType(String partnerType) 		{ this.partnerType = partnerType; }

	
	public abstract Class<M> getModelClazz();
	public abstract Class<U> getBeanClazz();
	public abstract AbstractManager<M> getManager();
}
