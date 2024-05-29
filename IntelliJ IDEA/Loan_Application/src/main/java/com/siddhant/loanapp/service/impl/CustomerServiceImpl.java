package com.siddhant.loanapp.service.impl;

import java.util.List;
import java.util.UUID;

import com.siddhant.loanapp.repository.CustomerRepository;
import com.siddhant.loanapp.repository.LoanRepository;
import com.siddhant.loanapp.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.loanapp.entity.Customer;
import com.siddhant.loanapp.entity.Loan;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public Customer registerCustomer(Customer customer, String Encodedpass) {
		customer.setCustomerId(generateKey("CID-"));
		customer.setPassword(Encodedpass);
		return customerRepository.save(customer);
	}
//	@Override
//	public Customer registerCustomer(Customer customer) {
//		customer.setCustomerId(generateKey("CID-"));
//		return customerRepository.save(customer);
//	}
	private String generateKey(String prefix) {
		String key = UUID.randomUUID().toString().split("-")[0];
		return prefix + key;
	}

	@Override
	public String getCustomerDetails(String email, String password){
		Customer dbPass=customerRepository.findByEmail(email);
		if(dbPass!=null){
		boolean isMatchPass= BCrypt.checkpw(password,dbPass.getPassword());
			System.out.println("The information is : "+ isMatchPass);
			if (isMatchPass && email.equals(dbPass.getEmail())){
				List<Customer> getCustomerDetails = customerRepository.findByEmailAndPassword(email,dbPass.getPassword());
				Customer getMail = customerRepository.findByEmail(email);
//				if (getCustomerDetails.isEmpty()) {
//					return null;
//				}
				return getMail.getCustomerId();
			}
		}
			return  null;
	}
	@Override
	public List<Customer> getCustomerProfile(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}
	@Override
	public Customer getCustomer(Loan loan) {
		List<Loan> getLoans = loanRepository.findByLoanId(loan.getLoanId());
		for(Loan loans : getLoans) {
			System.out.println("^^^^"+loan.getFkCustomerId());
			if(loans.getLoanId().equals(loan.getLoanId())) {
				System.out.println("****"+loan.getFkCustomerId());
				List<Customer> customers = customerRepository.findByCustomerId(loan.getFkCustomerId());
				for(Customer customer : customers) {
				System.out.println("!!!!!!"+customer.getAccno());
					return customer;
				}
				
			}
		}
		return null;
	}
}
