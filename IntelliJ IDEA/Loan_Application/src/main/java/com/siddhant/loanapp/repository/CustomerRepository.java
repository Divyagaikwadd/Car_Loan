package com.siddhant.loanapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.loanapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	List<Customer> findByEmailAndPassword(String email, String password);
	
	Customer findByEmail(String email);
	
	List<Customer> findByCustomerId(String customerId);

	@Override
	<S extends Customer> Optional<S> findOne(Example<S> example);

//	Optional<Customer> findByEmail(String email);
	
}
