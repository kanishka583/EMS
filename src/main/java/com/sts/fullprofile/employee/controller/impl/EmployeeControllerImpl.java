package com.sts.fullprofile.employee.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.sts.fullprofile.employee.controller.EmployeeController;
import com.sts.fullprofile.employee.entity.Employee;
import com.sts.fullprofile.employee.entity.ResponseHolder;
import com.sts.fullprofile.employee.model.AuthenticationRequest;
import com.sts.fullprofile.employee.model.EmployeeRequest;
import com.sts.fullprofile.employee.model.EmployeeResponse;
import com.sts.fullprofile.employee.model.UpdateIsCurrentEmpModel;
import com.sts.fullprofile.employee.service.EmployeeService;

@RestController
public class EmployeeControllerImpl implements EmployeeController{
	
	@Autowired
	EmployeeService employeeService;

	@Override
	public ResponseEntity<ResponseHolder<String>> greeting() {
		return new ResponseEntity<>(new ResponseHolder<>(true, "200 OK", null), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseHolder<EmployeeResponse>> registerEmployee(EmployeeRequest employeeRequest) {
			return employeeService.registerEmployee(employeeRequest);
	}

	@Override
	public ResponseEntity<ResponseHolder<Employee>> retriveEmployee(String empId) {
		return employeeService.retrieveEmployee(empId);
	}

	@Override
	public ResponseEntity<ResponseHolder<Employee>> updateEmployee(Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@Override
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllEmployees() {
		return employeeService.listAllEmployees();
	}

	@Override
	public ResponseEntity<ResponseHolder<Employee>> updateIsCurrentEmp(UpdateIsCurrentEmpModel updateIsCurrentEmpModel) {
		return employeeService.updateIsCurrentEmp(updateIsCurrentEmpModel);
	}

	@Override
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllOnBenchEmployees() {
		return employeeService.listAllOnBenchEmployees();
	}

	@Override
	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
		return employeeService.createAuthenticationToken(authenticationRequest);
	}

	

}
