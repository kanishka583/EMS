package com.sts.fullprofile.employee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sts.fullprofile.employee.entity.Employee;
import com.sts.fullprofile.employee.entity.ResponseHolder;
import com.sts.fullprofile.employee.model.AuthenticationRequest;
import com.sts.fullprofile.employee.model.EmployeeRequest;
import com.sts.fullprofile.employee.model.EmployeeResponse;
import com.sts.fullprofile.employee.model.UpdateIsCurrentEmpModel;


public interface EmployeeController {
	
	@GetMapping("/health")
	public ResponseEntity<ResponseHolder<String>> greeting() ;
	
	@PostMapping("/registerEmployee")
	public ResponseEntity<ResponseHolder<EmployeeResponse>> registerEmployee(@RequestBody EmployeeRequest employeeRequest);
	
	@GetMapping("/retriveEmployee/{empId}")
	public ResponseEntity<ResponseHolder<Employee>> retriveEmployee(@PathVariable String empId);
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<ResponseHolder<Employee>> updateEmployee(@RequestBody Employee employee);
	
	@GetMapping("/listAllEmployees")
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllEmployees();
	
	@PutMapping("/updateIsCurrentEmp")
	public ResponseEntity<ResponseHolder<Employee>> updateIsCurrentEmp(@RequestBody UpdateIsCurrentEmpModel updateIsCurrentEmpModel);
	
	@GetMapping("/listOnBenchEmployees")
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllOnBenchEmployees();
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception;
	
}
