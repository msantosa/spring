package org.jobeet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

	@Autowired
	private ICategoryService CategoryService;
	
	@Autowired
	private IJobService JobService;
	
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
	public String addJob(@ModelAttribute("trabajo") JobeetJob trabajo, BindingResult result) {
		/*CategoryService.addContact(contact);*/
		logger.info("El identificador del job es "+trabajo.getId());
		logger.info("El tipo del job es "+trabajo.getType());
		logger.info("Category="+trabajo.getCategory());
		JobService.addJob(trabajo);
		logger.info("Después de guardar el trabajo");
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/showJob/{idTrabajo}", method = RequestMethod.GET)
	public String showJob(@PathVariable Integer idTrabajo, ModelMap model) {
		logger.info("Recuperamos el trabajo en función del id "+idTrabajo);
		model.addAttribute("trabajo", JobService.getJobById(idTrabajo));
		return "showJob";
	}
	
	@RequestMapping(value = "/showJob/{idTrabajo}/{token:[a-z0-9@\\.]+}", method = RequestMethod.GET)
	public String showJobAdmin(@PathVariable("idTrabajo") Integer idTrabajo,@PathVariable("token") String token,ModelMap model) {
		logger.info("Recuperamos el trabajo en función del id "+idTrabajo);
		
		JobeetJob trabajoEditar=JobService.getJobValidadoEdicion(idTrabajo, token);
		logger.debug("trabajo a editar="+trabajoEditar);
		
		if(trabajoEditar!=null){
			logger.info("El trabajo introducido se ha encontrado y es válido");
			logger.debug("Fecha de expiración="+trabajoEditar.getExpires_at());

			model.addAttribute("trabajo", trabajoEditar);
			model.addAttribute("diasAdvertencia", AppConfig.getDiasAdvExpiracion());
			model.addAttribute("diasProrroga", AppConfig.getDiasActivos());
			return "showJobAdmin";
		}
		else{
			logger.info("El trabajo introducido no se ha encontrado o el token no es correcto");
			model.addAttribute("mensaje", "No se han encontrado datos");
			return "mensajeGenerico";
		}
	}
	
	@RequestMapping(value="/editJob", method = RequestMethod.GET)
	public String editJob(ModelMap model) {
		logger.info("Hemos entrado en newJob");
		List<JobeetCategory> listaCategorias=CategoryService.listAllCategory();
		model.addAttribute("trabajo",new JobeetJob());
		model.addAttribute("listaCategorias", listaCategorias);
		return "editJob";
	}
}
