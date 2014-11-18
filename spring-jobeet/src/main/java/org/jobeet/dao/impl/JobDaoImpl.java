package org.jobeet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.utilidades.JavaSHA1Hash;
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
		String token="";
		String tokenCifrado="";
		
		java.util.Date dt = new java.util.Date();

		trabajo.setCreated_at(dt);
		
		token=trabajo.getEmail()+(int)Math.floor(Math.random()*(11111-99999+1)+99999);
		LOGGER.debug("Token del trabajo="+token);
		
		tokenCifrado=JavaSHA1Hash.sha1(token);
		LOGGER.debug("Token Cifrado="+tokenCifrado);
		
		trabajo.setToken(tokenCifrado);

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
	
	public List<JobeetJob> getJobByExample(JobeetJob trabajoBuscar) {
		LOGGER.info("JobDaoImpl --> Entrada en getJobByExample");
		
		/*Los boolean siempre están presentes en la query. Si no se definen por defecto se setean a false*/
		Example ejemplo=Example.create(trabajoBuscar)
				.enableLike()
				.ignoreCase();
		
		List<JobeetJob> listTrabajos=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class).add(ejemplo).list();
		
		return listTrabajos;
	}

	public List<JobeetJob> listAllJob() {
		LOGGER.info("JobDaoImpl listAllJob <-- Entrada");
		List listaTrabajo=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class).list();
		LOGGER.info("JobDaoImpl listAllCategory --> Salida");
		return listaTrabajo;
	}
	
	public List<JobeetJob> listarTrabajosActivos(){
		LOGGER.info("JobDaoImpl listarTrabajosActivos <-- Entrada");
		
		java.util.Date hoy = new java.util.Date();
		
		List listaTrabajos=null;
			listaTrabajos = sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
					.add(Restrictions.ge("expires_at", hoy))
					.add(Restrictions.eq("is_activated", true))
					.addOrder( Property.forName("expires_at").desc()).list();
			
		LOGGER.info("JobDaoImpl listarTrabajosActivos <-- Salida");
		
		return listaTrabajos;
	}

	public List<JobeetJob> trabajosActivosCategoria(JobeetCategory categoria,int tamanioPagina) {
		LOGGER.info("trabajosActivosCategoria <-- Entrada");

		java.util.Date hoy = new java.util.Date();
		
		Criteria query=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class);
		
		query.add(Restrictions.ge("expires_at", hoy))
				.add(Restrictions.eq("category", categoria))
				.add(Restrictions.eq("is_activated", true))
				.addOrder( Property.forName("expires_at").desc());
		
		//Si tamanio es igual a -1 se muestran todos los trabajos activos
		if(tamanioPagina!=-1){
			query.setMaxResults(tamanioPagina);
		}
			
		List listaTrabajos=query.list();
				
		LOGGER.info("trabajosActivosCategoria <-- Salida");
		return listaTrabajos;
	}

	public List<JobeetJob> trabajosActivosPaginado(JobeetCategory categoria, int tamanioPagina, int numPagina) {
		LOGGER.info("trabajosActivosPaginado <-- Entrada");

		java.util.Date hoy = new java.util.Date();
		
		List listaTrabajos=sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
				.add(Restrictions.ge("expires_at", hoy))
				.add(Restrictions.eq("category", categoria))
				.add(Restrictions.eq("is_activated", true))
				.setFirstResult((numPagina-1)*tamanioPagina)
				.setMaxResults(tamanioPagina)
				.list();
				
		LOGGER.info("trabajosActivosPaginado <-- Salida");
		return listaTrabajos;
	}
	
	public int numTrabajosActivosCategoria(JobeetCategory categoria) {
		// TODO Auto-generated method stub
		LOGGER.info("trabajosActivosCategoria <-- Entrada");

		java.util.Date hoy = new java.util.Date();
		
		Integer numTrabajos=(Integer) sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("expires_at", hoy))
				.add(Restrictions.eq("is_activated", true))
				.add(Restrictions.eq("category", categoria)).uniqueResult();
		
		
		LOGGER.info("trabajosActivosCategoria <-- Salida");
		
		return numTrabajos.intValue();
	}
	
	public boolean validarToken(int idTrabajo, String token) {
		boolean valido=false;
		
		// TODO Auto-generated method stub
		LOGGER.info("validarToken <-- Entrada");

		JobeetJob trabajo=(JobeetJob)sessionFactory.getCurrentSession().createCriteria(JobeetJob.class)
				.add(Restrictions.eq("id",idTrabajo))
				.add(Restrictions.eq("token", token))
				.uniqueResult();
		
		if(trabajo!=null){
			valido=true;
		}
		
		LOGGER.info("valido="+valido);
		LOGGER.info("validarToken <-- Salida");
		
		return valido;
	}

}
