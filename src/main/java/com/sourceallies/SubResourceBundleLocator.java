package com.sourceallies;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.resourceloading.ResourceBundleLocator;

public class SubResourceBundleLocator implements ResourceBundleLocator {

	public ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle("com/sourceallies/ValidationMessages");
	}
}
