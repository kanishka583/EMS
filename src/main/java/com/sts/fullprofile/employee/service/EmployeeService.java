package com.sts.fullprofile.employee.service;

import java.util.List;


import com.sts.fullprofile.employee.entity.Employee;
import com.sts.fullprofile.employee.entity.ResponseHolder;
import com.sts.fullprofile.employee.model.AuthenticationRequest;
import com.sts.fullprofile.employee.model.EmployeeRequest;
import com.sts.fullprofile.employee.model.EmployeeResponse;
import com.sts.fullprofile.employee.model.UpdateIsCurrentEmpModel;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
	
	public ResponseEntity<ResponseHolder<EmployeeResponse>> registerEmployee(EmployeeRequest employeeRequest);
	
	public ResponseEntity<ResponseHolder<Employee>> retrieveEmployee(String empId);
	
	public ResponseEntity<ResponseHolder<Employee>> updateEmployee(Employee employee);
	
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllEmployees();
	
	public ResponseEntity<ResponseHolder<Employee>> updateIsCurrentEmp(UpdateIsCurrentEmpModel updateIsCurrentEmpModel);
	
	public ResponseEntity<ResponseHolder<List<Employee>>>listAllOnBenchEmployees();

	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;

}
