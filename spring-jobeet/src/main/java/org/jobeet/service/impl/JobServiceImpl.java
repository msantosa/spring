package org.jobeet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jobbet.beans.JobBean;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.IJobService;
import org.jobeet.utilidades.JavaSHA1Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobServiceImpl implements IJobService{
	
	private SessionFactory sessionFactory;
	
	private IJobDao jobDAO; 
	
	private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public IJobDao getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(IJobDao jobDAO) {
		this.jobDAO = jobDAO;
	}

	@Transactional(readOnly=false)
	public void addJob(JobeetJob trabajo){
		LOGGER.info("JobServiceImpl --> Entrada en añadir job");
		getJobDAO().addJob(trabajo);
		LOGGER.info("JobServiceImpl --> Entrada en añadir job");
	}
	
	@Transactional(readOnly=true)
	public JobeetJob getJobById(int idJob){
		LOGGER.info("Entrada a getJobById. Id="+ idJob);
		return jobDAO.getJobById(idJob);
	}
	
	@Transactional(readOnly=true)
	public List<JobeetJob> listAllJob(){
		return getJobDAO().listAllJob();
	}
	
	@Transactional(readOnly=true)
	public List<JobeetJob> listarTrabajosActivos(){
		LOGGER.info("JobServiceImpl --> Entrada listarTrabajosActivos");
		return getJobDAO().listarTrabajosActivos();
	}
	
	@Transactional(readOnly=true)
	public JobeetJob getJobValidadoEdicion(int idTrabajo,String token){
		LOGGER.info("Entrada a getJobValidadoEdicion. Id="+ idTrabajo+" token="+token);
		JobeetJob trabajo=null;
		
		LOGGER.debug("El token introducido es:"+token);
		LOGGER.debug("El token encriptado es:"+ JavaSHA1Hash.sha1(token));
		
		
		if(jobDAO.validarToken(idTrabajo, JavaSHA1Hash.sha1(token))){
			LOGGER.info("Se ha validado que el token introducido se corresponde con el trabajo");
			trabajo=jobDAO.getJobById(idTrabajo);
		}
		
		LOGGER.info("Salida a getJobValidadoEdicion. Id="+ idTrabajo+" token="+token);
		return trabajo;
	}
	
	@Transactional(readOnly=true)
	public List<JobBean> getJobByExample(JobeetJob trabajoBuscar){
		LOGGER.info("Entrada a getJobByExample");
		List<JobBean> listaTrabajos=new ArrayList();
		List<JobeetJob> listadoTrabajosDAO=jobDAO.getJobByExample(trabajoBuscar);
		
		for(JobeetJob trabajoAux : listadoTrabajosDAO){
			JobBean trabajoBean=new JobBean();
			trabajoBean.setId(trabajoAux.getId());
			/*trabajoBean.setCategory(trabajoAux.getCategory());*/
			trabajoBean.setType(trabajoAux.getType());
			trabajoBean.setCompany(trabajoAux.getCompany());
			trabajoBean.setLogo(trabajoAux.getLogo());
			trabajoBean.setUrl(trabajoAux.getLogo());
			trabajoBean.setPosition(trabajoAux.getPosition());
			trabajoBean.setLocation(trabajoAux.getLocation());
			trabajoBean.setDescription(trabajoAux.getDescription());
			trabajoBean.setHow_to_apply(trabajoAux.getHow_to_apply());
			trabajoBean.setToken(trabajoAux.getToken());
			trabajoBean.setIs_public(trabajoAux.isIs_public());
			trabajoBean.setIs_activated(trabajoAux.isIs_activated());
			trabajoBean.setEmail(trabajoAux.getEmail());
			trabajoBean.setExpires_at(trabajoAux.getExpires_at());
			trabajoBean.setCreated_at(trabajoAux.getCreated_at());
			trabajoBean.setUpdated_at(trabajoAux.getUpdated_at());
			
			listaTrabajos.add(trabajoBean);
		}
		
		
		LOGGER.info("Salida a getJobByExample");
		return listaTrabajos;
	}
	
	@Transactional(readOnly=true)
	public List<JobBean> buscarTrabajoPatron(String patronBusqueda){
		LOGGER.info("Entrada a buscarTrabajoPatron");
		List<JobBean> listaTrabajos=new ArrayList();
		
		/*Location*/
		JobeetJob trabajoBusqueda=new JobeetJob();
		trabajoBusqueda.setLocation(patronBusqueda+"%");
		trabajoBusqueda.setIs_public(true);
		trabajoBusqueda.setIs_activated(true);
		
		List<JobeetJob> listadoTrabajosDAO=jobDAO.getJobByExample(trabajoBusqueda);
		
		for(JobeetJob trabajoAux : listadoTrabajosDAO){
			JobBean trabajoBean=parsearJobeetJob(trabajoAux);
			listaTrabajos.add(trabajoBean);
		}
		
		/*Position*/
		trabajoBusqueda=new JobeetJob();
		trabajoBusqueda.setPosition(patronBusqueda+"%");
		trabajoBusqueda.setIs_public(true);
		trabajoBusqueda.setIs_activated(true);
		
		listadoTrabajosDAO=jobDAO.getJobByExample(trabajoBusqueda);
		
		for(JobeetJob trabajoAux : listadoTrabajosDAO){
			JobBean trabajoBean=parsearJobeetJob(trabajoAux);
			listaTrabajos.add(trabajoBean);
		}
		
		/*Company*/
		trabajoBusqueda=new JobeetJob();
		trabajoBusqueda.setCompany(patronBusqueda+"%");
		trabajoBusqueda.setIs_public(true);
		trabajoBusqueda.setIs_activated(true);
		
		listadoTrabajosDAO=jobDAO.getJobByExample(trabajoBusqueda);
		
		for(JobeetJob trabajoAux : listadoTrabajosDAO){
			JobBean trabajoBean=parsearJobeetJob(trabajoAux);
			listaTrabajos.add(trabajoBean);
		}
		
		
		LOGGER.info("Salida a buscarTrabajoPatron");
		return listaTrabajos;
	}
	
	public JobBean parsearJobeetJob(JobeetJob trabajoAux){
		JobBean trabajoBean=new JobBean();
		trabajoBean.setId(trabajoAux.getId());
		/*trabajoBean.setCategory(trabajoAux.getCategory());*/
		trabajoBean.setType(trabajoAux.getType());
		trabajoBean.setCompany(trabajoAux.getCompany());
		trabajoBean.setLogo(trabajoAux.getLogo());
		trabajoBean.setUrl(trabajoAux.getLogo());
		trabajoBean.setPosition(trabajoAux.getPosition());
		trabajoBean.setLocation(trabajoAux.getLocation());
		trabajoBean.setDescription(trabajoAux.getDescription());
		trabajoBean.setHow_to_apply(trabajoAux.getHow_to_apply());
		trabajoBean.setToken(trabajoAux.getToken());
		trabajoBean.setIs_public(trabajoAux.isIs_public());
		trabajoBean.setIs_activated(trabajoAux.isIs_activated());
		trabajoBean.setEmail(trabajoAux.getEmail());
		trabajoBean.setExpires_at(trabajoAux.getExpires_at());
		trabajoBean.setCreated_at(trabajoAux.getCreated_at());
		trabajoBean.setUpdated_at(trabajoAux.getUpdated_at());
		
		return trabajoBean;
	}
}
