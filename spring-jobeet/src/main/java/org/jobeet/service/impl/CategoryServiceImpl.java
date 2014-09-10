package org.jobeet.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jobeet.config.AppConfig;
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

	
	@Transactional(readOnly = false)
	public void addCategory(JobeetCategory categoria){
		
	}
	
	@Transactional(readOnly = true)
	public JobeetCategory getCategoryById(int idCategoria){
		return getCategoryDAO().getCategoryById(idCategoria);
	}
	
	@Transactional(readOnly = true)
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
			//Obtenemos el listado de categorias a mostrar en la patanlla principal
			List<JobeetJob> listaTrabajosActivos=getJobDAO().trabajosActivosCategoria(categoriaAux,AppConfig.getMaxTrabajosIndex());
			trabajosAux=new HashSet<JobeetJob>(listaTrabajosActivos);
			categoriaAux.setTrabajos(trabajosAux);
			
			listaCategoriaTrabajo.add(categoriaAux);
			categoriaAux=null;
			trabajosAux=null;
		}
		
		return listaCategoriaTrabajo;
	}
	
	@Transactional(readOnly=true)
	public List<JobeetJob> trabajosActivosCategoria(JobeetCategory categoria,int numPagina){
		return getJobDAO().trabajosActivosPaginado(categoria, AppConfig.getMaxTrabajosCategoria(), numPagina);
	}
	
	@Transactional(readOnly=true)
	public int numTrabajosActivosCategoria(JobeetCategory categoria){
		return getJobDAO().numTrabajosActivosCategoria(categoria);
	}
}
