package org.jobeet.controller;

import org.apache.log4j.Logger;
import org.jobeet.service.ICategoryService;
import org.jobeet.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@Autowired
	private ICategoryService CategoryService;
	
	@Autowired
	private IJobService JobService;
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value="/")
	public String index(ModelMap model){
		if(logger.isDebugEnabled()){
			logger.info("Hemos entrado en index");
		}
		logger.info("Recuperamos el listado de trabajos activos");
		/*model.addAttribute("listaTrabajosActivos",JobService.listarTrabajosActivos());*/
		model.addAttribute("listaCategoriaTrabajo",CategoryService.trabajosActivosXCategoria());
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

	public ICategoryService getCategoryService() {
		return CategoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		CategoryService = categoryService;
	}
}
