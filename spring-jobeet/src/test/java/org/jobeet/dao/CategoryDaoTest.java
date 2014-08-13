package org.jobeet.dao;

import java.util.List;

import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@TransactionConfiguration(defaultRollback=true)
@Transactional

public class CategoryDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private ICategoryDao categoryDAO;

	@Test
	public void testlistAllCategory() {
		System.out.println("Iniciamos el test testlistAllCategory");
		List<JobeetCategory> lista=categoryDAO.listAllCategory();
		System.out.println("Número de elementos="+lista.size());
		System.out.println("Número de trabajos asociados="+((JobeetCategory)(lista.get(1))).getTrabajos().size());
		for(JobeetJob j: ((JobeetCategory)(lista.get(0))).getTrabajos()){
			System.out.println("Trabajo"+j.getId()+"="+j.getCompany()+" fecha_creación="+j.getCreated_at()
					+" fecha expiración="+j.getExpires_at());
		}
		System.out.println("Fin el test testlistAllCategory");
	}
	
	@Test
	public void testSaveCategory() {
		System.out.println("Iniciamos el test testSaveCategory");
		JobeetCategory categoria1=new JobeetCategory();
		categoria1.setName("categoria1");;
		categoryDAO.addCategory(categoria1);
		System.out.println("Fin el test testSaveCategory");
	}
	
	@Test
	public void testCategoryById() {
		System.out.println("Iniciamos el test testCategoryById");
		System.out.println("Categoria1="+categoryDAO.getCategoryById(1));
		System.out.println("Categoria999="+categoryDAO.getCategoryById(999));
		System.out.println("Fin el test testCategoryById");
	}

}
