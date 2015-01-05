package org.jobeet.service;

import java.io.IOException;
import java.util.List;

import org.jobbet.beans.JobBean;
import org.jobeet.model.JobeetJob;
import org.springframework.web.multipart.MultipartFile;



public interface IJobService {
	public String addJob(JobeetJob trabajo);
	public String addJob(JobeetJob trabajo,MultipartFile file) throws IOException;
	public void borrarTrabajo(int idTrabajo);
	public JobeetJob getJobById(int idJob);
	public List<JobeetJob> listAllJob();
	public List<JobeetJob> listarTrabajosActivos();
	public JobeetJob getJobValidadoEdicion(int idTrabajo,String token);
	public List<JobBean> getJobByExample(JobeetJob trabajoBuscar);
	public List<JobBean> buscarTrabajoPatron(String Patron);
	public JobBean parsearJobeetJob(JobeetJob trabajo);
	public List<JobBean> buscarTrabajoPatronPaginado(String patronBusqueda, int pagina,int numTrabajo[]);
	public void publicarTrabajo(int idTrabajo);
	public void extenderTrabajo(int idTrabajo);
}
