package com.gss.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
			consumes={MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE}
	)
	public  EmployeeDTO login(@RequestBody LoginDTO login){
		EmployeeDTO emp;
		System.out.println("calling login  method"+login.getEmail());
		emp = loginService.login(login);
		return emp;
	}
}
