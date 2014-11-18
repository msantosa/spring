package org.jobeet.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@RequestMapping(value="/admin")
	public String indexAdmin(){
		logger.info("Hemos entrado en indexAdmin");
		return "indexAdmin";
	}
}
