package com.gss.dao;

import java.util.List;

import com.gss.entity.Employee;


public interface EmployeeDAO {

	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public Employee getEmployee(long empId);
	public Employee getEmployeeByEmail(String email);
	public List<Employee> getAllEmployees();
}
