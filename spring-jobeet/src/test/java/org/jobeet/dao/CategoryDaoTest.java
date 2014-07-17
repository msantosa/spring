package org.jobeet.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jobeet.dao.impl.CategoryDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryDaoTest{

	private ApplicationContext context;
	private ICategoryDao categoryDAO;
	
	@Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        categoryDAO = (CategoryDaoImpl) context.getBean("categoryDAO");
		//categoryDAO=new CategoryDaoImpl();
    }

	@Test
	public void testlistAllCategory() {
		System.out.println("Iniciamos el test testlistAllCategory");
		List lista=categoryDAO.listAllCategory();
		System.out.println("Número de elementos="+lista.size());
		System.out.println("Fin el test testlistAllCategory");
	}

}
