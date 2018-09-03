package com.gss.services;

import java.util.List;

import com.gss.rest.dto.EmployeeDTO;


public interface EmployeeService {

	public EmployeeDTO addEmployee(EmployeeDTO emp);
	public EmployeeDTO updateEmployee(EmployeeDTO emp);
	public void deleteEmployee(long empId);
	public EmployeeDTO getEmployee(long empId);
	public EmployeeDTO getEmployeeByEmail(String email);
	public List<EmployeeDTO> getAllEmployees();
}
