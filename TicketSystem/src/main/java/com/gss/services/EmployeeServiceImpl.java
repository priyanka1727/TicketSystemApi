package com.gss.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gss.dao.EmployeeDAO;
import com.gss.entity.Employee;
import com.gss.rest.dto.EmployeeDTO;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired(required=true)
	private EmployeeDAO employeeDAO;
	
	public EmployeeDTO addEmployee(EmployeeDTO emp) {
		System.out.println("adding data in service"+emp.getUserName());
		Employee employee = new Employee();
		employee.setUserName(emp.getUserName());
		employee.setEmail(emp.getEmail());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setPassword(emp.getPassword());
		employee.setContact(emp.getContact());
		employee = employeeDAO.addEmployee(employee);
		
		emp.setEmpId(employee.getEmployeeId());
		return emp;
	}

	public EmployeeDTO updateEmployee(EmployeeDTO emp) {
		
		Employee employee = new Employee();
		employee.setUserName(emp.getUserName());
		employee.setEmail(emp.getEmail());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setPassword(emp.getPassword());
		employee.setContact(emp.getContact());
		employee = employeeDAO.updateEmployee(employee);
		
		return emp;
	}

	public void deleteEmployee(long empId) {
		Employee employee =employeeDAO.getEmployee(empId);
		employeeDAO.deleteEmployee(employee);
	}

	public EmployeeDTO getEmployee(long empId) {
		
		Employee emp =employeeDAO.getEmployee(empId);
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setUserName(emp.getUserName());
		empDTO.setEmail(emp.getEmail());
		empDTO.setFirstName(emp.getFirstName());
		empDTO.setLastName(emp.getLastName());
		empDTO.setPassword(emp.getPassword());
		empDTO.setEmpId(emp.getEmployeeId());
		empDTO.setContact(emp.getContact());
		return empDTO;
	}
	
public EmployeeDTO getEmployeeByEmail(String email) {
		
		Employee emp =employeeDAO.getEmployeeByEmail(email);
		if(emp != null) {
		System.out.println("emp "+emp.getFirstName());
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setUserName(emp.getUserName());
		empDTO.setEmail(emp.getEmail());
		empDTO.setFirstName(emp.getFirstName());
		empDTO.setLastName(emp.getLastName());
		empDTO.setPassword(emp.getPassword());
		empDTO.setEmpId(emp.getEmployeeId());
		empDTO.setContact(emp.getContact());
		return empDTO;
		}
		else
			return null;
	}

	public List<EmployeeDTO> getAllEmployees() {
		
		List<Employee> list = employeeDAO.getAllEmployees();
		
		List<EmployeeDTO> dtoList  = new ArrayList<EmployeeDTO>();
		for(Employee emp:list){
			System.out.println("List "+emp.getEmployeeId());
			EmployeeDTO empDTO = new EmployeeDTO();
			empDTO.setUserName(emp.getUserName());
			empDTO.setEmail(emp.getEmail());
			empDTO.setFirstName(emp.getFirstName());
			empDTO.setLastName(emp.getLastName());
			empDTO.setPassword(emp.getPassword());
			empDTO.setEmpId(emp.getEmployeeId());
			empDTO.setContact(emp.getContact());
			dtoList.add(empDTO);
		}
		
		return dtoList 	;
	}

}
