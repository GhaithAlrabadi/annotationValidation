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

@Constraint(validatedBy = com.sourceallies.validation.validators.NotNullValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {
	String name() default "[field]";

	String message() default "{field.error.null}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
