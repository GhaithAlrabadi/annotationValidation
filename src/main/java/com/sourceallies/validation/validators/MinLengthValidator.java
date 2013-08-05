/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.MinLength;

/**
 * 
 * @author Ghaith
 */
public class MinLengthValidator extends BasePatternValidator implements
		ConstraintValidator<MinLength, String> {

	private MinLength annotation;

	public void initialize(MinLength annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {
		if (value != null) {
			if (value.length() < annotation.value()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}
		} else {
			System.out.println(annotation.message());
			return false;
		}

		return true;
	}
}
