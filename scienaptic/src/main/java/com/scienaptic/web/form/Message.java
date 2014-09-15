package com.scienaptic.web.form;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -8303031450568780637L;
	private String message;
	private String email;
	private String name;
	private String phone;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
