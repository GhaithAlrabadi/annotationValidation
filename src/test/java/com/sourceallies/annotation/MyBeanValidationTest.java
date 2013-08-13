package com.sourceallies.annotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class MyBeanValidationTest {
	Reflections reflections;
	HtmlWriter htmlWriter = new HtmlWriter("target/validationTree");
	String spaceCon = "        ";
	File indexFile;
	String packagePath = "com.sourceallies.model";

	@Test
	public void createValidationTree() {

		reflections = new Reflections(
				new ConfigurationBuilder()
						.setScanners(new SubTypesScanner(false),
								new ResourcesScanner())
						.setUrls(
								ClasspathHelper
										.forPackage(packagePath))
						.filterInputsBy(
								new FilterBuilder().include(FilterBuilder
										.prefix(packagePath))));

		Set<Class<? extends Object>> classes = reflections
				.getSubTypesOf(Object.class);
		indexFile = htmlWriter.createFile("index");
		scanAndLog(classes, "");
	}

	private void scanAndLog(Set<Class<? extends Object>> classes, String space) {
		if (classes == null || classes.isEmpty()) {
			return;
		}
		for (Class<? extends Object> classz : classes) {
			System.out.println("");
			System.out.println(space + classz.getSimpleName());
			htmlWriter.writeLink("index", classz.getSimpleName());
			System.out.println(space + "[");
			Field[] fields = classz.getDeclaredFields();

			Map<Field, List<Annotation>> fieldLvelAnnotations = getFieldWithConstrainAnnotationAsMap(fields);
			htmlWriter.createFile(classz.getSimpleName());
			htmlWriter.writeText(classz.getSimpleName(),
					"<h2>" + classz.getSimpleName() + "</h2>");
			
			// more elegant way
			for (Map.Entry<Field, List<Annotation>> entry : fieldLvelAnnotations
					.entrySet()) {
				htmlWriter
						.writeText(classz.getSimpleName(),
								"############################################################");
				htmlWriter.writeText(classz.getSimpleName(), "Field Name: "
						+ entry.getKey().getName());

				for (Annotation temp : entry.getValue()) {
					if (temp instanceof Valid) {
						System.out.println(space + spaceCon + spaceCon
								+ "Check Inner validation for ("
								+ entry.getKey().getType().getSimpleName()
								+ ")");
						htmlWriter.writeText(classz.getSimpleName(), space
								+ spaceCon + spaceCon
								+ "Check Inner validation for:");
						htmlWriter.writeLink(classz.getSimpleName(), entry
								.getKey().getType().getSimpleName());

					} else {
						System.out.println();
						htmlWriter.writeText(classz.getSimpleName(), space
								+ spaceCon + spaceCon + temp);
					}
				}
			}
			System.out.println(space + "]");
		}
	}

	private Map<Field, List<Annotation>> getFieldWithConstrainAnnotationAsMap(
			Field[] fields) {
		Map<Field, List<Annotation>> fieldsMap = new HashMap<Field, List<Annotation>>();
		for (Field field : fields) {
			List<Annotation> constraintsAnnotations = getConstraintAnnotations(field);
			if (!constraintsAnnotations.isEmpty()) {
				fieldsMap.put(field, constraintsAnnotations);
			}
		}
		return fieldsMap;
	}

	private List<Annotation> getConstraintAnnotations(Field field) {
		List<Annotation> result = new ArrayList<Annotation>();
		Annotation[] fieldAnnotations = field.getAnnotations();
		for (Annotation fieldAnnotation : fieldAnnotations) {
			if (fieldAnnotation instanceof Valid
					|| checkIfAnnotationIsConstaintType(fieldAnnotation)) {
				result.add(fieldAnnotation);
			}
		}
		return result;
	}

	private boolean checkIfAnnotationIsConstaintType(Annotation fieldAnnotation) {
		Annotation[] annotationAnnotations = fieldAnnotation.annotationType()
				.getAnnotations();
		for (Annotation constrainAnotation : annotationAnnotations) {
			if (constrainAnotation instanceof Constraint) {
				return true;
			}

		}
		return false;
	}

}
