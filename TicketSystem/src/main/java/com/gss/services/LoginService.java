package com.gss.services;

import com.gss.rest.dto.EmployeeDTO;
import com.gss.rest.dto.LoginDTO;

public interface LoginService {
	public EmployeeDTO login(LoginDTO login);
}
