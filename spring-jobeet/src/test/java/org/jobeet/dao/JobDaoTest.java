package org.jobeet.dao;

import java.util.List;

import org.jobeet.config.AppConfig;
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

public class JobDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
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
		trabajo1.setToken("prueba Token2");
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
		System.out.println("Categoria12="+jobDAO.getJobById(12));
		System.out.println("Fin el test testJobById");
	}
	
	@Test
	public void testListarTrabajosActivos() {
		System.out.println("Iniciamos el test testlistarTrabajosActivos");
		List <JobeetJob> lista=jobDAO.listarTrabajosActivos();
		if(lista!=null){
			System.out.println("Tamaño de lista="+lista.size());
			for(int i=0;i<lista.size();i++){
				System.out.println("Location="+((JobeetJob)lista.get(i)).getLocation());
				System.out.println("Position="+((JobeetJob)lista.get(i)).getPosition());
				System.out.println("Company="+((JobeetJob)lista.get(i)).getCompany());
			}
		}
		System.out.println("Fin el test testlistarTrabajosActivos");
	}

	@Test
	public void testListarTrabajosActivosCategoria() {
		System.out.println("Iniciamos el test testlistarTrabajosActivos");
		JobeetCategory categoria=new JobeetCategory();
		categoria.setId(1);
		List <JobeetJob> lista=jobDAO.trabajosActivosCategoria(categoria,AppConfig.getMaxTrabajosIndex());
		if(lista!=null){
			System.out.println("Tamaño de lista="+lista.size());
			for(int i=0;i<lista.size();i++){
				System.out.println("Location="+((JobeetJob)lista.get(i)).getLocation());
				System.out.println("Position="+((JobeetJob)lista.get(i)).getPosition());
				System.out.println("Company="+((JobeetJob)lista.get(i)).getCompany());
				System.out.println("Expires At="+((JobeetJob)lista.get(i)).getExpires_at());
			}
		}
		System.out.println("Fin el test testlistarTrabajosActivos");
	}
	
	@Test
	public void testTrabajosActivosCategoria() {
		System.out.println("Iniciamos el test testTrabajosActivosCategoria");
		JobeetCategory categoria=new JobeetCategory();
		categoria.setId(3);
		System.out.println("Trabajos activos categoria 3="+jobDAO.numTrabajosActivosCategoria(categoria));
		System.out.println("Fin el test testTrabajosActivosCategoria");
	}
	
	@Test
	public void testValidarTokenTrabajo() {
		System.out.println("Iniciamos el test testValidarTokenTrabajo");
		int idTrabajo=73;
		String token="abc";
		System.out.println("Trabajo ["+idTrabajo+"] encontrado="+jobDAO.validarToken(idTrabajo, token));
		
		idTrabajo=97;
		token="36d978a7ad2e18ca224342d6e3451924595cc8b0";
		System.out.println("Trabajo ["+idTrabajo+"] encontrado="+jobDAO.validarToken(idTrabajo, token));
		
		System.out.println("Fin el test testValidarTokenTrabajo");
	}
	
}
