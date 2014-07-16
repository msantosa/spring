package org.jobeet.service;

import java.util.List;

import org.jobeet.dao.ICategoryDao;
import org.jobeet.model.JobeetCategory;

public interface ICategoryService {
	public void addCategory(JobeetCategory categoria);
	public JobeetCategory getCategoryById(int idCategoria);
	public List<JobeetCategory> listAllCategory();
}
