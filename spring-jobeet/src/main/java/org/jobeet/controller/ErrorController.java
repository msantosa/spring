package org.jobeet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping(value="/404")
    public ModelAndView notFound() {

		ModelAndView model = new ModelAndView("error.page");
        model.addObject("mensaje", "The page you requested could not be found. This location may not be current.");

        return model;
    }
	
	@RequestMapping(value="/500")
	public ModelAndView errorPage() {
        ModelAndView model = new ModelAndView("error.page");
        model.addObject("mensaje", "The page you requested could not be found. This location may not be current, due to the recent site redesign.");

        return model;
    }
	
}
