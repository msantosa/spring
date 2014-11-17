package org.jobeet.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.IJobService;
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
		JobeetJob trabajo=null;;
		
		if(jobDAO.validarToken(idTrabajo, token)){
			trabajo=jobDAO.getJobById(idTrabajo);
		}
		
		LOGGER.info("Entrada a getJobValidadoEdicion. Id="+ idTrabajo+" token="+token);
		return trabajo;
	}
}
