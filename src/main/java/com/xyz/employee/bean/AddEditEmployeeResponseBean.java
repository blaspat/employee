package com.xyz.employee.bean;

public class AddEditEmployeeResponseBean {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AddEditEmployeeResponseBean() {
	}

	public AddEditEmployeeResponseBean(String message) {
		this.message = message;
	}
}
