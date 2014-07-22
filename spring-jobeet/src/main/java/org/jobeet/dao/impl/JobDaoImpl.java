package org.jobeet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetJob;

public class JobDaoImpl implements IJobDao {

	private SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(JobDaoImpl.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addJob(JobeetJob trabajo) {
		// TODO Auto-generated method stub
		LOGGER.info("JobDaoImpl --> Entrada en añadir job");
		
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String currentTime = sdf.format(dt);
		
		System.out.println("CURRENTTIME="+currentTime);
		
		/**REVISAR COMO CONTROLAR EXCEPCIONES EN DAO**/
		
		try {
			trabajo.setCreated_at(sdf.parse(currentTime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DAO addJob failed", e);
		}
		this.sessionFactory.getCurrentSession().save(trabajo);
		LOGGER.info("JobDaoImpl --> Salida en añadir job");

	}
	
	public void deleteJob(JobeetJob trabajo) {
		// TODO Auto-generated method stub
		
	}

	public JobeetJob getJobById(int idTrabajo) {
		LOGGER.info("JobDaoImpl --> Entrada en getJobById");
		return (JobeetJob) sessionFactory.getCurrentSession().get(JobeetJob.class, idTrabajo);
	
	}

	public List<JobeetJob> listAllJob() {
		LOGGER.info("JobDaoImpl listAllJob <-- Entrada");
		List listaTrabajo=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class).list();
		if(listaTrabajo!=null)
			LOGGER.info("Tamaño de lista="+listaTrabajo.size());
		LOGGER.info("JobDaoImpl listAllCategory --> Salida");
		return listaTrabajo;
	}

}
