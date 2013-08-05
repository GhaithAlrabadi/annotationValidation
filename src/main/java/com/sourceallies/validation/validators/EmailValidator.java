/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sourceallies.validation.annotation.Email;

/**
 * 
 * @author Ghaith
 */
public class EmailValidator extends BasePatternValidator implements
		ConstraintValidator<Email, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private Email annotation;

	public void initialize(Email aEmail) {
		this.annotation = aEmail;
	}

	public boolean isValid(String email,
			ConstraintValidatorContext constraintContext) {
		if (!validatePattern(email, pattern, annotation.mandatory())) {
			recordConstraintError(annotation.message(), constraintContext);
			return false;
		}

		return true;
	}
}
