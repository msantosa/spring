package org.jobeet.dao.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.jobeet.dao.ICategoryDao;
import org.jobeet.model.JobeetCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoryDaoImpl implements ICategoryDao{
	
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addCategory(JobeetCategory categoria){
		LOGGER.info("CategoryDaoImpl addCategory <-- Entrada");
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String currentTime = sdf.format(dt);
		
		System.out.println("CURRENTTIME="+currentTime);
		
		/**REVISAR COMO CONTROLAR EXCEPCIONES EN DAO**/
		
		try {
			categoria.setCreated_at(sdf.parse(currentTime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DAO addCategory failed", e);
		}
		sessionFactory.getCurrentSession().save(categoria);
		LOGGER.info("CategoryDaoImpl addCategory --> Salida");	
	}
	
	public void deleteCategory(JobeetCategory categoria) {
		// TODO Auto-generated method stub
		
	}

	public JobeetCategory getCategoryById(int idCategoria){
		/*
		session.load(Source.class, id);
		will throw an exception if there is no Source entity with the identifier id.
		getSession().get(Source.class, id);
		will return null if there is no Source entity with the identifier id.*/
		
		//return (JobeetCategory) sessionFactory.getCurrentSession().load(JobeetCategory.class, idCategoria);
		LOGGER.info("CategoryDaoImpl --> Entrada en getCategoryById");
		return (JobeetCategory) sessionFactory.getCurrentSession().get(JobeetCategory.class, idCategoria);
		
	}
	
	public List listAllCategory(){
		/*DetachedCriteria criteria = DetachedCriteria.forClass(JobeetCategory.class);
		return getHibernateTemplate().findByCriteria(criteria);*/
		LOGGER.info("CategoryDaoImpl listAllCategory <-- Entrada");
		List listaCategoria=sessionFactory.getCurrentSession().createCriteria(JobeetCategory.class).list();
		if(listaCategoria!=null)
			LOGGER.info("Tamaño de lista="+listaCategoria.size());
		LOGGER.info("CategoryDaoImpl listAllCategory --> Salida");
		return listaCategoria;
	}

}
