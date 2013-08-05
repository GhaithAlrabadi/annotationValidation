/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.Length;

/**
 * 
 * @author Ghaith
 */
public class LengthValidator extends BasePatternValidator implements
		ConstraintValidator<Length, String> {

	private Length annotation;

	public void initialize(Length arg0) {
		this.annotation = arg0;

	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {
		if (value != null) {
			if (value.length() > annotation.max()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}
