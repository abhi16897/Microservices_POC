package com.seveneleven.employeedetails.model;

import java.util.ArrayList;


public class AllEmployees {
	
	
	private ArrayList<Employee> allEmployees;
	private String name;
	public ArrayList<Employee> getAllEmployees() {
		return allEmployees;
	}

	public void setAllEmployees(ArrayList<Employee> allEmployees) {
		this.allEmployees = allEmployees;
		
}
}
