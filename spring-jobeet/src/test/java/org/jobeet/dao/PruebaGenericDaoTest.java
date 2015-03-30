package org.jobeet.dao;

import java.util.HashMap;
import java.util.List;

import org.jobeet.dao.impl.ClasePrueba;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@TransactionConfiguration(defaultRollback=true)
@Transactional

public class PruebaGenericDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private ClasePrueba clasePrueba;

	@Test
	public void testlistAllJob() {
		System.out.println("Iniciamos el test testlistAllJob@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<JobeetJob> listaJob=clasePrueba.getAll(JobeetJob.class);
		System.out.println("listaJob="+listaJob);
		System.out.println("Fin el test testlistAllJob@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Test
	public void testSaveJob() {
		System.out.println("Iniciamos el test testSaveJob@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		JobeetJob trabajo1=new JobeetJob();
		trabajo1.setCompany("prueba company");
		trabajo1.setPosition("prueba position");
		trabajo1.setLocation("prueba position");
		trabajo1.setDescription("prueba descripción");
		trabajo1.setHow_to_apply("prueba how_to_apply");
		trabajo1.setToken("prueba Token2");
		trabajo1.setEmail("prueba@prueba.com");
		trabajo1.setIs_public(false);
		trabajo1.setIs_activated(false);


		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);

		System.out.println("CURRENTTIME="+currentTime);

		/**REVISAR COMO CONTROLAR EXCEPCIONES EN DAO**/

		try {
			trabajo1.setExpires_at(sdf.parse(currentTime));
			trabajo1.setCreated_at(sdf.parse(currentTime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("DAO addJob failed", e);
		}

		JobeetCategory categoria=new JobeetCategory();
		categoria.setId(1);

		trabajo1.setCategory(categoria);

		clasePrueba.saveOrUpdate(trabajo1);

		List<JobeetJob> listaJob=clasePrueba.getAll(JobeetJob.class);

		System.out.println("Fin el test testSaveJob@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Test
	public void testJobById() {
		System.out.println("Iniciamos el test testJobById@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Categoria1="+clasePrueba.findByPK(JobeetJob.class, 1));
		System.out.println("Categoria12="+clasePrueba.findByPK(JobeetJob.class, 12));
		System.out.println("Fin el test testJobById@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Test
	public void testBuscarPorEjemplo() {
		System.out.println("Iniciamos el test testBuscarPorEjemplo@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		int idTrabajo=73;
		JobeetJob trabajoAux=new JobeetJob();

		System.out.println("PRIMERA PRUEBA");
		//trabajoAux.setLocation("Madrid");
		//trabajoAux.setPosition("Analista");
		trabajoAux.setIs_activated(true);
		trabajoAux.setIs_public(true);
		List<JobeetJob> listaTrabajos=clasePrueba.findByExample(JobeetJob.class, trabajoAux);

		System.out.println("Tamaño del vector encontrado="+listaTrabajos.size());
		for(JobeetJob trabajo: listaTrabajos){
			System.out.println("El id del trabajo es="+trabajo.getId());
		}

		System.out.println("SEGUNDA PRUEBA");
		trabajoAux=new JobeetJob();
		trabajoAux.setToken("pepito@everis.com60639.0");
		trabajoAux.setIs_activated(true);
		trabajoAux.setIs_public(true);
		listaTrabajos=clasePrueba.findByExample(JobeetJob.class,trabajoAux);

		System.out.println("Tamaño del vector encontrado="+listaTrabajos.size());
		for(JobeetJob trabajo: listaTrabajos){
			System.out.println("El id del trabajo es="+trabajo.getId());
		}

		System.out.println("Fin el test testBuscarPorEjemplo@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Test
	public void testBuscarQuery() {
		System.out.println("Iniciamos el test testBuscarQuery@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		//String query="select * from job where id = :id";
		String query="select * from job where id = id";
		
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("id",1);


		List<JobeetJob> jobs=clasePrueba.getAllByQuery(query, params);

		System.out.println("Fin el test testBuscarQuery@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}
