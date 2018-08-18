package com.gss.services;

import java.util.Date;
import java.util.List;

import com.gss.entity.Customer;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer getCustomer(long customerId);
	public List<Customer> getAllCustomers();
	public  void deleteCustomer(long customerId);
}
