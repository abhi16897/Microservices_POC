package com.seveneleven.employeemanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seveneleven.employeemanagementservice.model.Employee;

@Repository
public interface EmployeemngRepository extends JpaRepository<Employee, Integer> {
			Employee findByEmployeeId(int id);
}
