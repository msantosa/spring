package org.jobeet.config;

import java.util.List;

import org.jobeet.config.AppConfig;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})

public class AppConfigTest {
	@Autowired
	AppConfig appConfig;

	@Test
	public void testPrintProperties() {
		System.out.println("Iniciamos el test testPrintProperties");
		appConfig.printProperties();
		System.out.println("Fin el test testPrintProperties");
	}
	
	@Test
	public void testMaxTrabajosIndex(){
		System.out.println("Iniciamos el test testMaxTrabajosIndex");
		System.out.println("Máximo número trabajos Index="+AppConfig.getMaxTrabajosIndex());
		System.out.println("Fin el test testMaxTrabajosIndex");
	}
	
	@Test
	public void testMaxTrabajosCategoria(){
		System.out.println("Iniciamos el test testMaxTrabajosCategoria");
		System.out.println("Máximo número trabajos Categoria="+AppConfig.getMaxTrabajosCategoria());
		System.out.println("Fin el test testMaxTrabajosCategoria");;
	}
	
	@Test
	public void testDiasActivos(){
		System.out.println("Iniciamos el test testDiasActivos");
		System.out.println("Dias Activos="+AppConfig.getDiasActivos());
		System.out.println("Fin el test testDiasActivos");
	}
}
