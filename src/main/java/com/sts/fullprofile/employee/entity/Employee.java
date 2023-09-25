package com.sts.fullprofile.employee.entity;


import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Employee {
	
	@Id
	private String empId;
	
	@Column(nullable = false)
	private String empName;

	@Column(nullable = false)
	private String empEmail;

	@Column(nullable = false)
	private long empPhnNo;

	@Column(nullable = false)
	private String empGender;

	@Column(nullable = false)
	private double empSalary;

	@Column
	private String empDesignation;

	@Column(nullable = false)
	private int empExperience;

	@Column(nullable = false)
	private String empDepartment;

	@Column(nullable = false)
	private Date empDOB;

	@Column(nullable = false)
	private String empSkills;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="employee", cascade = CascadeType.ALL)
	private List<Address> empAddress;

	@Column(nullable = false)
	private boolean isCurrentEmployee;

	@Column(nullable = false)
	private boolean isOnBench;
	

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

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

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
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

	public List<Address> getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(List<Address> empAddress) {
		this.empAddress = empAddress;
	}

	public boolean isCurrentEmployee() {
		return isCurrentEmployee;
	}

	public void setCurrentEmployee(boolean isCurrentEmployee) {
		this.isCurrentEmployee = isCurrentEmployee;
	}

	public boolean isOnBench() {
		return isOnBench;
	}

	public void setOnBench(boolean isOnBench) {
		this.isOnBench = isOnBench;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empEmail=" + empEmail + ", empPhnNo=" + empPhnNo
				+ ", empGender=" + empGender + ", empSalary=" + empSalary + ", empDesignation=" + empDesignation
				+ ", empExperience=" + empExperience + ", empDepartment=" + empDepartment + ", empDOB=" + empDOB
				+ ", empSkills=" + empSkills + ", empAddress=" + empAddress + ", isCurrentEmployee=" + isCurrentEmployee
				+ ", isOnBench=" + isOnBench + "]";
	}
	
	

}
