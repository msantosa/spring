package org.jobeet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String addJob(@ModelAttribute("trabajo") JobeetJob trabajo, @RequestParam("file") MultipartFile file, BindingResult result) {
		/*CategoryService.addContact(contact);*/
		System.out.println("Fichero="+file.getName());
		System.out.println("Tamanio="+file.getSize());
		try{
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "uploads/logos");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

			}
			trabajo.setLogo(file.getOriginalFilename());
			logger.info("El identificador del job es "+trabajo.getId());
			logger.info("El tipo del job es "+trabajo.getType());
			logger.info("Category="+trabajo.getCategory());
			JobService.addJob(trabajo);
			logger.info("Después de guardar el trabajo");
		}catch(Exception e){
			return "error";
		}
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

	/*Se devuelve en una lista los tipos de contratos posibles*/
	@ModelAttribute("tiposContrato")
	public Map<String,String> listaTipoContratos(){
		HashMap<String,String> tipoContratos=new HashMap<String,String>();

		tipoContratos.put("Media Jornada", "Media Jornada");
		tipoContratos.put("Jornada Completa", "Jornada Completa");
		tipoContratos.put("Solo tarde", "Solo tarde");
		tipoContratos.put("Horario de noche", "Horario de noche");

		return tipoContratos;
	}

}
