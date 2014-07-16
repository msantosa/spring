package org.jobeet.dao;

import java.util.List;

import org.jobeet.model.JobeetCategory;

public interface ICategoryDao {
	public void addCategory(JobeetCategory categoria);
	public JobeetCategory getCategoryById(int idCategoria);
	public List<JobeetCategory> listAllCategory();
}
