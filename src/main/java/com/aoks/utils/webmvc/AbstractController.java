package com.aoks.utils.webmvc;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 * @param <M>
 * @param <U>
 */
public abstract class AbstractController<M extends GenericModel, U extends GenericBean<M>> implements ActionController {

	private static final long serialVersionUID = 1L;
	
	protected List<U> beans;
	protected U bean;
	
	@Override
	public void save() {
		doPreSave();
		saveCore();
		doPostSave();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("Salvo com sucesso!", "" + getManager().createModel().getClass().getSimpleName()));
	}
	
	protected void doPreSave(){
		
	}

	protected void doPostSave(){
		bean = null;
	}
	
	protected void saveCore(){
		
		AbstractManager<M> manager = getManager();
		manager.load(getAsModel(bean));
		manager.save();
		
		if(getDataModel() != null){
			if(!getDataModel().getFilteredBeans().contains(bean)){
				getDataModel().getFilteredBeans().add(bean);
			}
		}
			
	}

	@Override
	public void delete() {
		if (bean != null)
			getManager().load((M) bean.build(getFactory())).remove();
		
		if(getDataModel() != null){
			if(getDataModel().getFilteredBeans().contains(bean)){
				getDataModel().getFilteredBeans().remove(bean);
			}
		}
		
		bean = null;
	}
	
	public void deleteAll(){
		List<U> uBeans = new ArrayList<U>(beans);
		if(!uBeans.isEmpty()){
			for(U u : uBeans){
				getManager().load((M) u.build(getFactory())).remove();
				getDataModel().filteredBeans.remove(u);
			}
		}
		beans.clear();
	}

	@Override
	public void create() {
		bean = createBean();
	}

	@Override
	public void clean() {
		bean = null;
	}

	@Override
	public boolean has() {
		return bean != null;
	}
	

	public List<U> getBeans() {
		if(beans == null){
			beans = new ArrayList<U>();
		}
		return beans;
	}
	public void setBeans(List<U> beans) {
		this.beans = beans;
	}

	
	public U getBean() {
		return bean;
	}
	public void setBean(U bean) {
		if (bean != null)
			this.bean = bean;
		doAfterSetBean();
	}
	public void doAfterSetBean(){
		// do nothing
	}
	
	
	public List<U> updateBeans() {
		List<M> list = getManager().list();
		List<U> ret = new ArrayList<U>();
		for (M t : list) {
			if (t==null) continue;
			@SuppressWarnings("unchecked")
			U u = (U) createBean().load(t);
			ret.add(u);
		}
		return ret;
	}
	
	protected U createBean() {
		try {
			return getBeanClazz().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}
	
	public M getAsModel(U bean){
		return bean.build(getFactory());
	}
	

	public abstract Class<U> getBeanClazz();
	public abstract AbstractManager<M> getManager();
	public abstract GenericFactory<M> getFactory();
	public abstract GenericDataModel<U> getDataModel();
	
}
