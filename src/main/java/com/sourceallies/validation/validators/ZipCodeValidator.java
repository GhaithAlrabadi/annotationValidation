/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.ZipCode;

/**
 * 
 * @author Ghaith
 */
public class ZipCodeValidator extends BasePatternValidator implements
		ConstraintValidator<ZipCode, String> {

	// Format : XXXXX
	private static final String ZIP_PATTERN = "\\d{5}(-\\d{4})?";
	private Pattern pattern = Pattern.compile(ZIP_PATTERN);
	private ZipCode annotation;

	public void initialize(ZipCode annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(String zipCode,
			ConstraintValidatorContext constraintContext) {
		if (!validatePattern(zipCode, pattern, annotation.mandatory())) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}
}
