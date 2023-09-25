package com.sts.fullprofile.employee.model;

public class UpdateIsCurrentEmpModel {

	private String empId;
	
	private boolean isCurrentEmployee;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public boolean isCurrentEmployee() {
		return isCurrentEmployee;
	}

	public void setCurrentEmployee(boolean isCurrentEmployee) {
		this.isCurrentEmployee = isCurrentEmployee;
	}
	
	
}
