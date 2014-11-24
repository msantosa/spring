package org.jobeet.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobeet.config.AppConfig;
import org.jobeet.model.JobeetCategory;
import org.jobeet.service.ICategoryService;
import org.jobeet.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	@Autowired
	private ICategoryService CategoryService;
	
	@Autowired
	private IJobService JobService;
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	public ICategoryService getCategoryService() {
		return CategoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		CategoryService = categoryService;
	}
	
	@RequestMapping(value="/")
	public String index(ModelMap model){
		HashMap<Integer, Integer> categoriaTrabajosActivos=new HashMap<Integer, Integer>();
		int numTrabajosActivosMas=0;
		
		if(logger.isDebugEnabled()){
			logger.info("Hemos entrado en index");
		}
		
		List<JobeetCategory> trabajosActivosXCategoria=CategoryService.trabajosActivosXCategoria();
		
		logger.info("Recuperamos el listado de trabajos activos");
		model.addAttribute("listaCategoriaTrabajo",trabajosActivosXCategoria);
		
		logger.info("Creamos HasMap con el detalle de categoria-trabajos activos");
		for(JobeetCategory categoria : trabajosActivosXCategoria){
			logger.info("Categoria="+categoria.getName());
			numTrabajosActivosMas=CategoryService.numTrabajosActivosCategoria(categoria)-AppConfig.getMaxTrabajosIndex()>0?CategoryService.numTrabajosActivosCategoria(categoria)-AppConfig.getMaxTrabajosIndex():0;
			logger.info("numTrabajosActivos="+(numTrabajosActivosMas));
			categoriaTrabajosActivos.put(categoria.getId(), new Integer(numTrabajosActivosMas));
		}
		
		model.addAttribute("categoriaTrabajosActivos",categoriaTrabajosActivos);
		logger.info("Se han recuperado los trabajos. Hacemos redirect");
		return "index"; 
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		if(logger.isDebugEnabled()){
			logger.info("Hemos entrado en hello");
		}
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
	
}
