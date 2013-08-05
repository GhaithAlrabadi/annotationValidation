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
@Constraint(validatedBy = com.sourceallies.validation.validators.MaxLengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxLength {
	String name() default "[length field]";

	int Value();

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
