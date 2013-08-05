package com.sourceallies.annotation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import com.sourceallies.SubResourceBundleLocator;
import com.sourceallies.model.ConsumerBean;
import com.sourceallies.validation.ValidatorFactory;

public class MyBeanValidationTest {

	@Test
	public void testIfItWork() {
		ConsumerBean myBean = new ConsumerBean();
		myBean.setName("na");
		myBean.setEmail("abcq4abc.com");
		myBean.setLength(30);
		myBean.setEmail(null);
		Validator validator = new ValidatorFactory().creatValidator(
				HibernateValidator.class, new SubResourceBundleLocator());
		Set<ConstraintViolation<ConsumerBean>> constran = validator.validate(myBean);
		for (ConstraintViolation<ConsumerBean> temp : constran) {
			System.out.println(temp.getMessage());
		}
	}

}
