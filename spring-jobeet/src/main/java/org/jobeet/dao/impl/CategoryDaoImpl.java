package org.jobeet.dao.impl;

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
		
	}

	public JobeetCategory getCategoryById(int idCategoria){
		return null;
	}
	
	public List listAllCategory(){
		/*DetachedCriteria criteria = DetachedCriteria.forClass(JobeetCategory.class);
		return getHibernateTemplate().findByCriteria(criteria);*/
		LOGGER.info("CategoryDaoImpl listAllCategory <-- Entrada");
		List listaCategoria=sessionFactory.getCurrentSession().createCriteria(JobeetCategory.class).list();
		LOGGER.info("Tamaño de lista="+listaCategoria.size());
		LOGGER.info("CategoryDaoImpl listAllCategory --> Salida");
		return listaCategoria;
	}
}
