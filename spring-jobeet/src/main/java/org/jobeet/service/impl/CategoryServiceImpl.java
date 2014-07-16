package org.jobeet.service.impl;

import java.util.List;

import org.jobeet.dao.ICategoryDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{
	
	private ICategoryDao categoryDAO; 
	
	public void addCategory(JobeetCategory categoria){
		
	}
	public JobeetCategory getCategoryById(int idCategoria){
		return null;
	}
	public ICategoryDao getCategoryDAO() {
		return categoryDAO;
	}
	public void setCategoryDAO(ICategoryDao categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	public List<JobeetCategory> listAllCategory(){
		return null;
	}
}
