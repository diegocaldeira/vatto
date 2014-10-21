package com.aoks.utils.webmvc;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public abstract class GenericDataModel<T extends GenericBean<?>> extends ListDataModel<T> implements SelectableDataModel<T>{

	protected List<T> filteredBeans = new ArrayList<T>();
	
	public GenericDataModel(){
		
	}
	
	public GenericDataModel(List<T> beans){
		super(beans);
		filteredBeans = beans;
	}
	
	@Override  
    public T getRowData(String rowKey) {  
        @SuppressWarnings("unchecked")
		List<T> beans = (List<T>) getWrappedData();  
          
        for(T bean : beans) {  
            if(String.valueOf(bean.getId()).equals(rowKey))  
                return bean;  
        }  
          
        return null;  
    }  
	
	
	@Override  
    public Object getRowKey(T bean) {  
        return bean.getId();  
    } 
	
	
	public List<T> getFilteredBeans() { return filteredBeans; }
	public void setFilteredBeans(List<T> filteredBeans) { this.filteredBeans = filteredBeans; }
	
}
