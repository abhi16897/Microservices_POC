package com.seveneleven.employeemanagementservice.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seveneleven.employeemanagementservice.model.Employee;
import com.seveneleven.employeemanagementservice.repository.EmployeemngRepository;

@Service
public class EmployeeMngService {
	@Autowired
	EmployeemngRepository employeemngRepository;
	@Transactional
	public void employeeAddService(Employee employee) {
		employeemngRepository.save(employee);
	}
	@Transactional
	public ArrayList<Employee> getAllEmployees(){
		return (ArrayList<Employee>) employeemngRepository.findAll();
	}
	
	@Transactional
	public void updateEmployeeService(Employee employee) {
		employeemngRepository.save(employee);
	}
	@Transactional
	public void deleteEmployeeService(String id) {
		Employee employee=employeemngRepository.findByEmployeeId(Integer.parseInt(id));
		employeemngRepository.delete(employee);
	}
	@Transactional
	public Employee getEmployeebyId(int id) {
		Employee employee=employeemngRepository.findByEmployeeId(id);
		return employee;
	}

}
