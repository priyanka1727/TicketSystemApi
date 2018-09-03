package com.gss.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		String sql = "SELECT emp FROM Employee emp WHERE emp.email=:emailId and emp.password =:passWord";
		System.out.println("adding data in dao"+sql);
		try {
			return (Employee) entityManager.createQuery(sql)
				.setParameter("emailId", employee.getEmail())
				.setParameter("passWord", employee.getPassword())
				.getSingleResult();
		} catch( NoResultException nre) {
			return null;
		}
	}
}
