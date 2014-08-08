package org.jobeet.service;

import java.util.List;

import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

	@Autowired
	private ICategoryService CategoryService;
	
	@Test
	public void testTrabajosActivosXCategoria() {
		System.out.println("Iniciamos el test testTrabajosActivosXCategoria");
		List<JobeetCategory> listaCategory=CategoryService.trabajosActivosXCategoria();
		System.out.println("Tamaño de lista categorias="+listaCategory.size());
		for(JobeetCategory c: listaCategory){
			System.out.println(c.getName().toUpperCase());
			for(JobeetJob t: c.getTrabajos()){
				System.out.println("Id="+t.getId());
				System.out.println("Compañia="+t.getCompany());
				System.out.println("Fecha creación="+t.getCreated_at());
				System.out.println("Fecha expiración="+t.getExpires_at());
			}
		}
		
		System.out.println("Fin el test testTrabajosActivosXCategoria");
	}

}
