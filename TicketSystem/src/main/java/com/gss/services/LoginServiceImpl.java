package com.gss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gss.dao.LoginDAO;
import com.gss.entity.Employee;
import com.gss.rest.dto.LoginDTO;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired(required=true)
	private LoginDAO loginDAO;
	
	public  LoginDTO login(LoginDTO login) {
		LoginDTO emp = new LoginDTO();
		System.out.println("adding data in service"+login.getEmail());
		Employee employee = new Employee();
		employee.setEmail(login.getEmail());
		employee.setPassword(login.getPassword());
		employee = loginDAO.login(employee);
		if(employee != null) {
			emp.setEmpId(employee.getEmployeeId());
			emp.setContact(employee.getContact());
			emp.setEmail(employee.getEmail());
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			return emp;
		}
		return null;
	}
}
