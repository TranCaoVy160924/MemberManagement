package dxc.assignment.helper;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationHelper {
	public static boolean hasErrorOnlyForField(BindingResult bindingResult, String fieldName) {
		for (FieldError error : bindingResult.getFieldErrors()) {
			if (!error.getField().equals(fieldName)) {
				return false; // Found an error for a different field
			}
		}
		return bindingResult.hasFieldErrors(fieldName);
	}
}
