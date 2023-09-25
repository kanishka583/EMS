package com.sts.fullprofile.employee.entity;


public class ResponseHolder<T> {
	
	boolean hasMessage;
	
	String errorMessage;
	
	T employeeData;

	public boolean isHasMessage() {
		return hasMessage;
	}

	public void setHasMessage(boolean hasMessage) {
		this.hasMessage = hasMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getEmployeeData() {
		return employeeData;
	}

	public void setEmployeeData(T employeeData) {
		this.employeeData = employeeData;
	}

	public ResponseHolder(boolean hasMessage, String errorMessage, T employeeData) {
		super();
		this.hasMessage = hasMessage;
		this.errorMessage = errorMessage;
		this.employeeData = employeeData;
	}
	
	
}
