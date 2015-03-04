package org.jobeet.validator;

import java.util.Date;

import org.jobeet.beans.JobBean;
import org.jobeet.model.JobeetJob;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class JobValidator implements Validator{
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.isAssignableFrom(JobBean.class);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		JobeetJob trabajo=(JobeetJob) arg0;
		
		if(!StringUtils.hasLength(trabajo.getType())){
			errors.rejectValue("type", "jobform.error.noType");
		}
		
		if(!StringUtils.hasLength(trabajo.getCompany())){
			errors.rejectValue("company","jobform.error.noCompany");
		}
		
		if(!StringUtils.hasLength(trabajo.getPosition())){
			errors.rejectValue("position", "jobform.error.noPosition");
		}
		
		if(!StringUtils.hasLength(trabajo.getLocation())){
			errors.rejectValue("location", "jobform.error.noLocation");
		}
		
		if(!StringUtils.hasLength(trabajo.getDescription())){
			errors.rejectValue("description", "jobform.noDescription");
		}
		
		if(!StringUtils.hasLength(trabajo.getHow_to_apply())){
			errors.rejectValue("how_to_apply", "jobform.error.noHowToApply");
		}
		
		if(trabajo.getExpires_at()==null){
			errors.rejectValue("expires_at", "jobform.error.noExpiresAt");
		}else{
			Date fechaActual=new Date();
			
			if(trabajo.getExpires_at().before(fechaActual)){
				errors.rejectValue("expires_at", "jobform.error.ExpiresAtBeforeNow");
			}
		}	
	}
}
