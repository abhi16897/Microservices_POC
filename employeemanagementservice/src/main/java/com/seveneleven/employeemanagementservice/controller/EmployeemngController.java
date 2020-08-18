package com.seveneleven.employeemanagementservice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seveneleven.employeemanagementservice.model.AllEmployees;
import com.seveneleven.employeemanagementservice.model.Employee;
import com.seveneleven.employeemanagementservice.model.ResponseTransfer;
import com.seveneleven.employeemanagementservice.services.EmployeeMngService;

@RestController
public class EmployeemngController {
	@Autowired
	EmployeeMngService emloyeemngService;
	
	AllEmployees allemployees=new AllEmployees();
	
	@PostMapping("/employeeadd")
	@ResponseBody
	public ResponseTransfer addemployee(@RequestBody Employee employee) {
		try {
			emloyeemngService.employeeAddService(employee);
			return new ResponseTransfer("Sucsessfully Added");
		}catch(Exception e) {
			return new ResponseTransfer("Not Able To Add");
		}
	}
	@GetMapping("/allemployees")
	public AllEmployees displayAllEmployees() {
		allemployees.setAllEmployees(emloyeemngService.getAllEmployees());
		return allemployees;
	}
	@PutMapping("/update")
	public void updateEmployee(@RequestBody Employee employee) {
			emloyeemngService.updateEmployeeService(employee);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable String id) {
			emloyeemngService.deleteEmployeeService(id);
	}
	@GetMapping("/getbyemp/{id}")
	public Employee getbyId(@PathVariable int id) {
		return emloyeemngService.getEmployeebyId(id);
	}
}
