/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.Range;

/**
 * 
 * @author Ghaith
 */
public class RangeValidator extends BasePatternValidator implements
		ConstraintValidator<Range, Number> {

	private Range annotation;

	public void initialize(Range arg0) {
		annotation = arg0;

	}

	public boolean isValid(Number number,
			ConstraintValidatorContext constraintContext) {
		if (number == null) {
			recordConstraintError("Null or " + annotation.message(),
					constraintContext);
			return false;
		}

		if (number instanceof Integer) {
			if (number.intValue() < annotation.start()
					|| number.intValue() > annotation.end()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}

		} else if (number instanceof Float) {
			if (number.floatValue() < annotation.start()
					|| number.floatValue() > annotation.end()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}
		} else {
			if (number.doubleValue() < annotation.start()
					|| number.doubleValue() > annotation.end()) {
				recordConstraintError(annotation.message(), constraintContext);
				return false;
			}
		}

		return true;
	}
}
