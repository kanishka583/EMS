package com.sts.fullprofile.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sts.fullprofile.employee.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

	
	@Modifying(clearAutomatically = true, flushAutomatically = true) 
	@Transactional
	@Query("UPDATE Employee SET isCurrentEmployee = :isCurrentEmployee WHERE empId = :empId")
	void updateIsCurrentEmp(@Param("empId") String empId,@Param("isCurrentEmployee") boolean isCurrentEmployee);

	@Query("SELECT e FROM Employee e WHERE e.isOnBench = TRUE AND e.isCurrentEmployee = TRUE ")
	List<Employee> listAllOnBenchEmployees();
	
	@Query("SELECT MAX(empId) FROM Employee")
	String retrieveMaxEmpId();
}
