package com.sts.fullprofile.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sts.fullprofile.employee.entity.Address;
import com.sts.fullprofile.employee.entity.Employee;
import com.sts.fullprofile.employee.entity.ResponseHolder;
import com.sts.fullprofile.employee.model.AddressRequest;
import com.sts.fullprofile.employee.model.AuthenticationRequest;
import com.sts.fullprofile.employee.model.AuthenticationResponse;
import com.sts.fullprofile.employee.model.EmployeeRequest;
import com.sts.fullprofile.employee.model.EmployeeResponse;
import com.sts.fullprofile.employee.model.UpdateIsCurrentEmpModel;
import com.sts.fullprofile.employee.repository.EmployeeRepository;
import com.sts.fullprofile.employee.security.MyUserDetailsService;
import com.sts.fullprofile.employee.service.EmployeeService;
import com.sts.fullprofile.employee.util.JwtUtil;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
		
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	String firstEmpId = "STS-1234";

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Override
	public ResponseEntity<ResponseHolder<EmployeeResponse>> registerEmployee(EmployeeRequest employeeRequest){
		Employee employee = new Employee();
		Employee createdEmployee = null;
		EmployeeResponse employeeResponse = null;
		ResponseHolder<EmployeeResponse> responseHolder = null;
		try {
			employee.setEmpName(employeeRequest.getEmpName());
			employee.setEmpEmail(employeeRequest.getEmpEmail());
			employee.setEmpPhnNo(employeeRequest.getEmpPhnNo());
			employee.setEmpGender(employeeRequest.getEmpGender());
			employee.setEmpSalary(employeeRequest.getEmpSalary());
			employee.setEmpExperience(employeeRequest.getEmpExperience());
			employee.setEmpDepartment(employeeRequest.getEmpDepartment());
			employee.setEmpDOB(employeeRequest.getEmpDOB());
			employee.setEmpSkills(employeeRequest.getEmpSkills());
			List<Address> listOfInputAddress = new ArrayList();
			for(AddressRequest address : employeeRequest.getEmpAddress()) {
				Address address2 = new Address();
				address2.setAddressLine1(address.getAddressLine1());
				address2.setAddressLine2(address.getAddressLine2());
				address2.setAddressType(address.getAddressType());
				address2.setLandmark(address.getLandmark());
				address2.setZipCode(address.getZipCode());
				address2.setEmployee(employee);

				listOfInputAddress.add(address2);
			}
			employee.setEmpAddress(listOfInputAddress);
			employee = updateWithEmpId(employee);
			employee = setDesignation(employee);
			
			logger.info("Creation of Employee Object using EmployeeRequest is done: "+employee.toString());
			
			createdEmployee = employeeRepository.save(employee);
			
			logger.info("Employee is saved into DB");
			
			employeeResponse = new EmployeeResponse();
			employeeResponse.setEmpId(createdEmployee.getEmpId());
			employeeResponse.setEmpName(createdEmployee.getEmpName());
			employeeResponse.setEmpEmail(createdEmployee.getEmpEmail());
			employeeResponse.setEmpPhnNo(createdEmployee.getEmpPhnNo());
			employeeResponse.setEmpGender(createdEmployee.getEmpGender());
			employeeResponse.setEmpSalary(createdEmployee.getEmpSalary());
			employeeResponse.setEmpExperience(createdEmployee.getEmpExperience());
			employeeResponse.setEmpDepartment(createdEmployee.getEmpDepartment());
			employeeResponse.setEmpDOB(createdEmployee.getEmpDOB());
			employeeResponse.setEmpSkills(createdEmployee.getEmpSkills());
			List<Address> listOfAddress = new ArrayList();
			if(createdEmployee.getEmpAddress()!=null) {
				for(Address address : createdEmployee.getEmpAddress()) {
					listOfAddress.add(address);
				}
			}
			employeeResponse.setEmpAddress(listOfAddress);
			
			responseHolder = new ResponseHolder<>(false, "", employeeResponse);
			return new ResponseEntity<>(responseHolder, HttpStatus.CREATED);
		}catch(Exception e) {
			responseHolder = new ResponseHolder<>(true, "Error while registering the employee: "+e.toString(), null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

//	@Transactional
	@Override
	public ResponseEntity<ResponseHolder<Employee>> retrieveEmployee(String empId) {
		ResponseHolder<Employee> responseHolder= null;
		try {
			logger.info("Trying to retrieve Employee from DB");
			Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
			if(optionalEmployee.isPresent()) {
				Employee employee = optionalEmployee.get();
//				List<Address> listOfAddresses = employee.getEmpAddress();
//				logger.info("List of Addresses: " + listOfAddresses.toString());
//				employee.setEmpAddress(listOfAddresses);
				logger.info("Employee has been successfully retrived from DB");
				responseHolder = new ResponseHolder<>(false, "", employee);
				return new ResponseEntity<>(responseHolder, HttpStatus.OK);
			}else {
				logger.info("Employee not found in DB");
				responseHolder = new ResponseHolder<>(true, "Employee not found", null);
				return new ResponseEntity<>(responseHolder, HttpStatus.OK);
			}
		}catch(Exception e) {
			logger.info("There is an exception: "+e.toString());
			responseHolder = new ResponseHolder<>(true, "Something went wrong!", null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<ResponseHolder<Employee>> updateEmployee(Employee employee) {
		ResponseHolder<Employee> responseHolder = null;
		Employee savedEmployee = null;
		try {
			logger.info("Trying to retrieve Employee from DB");
			Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmpId());
			if(optionalEmployee.isPresent()) {
				Employee existingEmployee = optionalEmployee.get();
				if(employee.getEmpName()!=null) {
					existingEmployee.setEmpName(employee.getEmpName());
				}
				if(employee.getEmpEmail()!=null) {
					existingEmployee.setEmpEmail(employee.getEmpEmail());
				}
				if(employee.getEmpPhnNo()>0) {
					existingEmployee.setEmpPhnNo(employee.getEmpPhnNo());
				}
				if(employee.getEmpGender()!=null) {
					existingEmployee.setEmpGender(employee.getEmpGender());
				}
				if(employee.getEmpSalary()>0) {
					existingEmployee.setEmpSalary(employee.getEmpSalary());
				}
				if(employee.getEmpDesignation()!=null) {
					existingEmployee.setEmpDesignation(employee.getEmpDesignation());
				}
				if(employee.getEmpExperience()>0) {
					existingEmployee.setEmpExperience(employee.getEmpExperience());
				}
				if(employee.getEmpDepartment()!=null) {
					existingEmployee.setEmpDepartment(employee.getEmpDepartment());
				}
				if(employee.getEmpDOB()!=null) {
					existingEmployee.setEmpDOB(employee.getEmpDOB());
				}
				if(employee.getEmpSkills()!=null) {
					existingEmployee.setEmpSkills(employee.getEmpSkills());
				}
				if(employee.getEmpAddress()!=null) {
					existingEmployee.setEmpAddress(employee.getEmpAddress());
				}
				if(employee.isCurrentEmployee()!=existingEmployee.isCurrentEmployee()) {
					existingEmployee.setCurrentEmployee(employee.isCurrentEmployee());
				}
				if(employee.isOnBench()!=existingEmployee.isOnBench()) {
					existingEmployee.setOnBench(employee.isOnBench());
				}
				logger.info("Trying to save updated Employee into DB");
				savedEmployee = employeeRepository.save(existingEmployee);
				logger.info("Successfully saved updated Employee into DB");
				responseHolder = new ResponseHolder<>(false, "", savedEmployee);
				return new ResponseEntity<>(responseHolder, HttpStatus.ACCEPTED);
			}else {
				logger.info("Employee not found in DB");
				responseHolder = new ResponseHolder<>(true, "Employee not present", null);
				return new ResponseEntity<>(responseHolder, HttpStatus.OK);
			}
		}catch(Exception e) {
			logger.info("There is an exception: "+e.toString());
			responseHolder = new ResponseHolder<>(true, "Exception while saving to DB: "+e.toString(), null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllEmployees() {
		ResponseHolder<List<Employee>> responseHolder = null;
		try {
			logger.info("Trying to retrieve all Employees from DB");
			List<Employee> listOfEmployees = employeeRepository.findAll();
			logger.info("Successfully retrieved all Employees from DB");
			responseHolder = new ResponseHolder<>(false, "", listOfEmployees);
			return new ResponseEntity<>(responseHolder, HttpStatus.OK);
		}catch(Exception e) {
			logger.info("There is an exception: "+e.toString());
			responseHolder = new ResponseHolder<>(true, "Exception while retrieving employees: "+e.toString(), null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseHolder<Employee>> updateIsCurrentEmp(UpdateIsCurrentEmpModel updateIsCurrentEmpModel) {
		ResponseHolder<Employee> responseHolder = null;
		try {
			logger.info("Trying to update isCurrentEmp status in DB for given Employee");
			employeeRepository.updateIsCurrentEmp(updateIsCurrentEmpModel.getEmpId(), updateIsCurrentEmpModel.isCurrentEmployee());
			logger.info("Successfully updated isCurrentEmp status in DB for given Employee");
			responseHolder = new ResponseHolder<>(true, "Successfully updated the Employee Status", null);
			return new ResponseEntity<>(responseHolder, HttpStatus.OK);
		}catch(Exception e) {
			logger.info("There is an exception: "+e.toString());
			responseHolder = new ResponseHolder<>(true, "Exception while updating the Employee Status: "+e.toString(), null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseHolder<List<Employee>>> listAllOnBenchEmployees() {
		ResponseHolder<List<Employee>> responseHolder = null;
		try {
			logger.info("Trying to retrieve all on bench Employees from DB");
			List<Employee> listOfAllBenchEmployees = employeeRepository.listAllOnBenchEmployees();
			logger.info("Successfully retrieved all on bench Employees from DB");
			responseHolder = new ResponseHolder<>(true, "", listOfAllBenchEmployees);
			return new ResponseEntity<>(responseHolder, HttpStatus.OK);
		}catch(Exception e) {
			logger.info("There is an exception: "+e.toString());
			responseHolder = new ResponseHolder<>(true, "Exception while retrieving employees: "+e.toString(), null);
			return new ResponseEntity<>(responseHolder, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Employee updateWithEmpId(Employee employee) {
		String currentMaxEmpId = null;
		String prefix = "STS-";
		String newEmpId = null;
		try {
			currentMaxEmpId = employeeRepository.retrieveMaxEmpId();
			long lastIndexInt = Long.parseLong(currentMaxEmpId.substring(4));
			newEmpId =  prefix + Long.toString(lastIndexInt+2);
			employee.setEmpId(newEmpId);
			return employee;
		}catch(Exception e) {
			employee.setEmpId(firstEmpId);
			return employee;
		}
	}
	
	private Employee setDesignation(Employee employee) {
		if(employee.getEmpExperience() <= 2) {
			employee.setEmpDesignation("Programmer Analyst Trainee");
		}else if(employee.getEmpExperience() > 2 && employee.getEmpExperience() <= 4) {
			employee.setEmpDesignation("Programmer Analyst");
		}else if(employee.getEmpExperience() > 4 && employee.getEmpExperience() <= 6) {
			employee.setEmpDesignation("Associate");
		}else if(employee.getEmpExperience() > 6 && employee.getEmpExperience() <= 8) {
			employee.setEmpDesignation("Senior Associate");
		}else{
			employee.setEmpDesignation("Manager");
		}
		return employee;
	}

	@Override
	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			logger.info("Trying to authenticate the user: " + authenticationRequest.getUsername());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			logger.info("Successfully authenticated the user: " + authenticationRequest.getUsername());
		}catch(BadCredentialsException e) {
			logger.error("Failed to authenticate the user: " + authenticationRequest.getUsername());
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
