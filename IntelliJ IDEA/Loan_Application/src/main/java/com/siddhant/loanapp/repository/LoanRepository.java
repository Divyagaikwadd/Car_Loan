package com.siddhant.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.loanapp.entity.Customer;
import com.siddhant.loanapp.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan,String> {

	Loan save(Customer customerId);
	
	List<Loan> findAllByFkCustomerId(String fkCustomerId);
	
	List<Loan> findByLoanId(String loanId);
	
	List<Loan> findByLoanStatus(String loanStatus);
	
}
