package com.scienaptic.web.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Resume implements Serializable {
	private static final long serialVersionUID = 5496725894463692816L;
	private String message;
	private String email;
	private String name;
	private String phone;
	private MultipartFile resume;

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

	public MultipartFile getResume() {
		return resume;
	}

	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
}
