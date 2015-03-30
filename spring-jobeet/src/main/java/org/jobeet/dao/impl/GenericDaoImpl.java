package org.jobeet.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.jobeet.dao.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDaoImpl<T> implements IGenericDAO<T>{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveOrUpdate(T object) {
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void delete(T object) {
		sessionFactory.getCurrentSession().delete(object);
		
	}

	@Override
	public T findByPK(Class<T> typeClass, Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(typeClass, id);
	}

	@Override
	public List<T> findByExample(Class<T> typeClass, T object) {
		Example ejemplo=Example.create(object)
				.enableLike()
				.ignoreCase();
		
		List<T> listTrabajos=sessionFactory.getCurrentSession().createCriteria(typeClass).add(ejemplo).list();

		return listTrabajos;
	}

	@Override
	public List<T> getAll(Class<T> typeClass) {
		return sessionFactory.getCurrentSession().createCriteria(typeClass).list();
	}
	
	public List<T> getAllByQuery(String query, Map<String, Object> params) {
		Query qQuery = createQuery(query, params);
		return qQuery.getResultList();
	}	
	
	private Query createQuery(String query, Map<String, Object> params) {
		Query qQuery = (Query) sessionFactory.getCurrentSession().createQuery(query);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				qQuery.setParameter(key, value);
			}
		}
		return qQuery;
	}
	
	@Transactional(readOnly = true)
	protected List<T> getByQuery(String query) {
		return ((Query) sessionFactory.getCurrentSession().createQuery(query)).getResultList();
	}

}
