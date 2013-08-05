/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sourceallies.validation.annotation;

/**
 *
 * @author Ghaith
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = com.sourceallies.validation.validators.RangeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
	String name() default "[field]";

	float start();

	float end();

	String message() default "{field.error.range}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
