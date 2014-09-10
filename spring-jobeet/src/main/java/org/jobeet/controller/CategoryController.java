package org.jobeet.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.jobeet.config.AppConfig;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.ICategoryService;
import org.jobeet.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
	
	@Autowired
	private ICategoryService CategoryService;
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String mostrarCategoria(@ModelAttribute("categoria") JobeetCategory categoria, BindingResult result) {
		/*CategoryService.addContact(contact);*/
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/showCategory/{idCategoria}/{numPagina}", method = RequestMethod.GET)
	public String mostrarCategoriaPaginada(@ModelAttribute("idCategoria") Integer idCategoria,@ModelAttribute("numPagina") int numPagina,ModelMap model) {
		logger.info("Entrada mostrarCategoriaPaginada");
		logger.info("idCategoria="+idCategoria);
		logger.info("numPagina="+numPagina);
		
		JobeetCategory categoria=CategoryService.getCategoryById(idCategoria);
		int numTrabajos=CategoryService.numTrabajosActivosCategoria(categoria);
		
		logger.info("categoria="+categoria);
		int paginaInicio=1;
		logger.info("paginaInicio="+paginaInicio);
		int paginaFinal=numTrabajos/AppConfig.getMaxTrabajosCategoria()+1;
		logger.info("paginaFinal="+paginaFinal);
		int paginaActual=numPagina;
		List <JobeetJob> listaTrabajos=CategoryService.trabajosActivosCategoria(categoria,numPagina);
		
		logger.info("tamanio listaTrabajos="+listaTrabajos.size());
		
		model.addAttribute("listaTrabajos",listaTrabajos);
		model.addAttribute("categoria", categoria);
	    model.addAttribute("beginIndex", paginaInicio);
	    model.addAttribute("endIndex", paginaFinal);
	    model.addAttribute("currentIndex", paginaActual);
	    model.addAttribute("numTrabajos", numTrabajos);
		
		logger.info("Fin mostrarCategoriaPaginada");
		return "showCategory";
	}

}
