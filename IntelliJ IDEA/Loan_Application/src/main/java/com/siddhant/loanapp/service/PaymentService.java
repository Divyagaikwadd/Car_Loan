package com.siddhant.loanapp.service;

import java.util.List;

import com.siddhant.loanapp.entity.Loan;
import com.siddhant.loanapp.entity.Payment;

public interface PaymentService {
	
	Payment savePayment(Payment objPayment, String fkloanId);
	
	Payment getCycles(String loanId);
	
	List<Payment> getPayment(List<Loan> loans);
}
