/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourceallies.model;

import javax.annotation.meta.Exclusive;
import javax.validation.Valid;

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
public class ConsumerBean1 {
	@Valid
	ConsumerBean2 parent;

	@NotEmpty(message = "This field can not be blank")
	private String name;

	@Email(message = "Pelese Enter valid email in format abc@xyz.com")
	private String email;

	@Range(start = 1, end = 10, name = "length")
	private Integer length;

	@NotNull
	private boolean valid;

	@NotNull(name = "ghaithInt")
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
	
	public ConsumerBean2 getParent() {
		return parent;
	}
	
	public void setParent(ConsumerBean2 parent) {
		this.parent = parent;
	}
}
