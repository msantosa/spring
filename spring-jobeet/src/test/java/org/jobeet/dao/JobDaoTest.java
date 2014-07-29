package org.jobeet.dao;

import java.util.List;

import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback=true)
@ContextConfiguration({"classpath:test-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)

public class JobDaoTest{
	@Autowired
	private IJobDao jobDAO;

	@Test
	public void testlistAllJob() {
		System.out.println("Iniciamos el test testlistAllJob");
		List<JobeetJob> listaJob=jobDAO.listAllJob();
		System.out.println("listaJob="+listaJob);
		System.out.println("Fin el test testlistAllJob");
	}
	
	@Test
	public void testSaveJob() {
		System.out.println("Iniciamos el test testSaveJob");
		JobeetJob trabajo1=new JobeetJob();
		trabajo1.setCompany("prueba company");
		trabajo1.setPosition("prueba position");
		trabajo1.setLocation("prueba position");
		trabajo1.setDescription("prueba descripción");
		trabajo1.setHow_to_apply("prueba how_to_apply");
		trabajo1.setToken("prueba Token");
		trabajo1.setEmail("prueba@prueba.com");
		
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String currentTime = sdf.format(dt);
		
		System.out.println("CURRENTTIME="+currentTime);
		
		/**REVISAR COMO CONTROLAR EXCEPCIONES EN DAO**/
		
		try {
			trabajo1.setExpires_at(sdf.parse(currentTime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DAO addJob failed", e);
		}
		
		JobeetCategory categoria=new JobeetCategory();
		categoria.setId(1);
		
		trabajo1.setCategory(categoria);
		
		jobDAO.addJob(trabajo1);
		
		List<JobeetJob> listaJob=jobDAO.listAllJob();
		
		System.out.println("Fin el test testSaveJob");
	}
	
	@Test
	public void testJobById() {
		System.out.println("Iniciamos el test testJobById");
		System.out.println("Categoria1="+jobDAO.getJobById(1));
		System.out.println("Categoria999="+jobDAO.getJobById(999));
		System.out.println("Fin el test testJobById");
	}

}
