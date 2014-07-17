package org.jobeet.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

	@Autowired
	private ICategoryService CategoryService;
	
	private static final Logger logger = Logger.getLogger(JobController.class);
	
	@RequestMapping(value="/newJob", method = RequestMethod.GET)
	public String newJob(ModelMap model) {
		logger.info("Hemos entrado en newJob");
		List<JobeetCategory> listaCategorias=CategoryService.listAllCategory();
		model.addAttribute("trabajo",new JobeetJob());
		model.addAttribute("listaCategorias", listaCategorias);
		return "newjob";
	}
	
	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("trabajo") JobeetJob trabajo, BindingResult result) {
		/*CategoryService.addContact(contact);*/
		logger.info("El identificador del job es "+trabajo.getId());
		logger.info("El tipo del job es "+trabajo.getType());
		return "redirect:/";
	}
}
