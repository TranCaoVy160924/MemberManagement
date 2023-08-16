package dxc.assignment.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleGlobalException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		System.out.println(ex.getMessage());
		return modelAndView;
	}
}