package com.sts.fullprofile.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties 
public class EmployeeFullprofileMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFullprofileMicroserviceApplication.class, args);
	}

}
