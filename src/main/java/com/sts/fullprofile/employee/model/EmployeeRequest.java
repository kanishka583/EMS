package com.sts.fullprofile.employee.model;

import java.util.Date;
import java.util.List;

import com.sts.fullprofile.employee.entity.Address;


public class EmployeeRequest {

	private String empName;

	private String empEmail;

	private long empPhnNo;

	private String empGender;

	private double empSalary;

	private int empExperience;

	private String empDepartment;

	private Date empDOB;

	private String empSkills;

	private List<AddressRequest> empAddress;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public long getEmpPhnNo() {
		return empPhnNo;
	}

	public void setEmpPhnNo(long empPhnNo) {
		this.empPhnNo = empPhnNo;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public int getEmpExperience() {
		return empExperience;
	}

	public void setEmpExperience(int empExperience) {
		this.empExperience = empExperience;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public Date getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}

	public String getEmpSkills() {
		return empSkills;
	}

	public void setEmpSkills(String empSkills) {
		this.empSkills = empSkills;
	}

	public List<AddressRequest> getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(List<AddressRequest> empAddress) {
		this.empAddress = empAddress;
	}
	
	
}
