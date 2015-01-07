package org.jobeet.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.jobeet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@Autowired
	private ICategoryService CategoryService;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@RequestMapping(value="/admin")
	public String indexAdmin(ModelMap model){
		logger.info("Hemos entrado en indexAdmin");
		
		logger.debug("Recuperamos las categorias");
		List categorias= CategoryService.listAllCategory();
		logger.debug("Número de categorias="+categorias.size());
		
		model.addAttribute("categorias",categorias);
		return "indexAdmin";
	}
}
