package org.jobeet.service;

import java.util.List;

import org.jobeet.dao.ICategoryDao;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;

public interface ICategoryService {
	public void addCategory(JobeetCategory categoria);
	public JobeetCategory getCategoryById(int idCategoria);
	public List<JobeetCategory> listAllCategory();
	public List<JobeetCategory> trabajosActivosXCategoria();
	public List<JobeetJob> trabajosActivosCategoria(JobeetCategory categoria,int pagina);
	public int numTrabajosActivosCategoria(JobeetCategory categoria);
}
