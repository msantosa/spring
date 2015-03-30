package org.jobeet.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

public interface IGenericDAO<T> {
	public void saveOrUpdate(T object);
    public void delete(T object);
	public T findByPK(Class<T> typeClass,Serializable id);
	public List<T> findByExample(Class<T> typeClass, T object);
	public List<T> getAll(Class<T> typeClass);
	public List<T> getAllByQuery(String query, Map<String, Object> params);
}
