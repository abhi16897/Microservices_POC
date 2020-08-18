package com.seveneleven.employeedetails.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.seveneleven.employeedetails.model.AllEmployees;
import com.seveneleven.employeedetails.model.Employee;
import com.seveneleven.employeedetails.model.ResponseTransfer;

@RestController
@RefreshScope
public class EmployeeDetailController {
		@Value("${url}")
		String url;
		Employee employee;
		AllEmployees allemployees=new AllEmployees();
		@Autowired
		private RestTemplate restTemplate;
		
		//=================== post the Employee===========
		@HystrixCommand(fallbackMethod = "getFallbackaddEmployee",
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="6000")
				})
		@PostMapping("/addemployees")
		public ResponseTransfer addEmployee(@RequestBody Employee employee) {
			ResponseTransfer response=restTemplate.postForObject("http://employee-management-service/employeeadd", employee, ResponseTransfer.class);
			return response;
		}
		public ResponseTransfer getFallbackaddEmployee(@RequestBody Employee employee) {
			return new ResponseTransfer("Service is Not Working");
		}
		
		//======================== Display All the Employee=======================
		@HystrixCommand(fallbackMethod = "getFallbackEmployees",
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="6000")
				})
		@GetMapping("/displayemployees")
		public AllEmployees getallemployees(){
			RestTemplate rt=new RestTemplate();
			AllEmployees allemployees=rt.getForObject(url, AllEmployees.class);
			return allemployees;
		}
		public AllEmployees getFallbackEmployees() {
			return new AllEmployees();
		}
		
		//============================ Update By Employee ID=========================
		
		@Async
		public void updateEmployee(Employee employee) {
			restTemplate.put("http://employee-management-service/update", employee);
		}
		
		@HystrixCommand(fallbackMethod = "getFallbackupdateEmployee",
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="6000")
				})
		@PutMapping("/updateemployee")
		public ResponseTransfer employeeUpdate(@RequestBody Employee employee) {
			try {
				updateEmployee(employee);
			}catch(Exception e) {
				return new ResponseTransfer("Not Able to Update");
			}
			
			return new ResponseTransfer("Sucsessfully Updated");
		}
		public ResponseTransfer getFallbackupdateEmployee(@RequestBody Employee employee) {
			return new ResponseTransfer("Service is Not Working");
		}
		
		//=========================Delete By Employee ID=================
		@HystrixCommand(fallbackMethod = "getFallbackdeleteEmployee",
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="6000")
				})
		@DeleteMapping("deleteemployee/{employeeid}")
		public ResponseTransfer deleteEmployee(@PathVariable String employeeid) {
			try {
				restTemplate.delete("http://employee-management-service/delete/{employeeid}", employeeid);
			}catch(Exception e) {
				return new ResponseTransfer("Not Able to Delete");
			}
			
			return new ResponseTransfer("Sucessfully Deleted");
		}
		
		public ResponseTransfer getFallbackdeleteEmployee(@PathVariable String employeeid) {
			return new ResponseTransfer("Service is Not Working");
		}
		
		//==============================Get By Employee ID ======================
		@HystrixCommand(fallbackMethod = "getFallbyEmployee",
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="6000")
				})
		@GetMapping("/getByid/{empid}")
		public List<Employee> getByempId(@PathVariable int empid){
			AllEmployees employees=restTemplate.getForObject("http://employee-management-service/allemployees", AllEmployees.class);
			List<Employee> filteredlist=employees.getAllEmployees().stream().filter(emp->empid==emp.getEmployeeId()).collect(Collectors.toList());
			return filteredlist;
		}
		public ResponseTransfer getFallbyEmployee(@PathVariable int empid) {
			return new ResponseTransfer("Service is Not Working");
		}
}
