package org.jobeet.validator;

import java.util.Date;

import org.jobeet.model.JobeetJob;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class JobValidator {
	public void validar(JobeetJob trabajo, Errors errors){
		if(!StringUtils.hasLength(trabajo.getType())){
			errors.rejectValue("type", "Obligatorio","Debe seleccionar el tipo de contrato del trabajo");
		}
		
		if(!StringUtils.hasLength(trabajo.getCompany())){
			errors.rejectValue("company", "Obligatorio","Debe informarse el nombre de la compañía");
		}
		
		if(!StringUtils.hasLength(trabajo.getPosition())){
			errors.rejectValue("position", "Obligatorio","Debe informarse la posición del trabajo");
		}
		
		if(!StringUtils.hasLength(trabajo.getLocation())){
			errors.rejectValue("location", "Obligatorio","Debe informarse la localización del trabajo");
		}
		
		if(!StringUtils.hasLength(trabajo.getDescription())){
			errors.rejectValue("description", "Obligatorio","Debe rellenarse la descripción del trabajo");
		}
		
		if(!StringUtils.hasLength(trabajo.getHow_to_apply())){
			errors.rejectValue("how_to_apply", "Obligatorio","Debe rellenarse cómo aplicar el trabajo");
		}
		
		if(trabajo.getExpires_at()==null){
			errors.rejectValue("expires_at", "Obligatorio","Debe rellenarse la fecha de expiración del trabajo");
		}else{
			Date fechaActual=new Date();
			
			if(trabajo.getExpires_at().before(fechaActual)){
				errors.rejectValue("expires_at", "Error de fechas","La fecha de expiración no puede ser anterior a la actual");
			}
		}
	}

}
