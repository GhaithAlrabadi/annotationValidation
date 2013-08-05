/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.Phone;

/**
 * 
 * @author Ghaith
 */
public class PhoneValidator extends BasePatternValidator implements
		ConstraintValidator<Phone, String> {

	private static final String PHONE_PATTERN = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
	private static Pattern pattern = Pattern.compile(PHONE_PATTERN);
	private Phone annotation;

	public void initialize(Phone annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(String phone,
			ConstraintValidatorContext constraintContext) {
		if (!validatePattern(phone, pattern, annotation.mandatory())) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}
}
