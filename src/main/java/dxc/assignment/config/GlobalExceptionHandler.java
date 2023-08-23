package dxc.assignment.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleGlobalException(Exception ex) {
		String error = "ERROR";
		String instruction = "YOU SEEM TO BE TRYING TO FIND HIS WAY HOME";
		
		ModelAndView modelAndView = new ModelAndView("error");
		System.out.println("Error: " + ex.getMessage());
		
		// Specify the error message and instruction for unauthorize access
		if (ex.getMessage().equals("Access is denied")) {
			error = "ACCESS DENIED";
			instruction = "UNAUTHORIZE: YOU DONT HAVE THE PERMISSION TO ACCESS THIS FUNCTION";
		}
		
		modelAndView.addObject("error", error);
		modelAndView.addObject("instruction", instruction);
		
		return modelAndView;
	}
}