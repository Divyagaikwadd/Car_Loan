package com.siddhant.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.loanapp.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,String> {
	
	List<Payment> findByfkloanId(String loanId);
	
	 Payment findByfkloanIdAndRemainCycles(String loanId, Integer remainCycles);
	
	List<Payment> findByfkloanIdIn(List<String> loanIds);
}
