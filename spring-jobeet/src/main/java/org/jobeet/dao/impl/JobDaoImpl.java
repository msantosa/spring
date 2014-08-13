package org.jobeet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.springframework.stereotype.Repository;

@Repository
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

		trabajo.setCreated_at(dt);

		this.sessionFactory.getCurrentSession().save(trabajo);
		LOGGER.info("JobDaoImpl --> Salida en añadir job");

	}
	
	public void deleteJob(JobeetJob trabajo) {
		// TODO Auto-generated method stub
		LOGGER.info("JobDaoImpl --> Entrada en deleteJob");
		sessionFactory.getCurrentSession().delete(trabajo);
		LOGGER.info("JobDaoImpl --> Salida en deleteJob");
		
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
	
	public List<JobeetJob> listarTrabajosActivos(){
		LOGGER.info("JobDaoImpl listarTrabajosActivos <-- Entrada");
		
		java.util.Date hoy = new java.util.Date();
		
		List listaTrabajos=null;
			listaTrabajos = sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
					.add(Restrictions.ge("expires_at", hoy))
					.addOrder( Property.forName("expires_at").desc()).list();
			
		LOGGER.info("JobDaoImpl listarTrabajosActivos <-- Salida");
		
		return listaTrabajos;
	}

	public List<JobeetJob> trabajosActivosCategoria(JobeetCategory categoria) {
		LOGGER.info("trabajosActivosCategoria <-- Entrada");

		java.util.Date hoy = new java.util.Date();
		
		List listaTrabajos=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
				.add(Restrictions.ge("expires_at", hoy))
				.add(Restrictions.eq("category", categoria))
				.addOrder( Property.forName("expires_at").desc() )     
				.setMaxResults(10).list();
		
		
		
		LOGGER.info("trabajosActivosCategoria <-- Salida");
		return listaTrabajos;
	}

	public int numTrabajosActivosCategoria(JobeetCategory categoria) {
		// TODO Auto-generated method stub
		LOGGER.info("trabajosActivosCategoria <-- Entrada");

		java.util.Date hoy = new java.util.Date();
		
		Integer numTrabajos=(Integer) sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("expires_at", hoy))
				.add(Restrictions.eq("category", categoria)).uniqueResult();
		
		
		LOGGER.info("trabajosActivosCategoria <-- Salida");
		
		return numTrabajos.intValue();
	}

}
