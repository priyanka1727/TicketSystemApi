package com.gss.rest.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gss.exceptions.CustomException;
import com.gss.exceptions.CustomExceptionHandler;
import com.gss.rest.dto.EmployeeDTO;
import com.gss.rest.dto.LoginDTO;
import com.gss.rest.dto.StatusDTO;
import com.gss.services.EmployeeService;

@RestController
@EnableWebMvc
@RequestMapping("/employee")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController extends CustomExceptionHandler {
	
	@Autowired(required=true)
	private EmployeeService employeeService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET,value="/get/{email:.+}", headers="Accept=*/*",produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable("email") String email,@RequestBody(required=false) EmployeeDTO emp) throws CustomException{
		System.out.println("calling get Employee method " +email);
		//System.out.println("priya "+employeeService.getEmployeeByEmail(email).getFirstName());
		emp= employeeService.getEmployeeByEmail(email);
		if(emp == null) {
			throw new CustomException("No result found Here");
		}
		return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
		
		
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
	
	
	@RequestMapping(value = "pdf/pdfreport",method = RequestMethod.GET ,headers="Accept=*/*",
    produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource>  getAll(){
		
				List<EmployeeDTO> employees=(List<EmployeeDTO>)employeeService.getAllEmployees();
				
				ByteArrayInputStream bis = GeneratePdfReport.employeesReport(employees);
				System.out.println("calling PDF  method" +bis);
		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=Employee.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
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
