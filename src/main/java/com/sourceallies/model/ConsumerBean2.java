/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.model;

import javax.annotation.meta.Exclusive;

import com.google.common.annotations.Beta;
import com.sourceallies.validation.annotation.Email;
import com.sourceallies.validation.annotation.MinLength;
import com.sourceallies.validation.annotation.NotEmpty;
import com.sourceallies.validation.annotation.NotNull;
import com.sourceallies.validation.annotation.Range;

/**
 * 
 * @author Ghaith
 */
@Exclusive
public class ConsumerBean2 {

	@MinLength(value = 5, message = "Min Length of name field is 5.")
	@NotEmpty(message = "This field can not be blank")
	private String name;

	@Email(message = "Pelese Enter valid email in format abc@xyz.com")
	private String email;

	
	private Integer length;


	private boolean valid;

	
	private Integer ghaithInt;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
