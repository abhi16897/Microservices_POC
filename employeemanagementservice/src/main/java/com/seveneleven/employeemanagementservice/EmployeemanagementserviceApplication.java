package com.seveneleven.employeemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeemanagementserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementserviceApplication.class, args);
	}

}
