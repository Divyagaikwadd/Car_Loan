package com.siddhant.loanapp.service;

import java.util.List;

import com.siddhant.loanapp.entity.Customer;
import com.siddhant.loanapp.entity.Loan;

public interface CustomerService {
		
	public Customer registerCustomer(Customer customer, String pass);
	
	public String getCustomerDetails(String email, String password);
	
	public List<Customer> getCustomerProfile(String customerId);
	
	public Customer getCustomer(Loan loan);
	
}
