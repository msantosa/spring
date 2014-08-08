package org.jobeet.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jobeet.dao.ICategoryDao;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
	
	private ICategoryDao categoryDAO; 
	
	private IJobDao jobDAO; 
	
	public IJobDao getJobDAO() {
		return jobDAO;
	}
	public void setJobDAO(IJobDao jobDAO) {
		this.jobDAO = jobDAO;
	}

	
	public void addCategory(JobeetCategory categoria){
		
	}
	public JobeetCategory getCategoryById(int idCategoria){
		return null;
	}
	
	@Transactional(readOnly = false)
	public ICategoryDao getCategoryDAO() {
		return categoryDAO;
	}
	
	@Transactional(readOnly = true)
	public void setCategoryDAO(ICategoryDao categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	@Transactional(readOnly = true)
	public List listAllCategory(){
		LOGGER.info("CategoryServiceImpl listAllCategory <-- Entrada");
		List listaCategoria= getCategoryDAO().listAllCategory();
		LOGGER.info("CategoryServiceImpl listAllCategory --> Salida");
		return listaCategoria;
	}
	
	@Transactional(readOnly=true)
	public List<JobeetCategory> trabajosActivosXCategoria(){
		LOGGER.info("JobServiceImpl --> Entrada listarTrabajosActivos");
		List<JobeetCategory> listaCategoria= getCategoryDAO().listAllCategory();
		List<JobeetCategory> listaCategoriaTrabajo=new ArrayList<JobeetCategory>();
		JobeetCategory categoriaAux=null;
		Set<JobeetJob> trabajosAux=null;
		
		for(int i=0; i<listaCategoria.size();i++){
			categoriaAux=((JobeetCategory)listaCategoria.get(i));
			List<JobeetJob> listaTrabajosActivos=getJobDAO().trabajosActivosCategoria(categoriaAux);
			trabajosAux=new HashSet<JobeetJob>(listaTrabajosActivos);
			categoriaAux.setTrabajos(trabajosAux);
			listaCategoriaTrabajo.add(categoriaAux);
			categoriaAux=null;
			trabajosAux=null;
		}
		
		return listaCategoriaTrabajo;
	}
}
