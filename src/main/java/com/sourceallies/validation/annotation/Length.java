package com.sourceallies.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sourceallies.validation.validators.LengthValidator;

/**
 * 
 * @author Ghaith
 */
@Constraint(validatedBy = LengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {
	String name() default "[length field]";

	int max();

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
