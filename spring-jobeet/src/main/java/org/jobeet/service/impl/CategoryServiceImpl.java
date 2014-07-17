package org.jobeet.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.jobeet.dao.ICategoryDao;
import org.jobeet.dao.impl.CategoryDaoImpl;
import org.jobeet.model.JobeetCategory;
import org.jobeet.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{
	
	private ICategoryDao categoryDAO; 
	
	private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
	
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
	public List listAllCategory(){
		LOGGER.debug("CategoryServiceImpl listAllCategory <-- Entrada");
		List listaCategoria= getCategoryDAO().listAllCategory();
		LOGGER.debug("CategoryServiceImpl listAllCategory --> Salida");
		return listaCategoria;
	}
}
