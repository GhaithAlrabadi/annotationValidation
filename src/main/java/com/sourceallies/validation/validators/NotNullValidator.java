/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.NotNull;

/**
 * 
 * @author Ghaith
 */
public class NotNullValidator extends BasePatternValidator implements
		ConstraintValidator<NotNull, Object> {

	private NotNull annotation;

	public void initialize(NotNull annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(Object value,
			ConstraintValidatorContext constraintContext) {
		if (value == null) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}

}
