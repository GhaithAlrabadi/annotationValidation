/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.NotEmpty;

/**
 * 
 * @author Ghaith
 */
public class NotEmptyValidator extends BasePatternValidator implements
		ConstraintValidator<NotEmpty, String> {

	private NotEmpty annotation;

	public void initialize(NotEmpty annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(String value,
			ConstraintValidatorContext constraintContext) {
		if (value == null || value.trim().length() == 0) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}

}
