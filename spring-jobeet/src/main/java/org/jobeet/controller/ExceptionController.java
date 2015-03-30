package org.jobeet.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
	protected final Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(final IOException ex) {
		logger.info("handleIOException - Catching: " + ex.getClass().getSimpleName());
		logger.info("handleIOException - Catching: " + ex.getCause());
		ModelAndView modelAndView = new ModelAndView("error.page");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(final SQLException ex) {
		logger.info("handleSQLException - Catching: " + ex.getClass().getSimpleName());
		logger.info("handleSQLException - Catching: " + ex.getCause());
		logger.info("handleSQLException - Catching: " + ex.getCause());
		logger.info("handleSQLException - Catching: " + ex.getMessage());
		ModelAndView modelAndView = new ModelAndView("error.page");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(final Exception ex) {
		logger.info("handleException - Catching: " + ex.getClass().getSimpleName());
		logger.info("handleException - Catching: " + ex.getCause());
		logger.info("handleException - Catching: " + ex.hashCode());
		logger.info("handleException - Catching: " + ex.getMessage());
		ModelAndView modelAndView = new ModelAndView("error.page");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleExceptionConstraint(final ConstraintViolationException ex) {
		logger.info("handleException - Catching: " + ex.getClass().getSimpleName());
		logger.info("handleException - Catching: " + ex.getCause());
		logger.info("handleException - Catching: " + ex.hashCode());
		logger.info("handleException - Catching: " + ex.getMessage());
		ModelAndView modelAndView = new ModelAndView("error.page");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
}
