package org.jobeet.controller;

import org.jobeet.model.JobeetCategory;
import org.jobeet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
	
	@Autowired
	private ICategoryService CategoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("categoria") JobeetCategory categoria, BindingResult result) {
		/*CategoryService.addContact(contact);*/
		return "redirect:/index";
	}

}
