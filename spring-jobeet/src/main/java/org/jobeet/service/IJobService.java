package org.jobeet.service;

import java.util.List;

import org.jobbet.beans.JobBean;
import org.jobeet.model.JobeetJob;



public interface IJobService {
	public void addJob(JobeetJob trabajo);
	public JobeetJob getJobById(int idJob);
	public List<JobeetJob> listAllJob();
	public List<JobeetJob> listarTrabajosActivos();
	public JobeetJob getJobValidadoEdicion(int idTrabajo,String token);
	public List<JobBean> getJobByExample(JobeetJob trabajoBuscar);
	public List<JobBean> buscarTrabajoPatron(String Patron);
}
