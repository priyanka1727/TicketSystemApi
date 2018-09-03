package com.gss.rest.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gss.exceptions.EmployeeException;
import com.gss.exceptions.ErrorResponse;
import com.gss.rest.dto.EmployeeDTO;
import com.gss.rest.dto.LoginDTO;
import com.gss.services.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired(required=true)
	private LoginService loginService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(
			value="/login",
			method=RequestMethod.POST,
			consumes="application/json",
			produces="application/json"
	)
	public ResponseEntity<LoginDTO>  login(@RequestBody LoginDTO login) throws EmployeeException {
		LoginDTO emp;
		System.out.println("calling login  method"+login.getEmail());
		emp = loginService.login(login);
		if(emp == null) {
			throw new EmployeeException("No result found");
		}
		return new ResponseEntity<LoginDTO>(emp, HttpStatus.OK);

	}
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
