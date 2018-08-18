package com.gss.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gss.entity.Employee;

@Repository("loginDAO")
@Transactional
public class LoginDAOImpl implements LoginDAO {
	
	@PersistenceContext
	public EntityManager entityManager;
	

	@Transactional(readOnly=true)
	public Employee login(Employee employee) {
		String email=employee.getEmail();
		String pass=employee.getPassword();
		String sql = "SELECT emp FROM Employee emp WHERE emp.email='"+email+"' and emp.password ='"+pass+"'";
		System.out.println("adding data in dao"+sql);
		try{
			return (Employee) entityManager.createQuery(sql).getSingleResult();
		}catch(Exception e){
			throw e;
		}
	}

}
