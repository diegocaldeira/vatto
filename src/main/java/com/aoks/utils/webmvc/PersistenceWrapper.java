package com.aoks.utils.webmvc;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
public class PersistenceWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PersistenceWrapper.class);

	@PersistenceContext(name="em", unitName="em", type=PersistenceContextType.EXTENDED)
	private transient EntityManager em;
	

	public void saveOrUpdate(GenericModel entity){
		if (entity.getId() == 0)
			em.persist(entity);
		else 
			em.merge(entity);
	}
	
	public void delete(GenericModel entity){
		try {
			GenericModel merged = em.merge(entity);
			em.remove(merged);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> clazz, int maxResults){
		
		final String name = clazz.getAnnotation(Entity.class).name().equals("") ? clazz.getSimpleName() : clazz.getAnnotation(Entity.class).name();
		String queryString = "Select x from " + name + " x";
		
		logger.debug("Executing list() with query '{}'", queryString);
		
		Query query = em.createQuery(queryString);
		if (maxResults > -1)
			query.setMaxResults(maxResults);
		
		return query.getResultList();
			
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> clazz, String attr, String param, int start, int maxResults){
		
		final String name = clazz.getSimpleName();
		String queryString = "Select x from " + name + " x where x." + attr + " = '" + param + "'";
		
		logger.debug("Executing list() with query '{}'", queryString);
		
		Query query = em.createQuery(queryString);
		if (start > -1 && maxResults > -1){
			query.setFirstResult(start);
			query.setMaxResults(maxResults);
		}
		
		return query.getResultList();
			
	}
	
	public <T> T get(Class<T> clazz, long id){
		
		T find = em.find(clazz, id);
		return find;
		
	}
	
	public <T> T get(Class<T> clazz, long id, String lazyMethod){
		
		T find = em.find(clazz, id);
		
		try {
			Method method = find.getClass().getMethod(lazyMethod, new Class<?>[]{});
			method.invoke(find, new Object[]{});
		} catch (NoSuchMethodException	 e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return find;
		
	}
	
	
	public Object get(String table, String param, String value, String property){
		String queryString = "Select x." + property + " from " + table + " x where x." + param + " = '" + value + "'";
		Query query = em.createNativeQuery(queryString);
		Object t = null;
		
		try {
			if(!query.getResultList().isEmpty()){
				t = query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, String param, String value){
		
		final String name = clazz.getAnnotation(Entity.class).name().equals("") ? clazz.getSimpleName() : clazz.getAnnotation(Entity.class).name();
		String queryString = "Select x from " + name + " x where x." + param + " = '" + value + "'";
		
		logger.debug("Executing query '{}'", queryString);
		
		Query query = em.createQuery(queryString);
		
		T t = null;
		
		try {
			t = (T) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<T> clazz, String param, String value){
		
		final String name = clazz.getAnnotation(Entity.class).name().equals("") ? clazz.getSimpleName() : clazz.getAnnotation(Entity.class).name();
		String queryString = "Select x from " + name + " x where x." + param + " = '" + value + "'";
		
		logger.debug("Executing query '{}'", queryString);
		
		Query query = em.createQuery(queryString);

		List<T> t = new ArrayList<T>();
		
		// TODO improve this, please!
		try {
			t = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				T to = (T) query.getSingleResult();
				t.add(to);	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return t;
		
	}
	
	
	/**
     * Returns the number of total records
     * 
	 * @param <T>
     * @param namedQueryName
     * @return int
     */
    public <T> int countTotalRecord(Class<T> clazz) {
    	final String name = clazz.getAnnotation(Entity.class).name().equals("") ? clazz.getSimpleName() : clazz.getAnnotation(Entity.class).name();
		String queryString = "Select count(*) from " + name + " x";
    	
        Query query = em.createQuery(queryString);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }
    
    
	public <T> T loadLazyProperty(T entity, String methodName){
		
		T merged = em.merge(entity);
		
		try {
			Method method = entity.getClass().getMethod(methodName, new Class[]{});
			method.invoke(merged, new Object[]{});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return merged;
		
	}

	public EntityManager getEm() { return em; }
	
}
