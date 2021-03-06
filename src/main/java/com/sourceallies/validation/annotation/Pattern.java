/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sourceallies.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author Ghaith
 */

@Constraint(validatedBy = com.sourceallies.validation.validators.PatternValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Pattern {
	String name() default "[field]";

	String pattern();

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
