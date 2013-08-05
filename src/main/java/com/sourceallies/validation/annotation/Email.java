package com.sourceallies.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sourceallies.validation.validators.EmailValidator;

/**
 * 
 * @author Ghaith
 */
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Email {
	String name() default "[email field]";

	boolean mandatory() default true;

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
