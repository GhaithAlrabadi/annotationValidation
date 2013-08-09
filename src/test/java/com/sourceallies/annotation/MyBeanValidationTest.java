package com.sourceallies.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.sourceallies.SubResourceBundleLocator;
import com.sourceallies.model.ConsumerBean;
import com.sourceallies.validation.ValidatorFactory;

public class MyBeanValidationTest {
	Reflections reflections;

	String spaceCon = "        ";

	@Test
	public void testIfItWork() {

		reflections = new Reflections(
				new ConfigurationBuilder()
						.setScanners(new SubTypesScanner(false),
								new ResourcesScanner())
						.setUrls(
								ClasspathHelper
										.forPackage("com.sourceallies.model"))
						.filterInputsBy(
								new FilterBuilder().include(FilterBuilder
										.prefix("com.sourceallies.model"))));

		Set<Class<? extends Object>> classes = reflections
				.getSubTypesOf(Object.class);
		scanAndLog(classes, "");

		
		/************************************/
		ConsumerBean myBean = new ConsumerBean();
		myBean.setName("na");
		myBean.setEmail("abcq4abc.com");
		myBean.setLength(30);
		myBean.setEmail(null);
		Validator validator = new ValidatorFactory().creatValidator(
				HibernateValidator.class, new SubResourceBundleLocator());
		Set<ConstraintViolation<ConsumerBean>> constran = validator
				.validate(myBean);
		for (ConstraintViolation<ConsumerBean> temp : constran) {
			System.out.println(temp.getMessage());
		}

	}

	private void scanAndLog(Set<Class<? extends Object>> classes, String space) {
		if (classes == null || classes.isEmpty()) {
			return;
		}
		for (Class<? extends Object> classz : classes) {
			Annotation[] classLevelAnnotations = classz.getAnnotations();
			System.out.println(space + classz.getSimpleName());
			System.out.println(space + "[");
			Field[] fields = classz.getDeclaredFields();
			for (Field field : fields) {
				Annotation[] fieldLvelAnnotations = field.getAnnotations();
				Annotation[] allClassAnotations = mergeArrays(
						fieldLvelAnnotations, classLevelAnnotations);
				for (Annotation annotation : allClassAnotations) {
					if (annotation instanceof Valid) {
						Set<Class<? extends Object>> set = new HashSet<Class<? extends Object>>();
						set.add(field.getType());
						System.out.println(space + field.getName());
						scanAndLog(set, space + spaceCon);
					}
					Annotation[] annotationAnnotations = annotation
							.annotationType().getAnnotations();
					for (Annotation constrainAnotation : annotationAnnotations) {
						if (constrainAnotation instanceof Constraint) {
							System.out.println(space + annotation);
						}

					}

				}

			}
			System.out.println(space + "]");
		}
	}

	private Annotation[] mergeArrays(Annotation[] fieldLvelAnnotations,
			Annotation[] classLevelAnnotations) {
		int aLen = fieldLvelAnnotations.length;
		int bLen = classLevelAnnotations.length;
		Annotation[] all = new Annotation[aLen + bLen];
		System.arraycopy(fieldLvelAnnotations, 0, all, 0, aLen);
		System.arraycopy(classLevelAnnotations, 0, all, aLen, bLen);
		return all;
	}

}
