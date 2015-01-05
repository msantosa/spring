package org.jobeet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jobbet.beans.JobBean;
import org.jobeet.config.AppConfig;
import org.jobeet.model.JobeetCategory;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.ICategoryService;
import org.jobeet.service.IJobService;
import org.jobeet.validator.JobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	//public String addJob(@ModelAttribute("trabajo") JobeetJob trabajo, @RequestParam("file") MultipartFile file, BindingResult result) {
	/*public String addJob(@ModelAttribute("trabajo") JobeetJob trabajo, @RequestParam("file") MultipartFile file, ModelMap model, BindingResult result){
		System.out.println("Fichero="+file.getName());
		System.out.println("Tamanio="+file.getSize());
		String tokenAcceso="";
		
		try{
			
			new JobValidator().validar(trabajo, result);
			
			if(result.hasErrors()){
				model.addAttribute("mensaje","Se han producido ["+result.getErrorCount()+"] errores al validar el formulario");
				
				List<ObjectError> errores=result.getAllErrors();
		
				for(ObjectError error : errores){
					logger.debug("Errores de validación="+error.getDefaultMessage());
				}
				model.addAttribute("listaErrores",result.getAllErrors());
				return "error.page";
			}
			
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				String rootPath = AppConfig.rutaImagenesLogo();
				File dir = new File(rootPath);
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
				
				trabajo.setLogo(file.getOriginalFilename());
			}
			
			logger.info("El identificador del job es "+trabajo.getId());
			logger.info("El tipo del job es "+trabajo.getType());
			logger.info("Category="+trabajo.getCategory());
			
			tokenAcceso=JobService.addJob(trabajo);
			logger.info("Después de guardar el trabajo");
		}catch(Exception e){
			model.addAttribute("exception", e);
			return "error.page";
		}
		return "redirect:/showJob/"+trabajo.getId()+"/"+tokenAcceso;
	}*/
	public String addJob(@ModelAttribute("trabajo") JobeetJob trabajo, @RequestParam("file") MultipartFile file, ModelMap model, BindingResult result){
		System.out.println("Fichero="+file.getName());
		System.out.println("Tamanio="+file.getSize());
		String tokenAcceso="";
		
		try{
			
			new JobValidator().validar(trabajo, result);
			
			if(result.hasErrors()){
				model.addAttribute("mensaje","Se han producido ["+result.getErrorCount()+"] errores al validar el formulario");
				
				List<ObjectError> errores=result.getAllErrors();
		
				for(ObjectError error : errores){
					logger.debug("Errores de validación="+error.getDefaultMessage());
				}
				model.addAttribute("listaErrores",result.getAllErrors());
				return "error.page";
			}
			
			/*if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				String rootPath = AppConfig.rutaImagenesLogo();
				File dir = new File(rootPath);
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
				
				trabajo.setLogo(file.getOriginalFilename());
			}*/
			
			logger.info("El identificador del job es "+trabajo.getId());
			logger.info("El tipo del job es "+trabajo.getType());
			logger.info("Category="+trabajo.getCategory());
			
			tokenAcceso=JobService.addJob(trabajo,file);
			logger.info("Después de guardar el trabajo");
		}catch(Exception e){
			model.addAttribute("exception", e);
			return "error.page";
		}
		return "redirect:/showJob/"+trabajo.getId()+"/"+tokenAcceso;
	}


	@RequestMapping(value = "/showJob/{idTrabajo}", method = RequestMethod.GET)
	public String showJob(@PathVariable Integer idTrabajo, ModelMap model) {
		logger.info("Recuperamos el trabajo en función del id "+idTrabajo);
		JobeetJob trabajo=JobService.getJobById(idTrabajo);
		//Se verifica si se puede acceder al detalle del trabajo.
		if(!trabajo.isIs_activated()){
			model.addAttribute("mensaje", "No se encuentran datos");
			return "mensajeGenerico";
		}
		model.addAttribute("trabajo", trabajo);
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

	@RequestMapping(value="/editJob/{idTrabajo}", method = RequestMethod.GET)
	public String editJob(@PathVariable("idTrabajo") Integer idTrabajo,ModelMap model) {
		logger.info("Hemos entrado en editJob");
		List<JobeetCategory> listaCategorias=CategoryService.listAllCategory();
		JobeetJob trabajo=JobService.getJobById(idTrabajo);
		JobBean trabajoRecuperado=JobService.parsearJobeetJob(trabajo);
		
		model.addAttribute("trabajo",new JobeetJob());
		model.addAttribute("trabajoEditar",trabajoRecuperado);
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
	
	/*@RequestMapping(value="/buscadorTrabajosAjax/{patroBusqueda}", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody List<JobBean> buscadorTrabajosAjax(@PathVariable("patroBusqueda") String patronBusqueda, ModelMap model) {
		logger.info("Entramos en buscadorTrabajosAjax");
		logger.info("Recuperamos el listado que cumple los valores introducidos en el filtro");
		List<JobBean> listaTrabajos=null;
		//listaTrabajos=JobService.buscarTrabajoPatron(patronBusqueda);
		int numTrabajos[]=new int[1];
		listaTrabajos=JobService.buscarTrabajoPatronPaginado(patronBusqueda, 1,numTrabajos);
		
		logger.debug("Número de trabajos="+numTrabajos[0]);
		logger.info("Salimos de buscadorTrabajosAjax");
		return listaTrabajos;
	}*/
	
	@RequestMapping(value="/buscadorTrabajosAjax/{patroBusqueda}", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody HashMap<String,Object> buscadorTrabajosAjax(@PathVariable("patroBusqueda") String patronBusqueda, ModelMap model) {
		logger.info("Entramos en buscadorTrabajosAjax");
		logger.info("Recuperamos el listado que cumple los valores introducidos en el filtro");
		List<JobBean> listaTrabajos=null;
		//listaTrabajos=JobService.buscarTrabajoPatron(patronBusqueda);
		int numTrabajos[]=new int[1];
		listaTrabajos=JobService.buscarTrabajoPatronPaginado(patronBusqueda, 1,numTrabajos);
		
		HashMap resultado=new HashMap<String,Object>();
		
		resultado.put("listaTrabajos", listaTrabajos);
		resultado.put("numTrabajosRestantes",numTrabajos[0]-AppConfig.getMaxTrabajosCategoria()>0?numTrabajos[0]-AppConfig.getMaxTrabajosCategoria():0);
		
		logger.debug("Número de trabajos="+numTrabajos[0]);
		logger.info("Salimos de buscadorTrabajosAjax");
		return resultado;
	}
	
	@RequestMapping(value="/buscadorTrabajos/{numPagina}", method = RequestMethod.GET)
	public String buscadorTrabajos(@PathVariable("numPagina") int numPagina,@RequestParam("patroBusqueda") String patronBusqueda, ModelMap model) {
		logger.info("Entramos en buscadorTrabajos");
		logger.debug("Patrón búsqueda="+patronBusqueda);
		List<JobBean> listaTrabajos=null;
		int numTrabajos[]=new int[1];
		listaTrabajos=JobService.buscarTrabajoPatronPaginado(patronBusqueda, numPagina, numTrabajos);
		
		int paginaInicio=1;
		logger.info("paginaInicio="+paginaInicio);
		int paginaFinal=numTrabajos[0]/AppConfig.getMaxTrabajosCategoria()+1;
		logger.info("paginaFinal="+paginaFinal);
		int paginaActual=numPagina;
		
		model.addAttribute("listaTrabajos",listaTrabajos);
		model.addAttribute("numTrabajos",numTrabajos[0]);
	    model.addAttribute("beginIndex", paginaInicio);
	    model.addAttribute("endIndex", paginaFinal);
	    model.addAttribute("currentIndex", paginaActual);
	    model.addAttribute("patronBusqueda", patronBusqueda);
		
		logger.debug("Número de trabajos="+numTrabajos[0]);
		logger.info("Salimos en buscadorTrabajos");
		
		return "resultadoBusqueda";
	}
	
	@RequestMapping(value="/publicar/{idTrabajo}", method = RequestMethod.POST)
	public @ResponseBody String publicarTrabajo(@PathVariable("idTrabajo") int idTrabajo, ModelMap model){
		logger.info("Entramos en publicarTrabajo");
		logger.debug("Trabajo a publicar="+idTrabajo);
		JobService.publicarTrabajo(idTrabajo);
		logger.info("Salimos en publicarTrabajo");
		return "";
	}
	
	@RequestMapping(value="/extender/{idTrabajo}", method = RequestMethod.POST)
	public @ResponseBody String extenderTrabajo(@PathVariable("idTrabajo") int idTrabajo, ModelMap model){
		logger.info("Entramos en publicarTrabajo");
		logger.debug("Trabajo a publicar="+idTrabajo);
		JobService.extenderTrabajo(idTrabajo);
		logger.info("Salimos en publicarTrabajo");
		return "";
	}
	
	@RequestMapping(value="/borrarTrabajo/{idTrabajo}", method = RequestMethod.POST)
	public @ResponseBody String borrarTrabajo(@PathVariable("idTrabajo") int idTrabajo, ModelMap model){
		logger.info("Entramos en borrarTrabajo");
		logger.debug("Trabajo a borrar="+idTrabajo);
		JobService.borrarTrabajo(idTrabajo);
		logger.info("Salimos en borrarTrabajo");
		return "";
	}

}
