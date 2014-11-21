package com.aoks.utils.webmvc;

import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import com.aoks.security.control.SecureSessionController;
import com.aoks.security.model.AuditEntity;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 * @param <T>
 */
public abstract class AbstractManager<T extends GenericModel> implements ModelManager<T> {

	private static final long serialVersionUID = 1L;

	protected T model;
	
	protected AbstractManager() {}
	
	@Inject protected PersistenceWrapper wrapper;
	@Inject protected SecureSessionController secureSession;
	
	@Override
	public AbstractManager<T> load(T obj) {
		this.model = obj;
		return this;
	}
	
	@Override
	public AbstractManager<T> load(long id) {
		model = wrapper.get(getModelClazz(), id);
		return this;
	}
	
	@Override
	public AbstractManager<T> save(){
		if(secureSession.isLogged()){
			verifyState();
			
			if(model.getAuditEntity() == null)
				model.setAuditEntity(new AuditEntity());
			else
				if(model.getAuditEntity().getId() != 0L)
					model.getAuditEntity().getModifyDates().add(GregorianCalendar.getInstance());
			
			if(model.getId() == 0L){
				model.getAuditEntity().setUser(secureSession.getLoggedBean().getModel());
			}else{
				if(model.getAuditEntity().getUser() == null)
					model.getAuditEntity().setUser(secureSession.getLoggedBean().getModel());
				else
					model.getAuditEntity().getUsers().add(secureSession.getLoggedBean().getModel());
			}
			
			wrapper.saveOrUpdate(model);
		}
		
		return this;
	}
	
	@Override
	public ModelManager<T> create() {
		model = createModel();
		return this;
	}

	@Override
	public List<T> list() {
		List<T> list = list(-1);
		return list;
	}

	@Override
	public List<T> list(int maxresults) {
		List<T> list = wrapper.list(getModelClazz(), maxresults);
		return list;
	}

	@Override
	public T getModel() {
		return model;
	}
	
	@Override
	public void verifyState() {
		if (model == null)
			throw new IllegalStateException();
	}
	
	@Override
	public ModelManager<T> remove() {
		wrapper.delete(model);
		return this;
	}

	@Override
	public T createModel() {
		try {
			return getModelClazz().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

	public PersistenceWrapper getWrapper(){
		return wrapper;
	}
}
