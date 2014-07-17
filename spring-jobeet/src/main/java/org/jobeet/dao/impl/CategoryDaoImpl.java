package org.jobeet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jobeet.dao.ICategoryDao;
import org.jobeet.model.JobeetCategory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDaoImpl extends HibernateDaoSupport implements ICategoryDao{
	
	private SessionFactory sessionFactory;
	
	public void addCategory(JobeetCategory categoria){
		
	}
	public JobeetCategory getCategoryById(int idCategoria){
		return null;
	}
	
	public List<JobeetCategory> listAllCategory(){
		return null;
	}
}
