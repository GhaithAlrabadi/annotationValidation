/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.MaxLength;

/**
 * 
 * @author Ghaith
 */
public class MaxLengthValidator extends BasePatternValidator implements
		ConstraintValidator<MaxLength, String> {

	private MaxLength annotation;

	public void initialize(MaxLength annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {
		if (value.trim().length() > annotation.Value()) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}
}
