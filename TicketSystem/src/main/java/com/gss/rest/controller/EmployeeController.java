package com.gss.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gss.rest.dto.EmployeeDTO;
import com.gss.rest.dto.StatusDTO;
import com.gss.services.EmployeeService;

@RestController
@EnableWebMvc
@RequestMapping("/employee")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	@Autowired(required=true)
	private EmployeeService employeeService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET,value="/get/{email:.+}", headers="Accept=*/*",produces={MediaType.APPLICATION_JSON_VALUE})
	public EmployeeDTO getEmployeeByEmail(@PathVariable("email") String email){
		System.out.println("calling get Employee method " +email);
		System.out.println("priya "+employeeService.getEmployeeByEmail(email).getFirstName());
		return employeeService.getEmployeeByEmail(email);
		
		
	}
	
	@GetMapping(value="/{empId}",produces={MediaType.APPLICATION_JSON_VALUE})
	public EmployeeDTO getEmployee(@PathVariable("empId") long empId){
		System.out.println("calling get Employee method");
		return employeeService.getEmployee(empId);
		
	}
	
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	public  EmployeeDTO addEmployee(@RequestBody EmployeeDTO emp){
		
		System.out.println("calling add employee  method"+emp.getEmail());
		emp = employeeService.addEmployee(emp);
		return emp;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE})
	public  EmployeeDTO updateEmployee(@RequestBody EmployeeDTO emp){
		System.out.println("calling add employee  method"+emp.getEmail());
			emp = employeeService.updateEmployee(emp);
		
		return emp;
	}
	
	@GetMapping(value="/all",produces={MediaType.APPLICATION_JSON_VALUE})
	public List<EmployeeDTO> getAll(){
		return employeeService.getAllEmployees();
		
	}
	
		
	@GetMapping(value="/delete/{empId}",produces={MediaType.APPLICATION_JSON_VALUE})
	public StatusDTO deleteEmployee(@PathVariable("empId") long empId){
		
		employeeService.deleteEmployee(empId);
		StatusDTO status = new StatusDTO();
		status.setMessage("Employee Deleted Successfully");
		status.setStatus(200);
		return status;
		
	}
	
}
