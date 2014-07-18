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
		this.sessionFactory.getCurrentSession().save(trabajo);
		LOGGER.info("JobDaoImpl --> Salida en añadir job");

	}

	public JobeetJob getJobById(int idTrabajo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<JobeetJob> listAllJob() {
		// TODO Auto-generated method stub
		return null;
	}

}
