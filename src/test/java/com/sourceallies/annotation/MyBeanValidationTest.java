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

	String spaceCon = "        ";
	File indexFile;

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
		indexFile = createHTMLFile("index");
		scanAndLog(classes, "");
	}

	private void scanAndLog(Set<Class<? extends Object>> classes, String space) {
		if (classes == null || classes.isEmpty()) {
			return;
		}
		for (Class<? extends Object> classz : classes) {
			System.out.println("");
			System.out.println(space + classz.getSimpleName());
			writeLinkToHTMLFile("index", classz.getSimpleName());
			System.out.println(space + "[");
			Field[] fields = classz.getDeclaredFields();

			Map<Field, List<Annotation>> fieldLvelAnnotations = getFieldWithConstrainAnnotationAsMap(fields);
			createHTMLFile(classz.getSimpleName());
			writeLinkToTEXTFile(classz.getSimpleName(),
					"<h2>" + classz.getSimpleName() + "</h2>");

			// more elegant way
			for (Map.Entry<Field, List<Annotation>> entry : fieldLvelAnnotations
					.entrySet()) {
				writeLinkToTEXTFile(
						classz.getSimpleName(),
						"____________________________________________________________________________________________________________________________________");
				writeLinkToTEXTFile(classz.getSimpleName(), "Field Name: "
						+ entry.getKey().getName());

				for (Annotation temp : entry.getValue()) {
					if (temp instanceof Valid) {
						System.out.println(space + spaceCon + spaceCon
								+ "Check Inner validation for ("
								+ entry.getKey().getType().getSimpleName()
								+ ")");
						writeLinkToTEXTFile(classz.getSimpleName(), space
								+ spaceCon + spaceCon
								+ "Check Inner validation for:");
						writeLinkToHTMLFile(classz.getSimpleName(), entry
								.getKey().getType().getSimpleName());

					} else {
						System.out.println();
						writeLinkToTEXTFile(classz.getSimpleName(), space
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

	private File createHTMLFile(String name) {
		File file = new File("target/" + name + ".html");
		file.delete();
		try {
			FileUtils fileUtils = new FileUtils();
			file.createNewFile();
			fileUtils.writeStringToFile(file, "<h1>Validation Tree</h1>", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	private void writeLinkToHTMLFile(String name, String data) {
		FileUtils fileUtils = new FileUtils();
		try {
			fileUtils.writeStringToFile(new File("target/" + name + ".html"),
					"<a href='./" + data + ".html'>" + data + "</a><br>", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeLinkToTEXTFile(String name, String data) {
		FileUtils fileUtils = new FileUtils();
		try {
			fileUtils.writeStringToFile(new File("target/" + name + ".html"),
					"<br>" + data + "<br>", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
