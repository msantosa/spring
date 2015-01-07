package org.jobeet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jobeet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/login")
	public String loginAdmin(ModelMap model){
		logger.info("Hemos entrado en loginAdmin");
		
		logger.info("Redirigimos a la ventana de Login");
		return "loginAdmin";
	}
	
	@RequestMapping(value = "/errorLogin", method = RequestMethod.GET)
    public String loginWithError(Model model) {
        model.addAttribute("error", true);
        return "loginAdmin";
    }
	
	@RequestMapping(value="/validarLogin")
	public String validarLogin(ModelMap model){
		logger.info("Hemos entrado en validarLogin");
		
		logger.info("Redirigimos a la ventana de indexAdmin");
		return "indexAdmin";
	}
}
