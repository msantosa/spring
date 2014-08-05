package org.jobeet.dao;

import java.util.List;

import org.jobeet.model.JobeetJob;

public interface IJobDao {
	public void addJob(JobeetJob trabajo);
	public void deleteJob(JobeetJob trabajo);
	public JobeetJob getJobById(int idTrabajo);
	public List<JobeetJob> listAllJob();
	public List<JobeetJob> listarTrabajosActivos();
}
