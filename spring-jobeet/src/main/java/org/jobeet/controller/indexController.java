package org.jobeet.controller;

import org.apache.log4j.Logger;
import org.jobeet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {
	
	@Autowired
	private ICategoryService CategoryService;
	
	private static final Logger logger = Logger.getLogger(indexController.class);
	
	@RequestMapping(value="/")
	public String index(){
		if(logger.isDebugEnabled()){
			logger.info("Hemos entrado en index");
		}
		
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
	
	@RequestMapping(value="/newJob", method = RequestMethod.GET)
	public String newJob(ModelMap model) {
		if(logger.isDebugEnabled()){
			logger.info("Hemos entrado en newJob");
		}
		CategoryService.listAllCategory();
		return "newjob";
	}

	public ICategoryService getCategoryService() {
		return CategoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		CategoryService = categoryService;
	}
}
