package org.jobeet.rest;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jobeet.beans.JobBean;
import org.jobeet.model.JobeetJob;
import org.jobeet.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RestController {

	private static Logger logger=Logger.getLogger(RestController.class);
	
	@Autowired
	private IJobService JobService;
	
	@RequestMapping(value="/rest/job", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody HashMap<String,Object> listAllJobs(ModelMap model) {
		logger.info("Entramos en listAllJobs");
		logger.info("Recuperamos el listado que cumple los valores introducidos en el filtro");
		List listaTrabajos=null;
		listaTrabajos=JobService.listAllJobBean();
		//listaTrabajos=JobService.listAllJob();
		
		HashMap resultado=new HashMap<String,Object>();
		
		resultado.put("listaTrabajos", listaTrabajos);
		resultado.put("numTrabajos", listaTrabajos.size());
			
		logger.info("Salimos de listAllJobs");
		return resultado;
	}
	
	@RequestMapping(value="/rest/job/{patron}", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody HashMap<String,Object> searchJobByPattern(@PathVariable("patron") String patron,ModelMap model) {
		logger.info("Entramos en searchJobByPattern");
		logger.info("Recuperamos el listado que cumple los valores introducidos en el filtro");
		List listaTrabajos=null;
		listaTrabajos=JobService.buscarTrabajoPatron(patron);
		//listaTrabajos=JobService.listAllJob();
		
		HashMap resultado=new HashMap<String,Object>();
		
		resultado.put("listaTrabajos", listaTrabajos);
		resultado.put("numTrabajos", listaTrabajos.size());
			
		logger.info("Salimos de searchJobByPattern");
		return resultado;
	}
	
	@RequestMapping(value="/rest/job/id/{idJob}", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(value=HttpStatus.OK)
	//public @ResponseBody ResponseEntity<JobBean> searchById(@PathVariable("idJob") int idJob,ModelMap model) {
	public @ResponseBody JobBean searchById(@PathVariable("idJob") int idJob,ModelMap model) {
		logger.info("Entramos en searchById");
		logger.info("Recuperamos el listado que cumple los valores introducidos en el filtro");
		JobBean job=null;
		
		JobeetJob jobAux=JobService.getJobById(idJob);
		
		if(jobAux!=null){
			job=JobService.parsearJobeetJob(jobAux);
		}else{
			//Modificarlo por la excepción que corresponda
			throw new IllegalArgumentException("No existe el trabajo");
		}
		
		logger.info("Salimos de searchById");
		//return new ResponseEntity<JobBean>(job, HttpStatus.OK);
		return job;
	}
	
	@RequestMapping(value = "/rest/**", method = RequestMethod.GET)  
    public void notExistsUrlHandler(HttpServletRequest request) {  
        throw new IllegalArgumentException("Requested url not exists: " + request.getRequestURI());  
    } 
	
}
