package org.jobeet.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jobeet.beans.JobBean;
import org.jobeet.config.AppConfig;
import org.jobeet.dao.IJobDao;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.IJobService;
import org.jobeet.utilidades.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	public String addJob(JobeetJob trabajo){
		LOGGER.info("JobServiceImpl --> Entrada en añadir job");
		String token="";
		String tokenCifrado="";

		java.util.Date dt = new java.util.Date();

		trabajo.setCreated_at(dt);
		trabajo.setUpdated_at(dt);

		token=trabajo.getEmail()+(int)Math.floor(Math.random()*(11111-99999+1)+99999);
		LOGGER.debug("Token del trabajo="+token);

		tokenCifrado=Utils.sha1(token);
		LOGGER.debug("Token Cifrado="+tokenCifrado);

		trabajo.setToken(tokenCifrado);

		getJobDAO().guardarJob(trabajo);
		LOGGER.info("JobServiceImpl --> Salida en añadir job");
		return token;
	}

	@Transactional(readOnly=false)
	public String addJob(JobeetJob trabajo,MultipartFile file) throws IOException{
		LOGGER.info("El identificador del job es "+trabajo.getId());
		LOGGER.info("El tipo del job es "+trabajo.getType());
		LOGGER.info("Category="+trabajo.getCategory());

		LOGGER.info("JobServiceImpl --> Entrada en añadir job");
		String token="";
		String tokenCifrado="";

		java.util.Date dt = new java.util.Date();

		trabajo.setCreated_at(dt);
		trabajo.setUpdated_at(dt);

		token=trabajo.getEmail()+(int)Math.floor(Math.random()*(11111-99999+1)+99999);
		LOGGER.debug("Token del trabajo="+token);

		tokenCifrado=Utils.sha1(token);
		LOGGER.debug("Token Cifrado="+tokenCifrado);

		trabajo.setToken(tokenCifrado);
		LOGGER.debug("Guardamos el trabajo");
		getJobDAO().guardarJob(trabajo);

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			String rootPath = AppConfig.rutaImagenesLogo();
			File dir = new File(rootPath);
			if (!dir.exists())
				dir.mkdirs();

			String fichero[]=file.getOriginalFilename().split("\\.");
			String extensionFichero=fichero[fichero.length-1];
			String nombreFichero="";

			for(int i=0;i<fichero.length-1;i++){
				nombreFichero+=fichero[i];
			}

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()+ File.separator + nombreFichero+"_"+trabajo.getId()+"."+extensionFichero);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			LOGGER.info("Server File Location="
					+ serverFile.getAbsolutePath());

			trabajo.setLogo(nombreFichero+"_"+trabajo.getId()+"."+extensionFichero);
		}

		LOGGER.debug("Actualizamos el logo");
		getJobDAO().guardarJob(trabajo);

		LOGGER.info("JobServiceImpl --> Salida en añadir job");
		return token;
	}

	@Transactional(readOnly=false)
	public void borrarTrabajo(int idTrabajo){
		LOGGER.info("JobServiceImpl --> Entrada en borrar job");
		LOGGER.debug("Obtenemos el trabajo con id="+idTrabajo);
		JobeetJob trabajo=getJobDAO().getJobById(idTrabajo);

		LOGGER.debug("Borramos el logo asociado al trabajo");
		if(trabajo.getLogo()!=null && !trabajo.getLogo().equals("")){
			String rootPath = AppConfig.rutaImagenesLogo();
			File fichero = new File(rootPath+File.separator+trabajo.getLogo());
			
			fichero.delete();
		}

		LOGGER.debug("Se borra el trabajo");
		getJobDAO().deleteJob(trabajo);
		LOGGER.info("JobServiceImpl --> Salida en borrar job");
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
	public List<JobBean> listAllJobBean(){
		List<JobBean> listado=new ArrayList();
		
		for(JobeetJob trabajo: getJobDAO().listAllJob()){
			listado.add(parsearJobeetJob(trabajo));
		}
		
		return listado;
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
		LOGGER.debug("El token encriptado es:"+ Utils.sha1(token));


		if(jobDAO.validarToken(idTrabajo, Utils.sha1(token))){
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
			listaTrabajos.add(parsearJobeetJob(trabajoAux));
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

		/*System.out.println("Antes de ordenar");
		for(JobBean trabajo : listaTrabajos){
			System.out.println("ID Trabajo="+trabajo.getId()+ " Fecha Expiración="+trabajo.getExpires_at());
		}*/
		//Ordenamos la lista de mayor fecha de expiración más próxima a más alejada
		Collections.sort(listaTrabajos, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((JobBean)o1).getExpires_at().compareTo(((JobBean)o2).getExpires_at());
			}
		});

		/*System.out.println("Después de ordenar");
		for(JobBean trabajo : listaTrabajos){
			System.out.println("ID Trabajo="+trabajo.getId()+ " Fecha Expiración="+trabajo.getExpires_at());
		}*/

		//Para quitar repetidos
		//Creamos un objeto HashSet
		HashSet hs = new HashSet();
		//Lo cargamos con los valores del array, esto hace quite los repetidos
		hs.addAll(listaTrabajos);
		//Limpiamos el array
		listaTrabajos.clear();
		//Agregamos los valores sin repetir
		listaTrabajos.addAll(hs);
		//Imprimimos  el r

		LOGGER.info("Salida a buscarTrabajoPatron");
		return listaTrabajos;
	}


	@Transactional(readOnly=true)
	public List<JobBean> buscarTrabajoPatronPaginado(String patronBusqueda, int pagina, int numTrabajos[]){
		LOGGER.info("Entrada a buscarTrabajoPatronPaginado");
		List<JobBean> listaTrabajos=new ArrayList();

		List<JobeetJob> listadoTrabajosDAO=jobDAO.buscarTrabajoPatronPaginado(patronBusqueda,AppConfig.getMaxTrabajosCategoria(),pagina);

		for(JobeetJob trabajoAux : listadoTrabajosDAO){
			JobBean trabajoBean=new JobBean();
			listaTrabajos.add(parsearJobeetJob(trabajoAux));
		}

		numTrabajos[0]=jobDAO.numTrabajosPatron(patronBusqueda);

		LOGGER.info("Salida a buscarTrabajoPatronPaginado");
		return listaTrabajos;
	}

	public JobBean parsearJobeetJob(JobeetJob trabajoAux){
		JobBean trabajoBean=new JobBean();
		trabajoBean.setId(trabajoAux.getId());
		if(trabajoAux.getCategory()!=null)
			trabajoBean.setIdCategory(trabajoAux.getCategory().getId());
		trabajoBean.setType(trabajoAux.getType());
		trabajoBean.setCompany(trabajoAux.getCompany());
		trabajoBean.setLogo(trabajoAux.getLogo());
		trabajoBean.setUrl(trabajoAux.getUrl());
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

	@Transactional(readOnly=false)
	public void publicarTrabajo(int idTrabajo){
		LOGGER.info("Entrada a publicarTrabajo");
		LOGGER.info("Recuperamos el trabajo");
		JobeetJob trabajo=jobDAO.getJobById(idTrabajo);
		LOGGER.info("Modificamos el indicador de activo");
		trabajo.setIs_activated(true);
		LOGGER.info("Guardamos el trabajo modificado");
		jobDAO.guardarJob(trabajo);
		LOGGER.info("Salida a publicarTrabajo");
	}

	@Transactional(readOnly=false)
	public void extenderTrabajo(int idTrabajo){
		LOGGER.info("Entrada a extenderTrabajo");
		LOGGER.info("Recuperamos el trabajo");
		JobeetJob trabajo=jobDAO.getJobById(idTrabajo);
		LOGGER.info("Modificamos la fecha de expiración");
		trabajo.setExpires_at(Utils.sumarRestarDiasFecha(trabajo.getExpires_at(),AppConfig.getDiasActivos()));
		LOGGER.info("Guardamos el trabajo modificado");
		jobDAO.guardarJob(trabajo);
		LOGGER.info("Salida a extenderTrabajo");
	}
}
