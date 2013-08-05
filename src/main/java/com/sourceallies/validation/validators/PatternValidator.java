/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.Pattern;

/**
 * 
 * @author Ghaith
 */
public class PatternValidator extends BasePatternValidator implements
		ConstraintValidator<Pattern, String> {

	private Pattern annotation;

	public void initialize(Pattern annotation) {
		this.annotation = annotation;
	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {
		try {

			java.util.regex.Pattern pattern = java.util.regex.Pattern
					.compile(value);

			if (!pattern.matcher(value).matches()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}

		} catch (Exception ex) {
			Logger.getLogger(PatternValidator.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		return true;
	}
}
