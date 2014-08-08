package org.jobeet.service;

import java.util.List;
import org.jobeet.model.JobeetJob;



public interface IJobService {
	public void addJob(JobeetJob trabajo);
	public JobeetJob getJobById(int idJob);
	public List<JobeetJob> listAllJob();
	public List<JobeetJob> listarTrabajosActivos();
}
