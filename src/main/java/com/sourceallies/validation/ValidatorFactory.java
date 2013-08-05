package com.sourceallies.validation;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.ResourceBundleLocator;

public class ValidatorFactory {
	public Validator creatValidator(Class providerType,
			ResourceBundleLocator bundleLocator) {
		return Validation
				.byProvider(providerType)
				.configure()
				.messageInterpolator(
						new ResourceBundleMessageInterpolator(bundleLocator))
				.buildValidatorFactory().getValidator();
	}
}
