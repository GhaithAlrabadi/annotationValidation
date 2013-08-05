/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author Ghaith
 */
public class BasePatternValidator {

	/**
	 * validate Patten. Mandatory = false : if it is null or empty return true.
	 * else check for pattern. Mandatory = true : if it is null return false
	 * else check for pattern
	 * 
	 * @param input
	 * @param pattern
	 * @param mandatory
	 * @param message
	 * @return
	 */
	public boolean validatePattern(String input, Pattern pattern,
			boolean mandatory) {
		if (!mandatory) {
			if (input == null) {
				return true;
			} else if (input.trim().isEmpty()) {
				return true;
			}
		} else if (mandatory) {
			if (input == null) {
				return false;
			}
		}
		return pattern.matcher(input).matches();

	}

	public ConstraintValidatorContext recordConstraintError(String message,
			ConstraintValidatorContext constraintContext) {
		constraintContext.disableDefaultConstraintViolation();
		return constraintContext.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation();
	}

}
