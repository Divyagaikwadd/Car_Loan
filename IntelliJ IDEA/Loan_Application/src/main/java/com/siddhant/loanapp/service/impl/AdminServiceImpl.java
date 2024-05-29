package com.siddhant.loanapp.service.impl;

import java.util.List;

import com.siddhant.loanapp.entity.Customer;
import com.siddhant.loanapp.repository.AdminRepository;
import com.siddhant.loanapp.repository.CustomerRepository;
import com.siddhant.loanapp.service.AdminService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.loanapp.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private  AdminRepository adminRepository;

	@Autowired
	private  CustomerRepository customerRepository;


//	@Override
//	public String getCustomerDetails(String email, String password) {
//		Customer dbPass=customerRepository.findByEmail(email);
//		if(dbPass!=null){
//			boolean isMatchPass= BCrypt.checkpw(password,dbPass.getPassword());
//			System.out.println("The information is : "+ isMatchPass);
//			if (isMatchPass && email.equals(dbPass.getEmail())){
//				List<Customer> getCustomerDetails = customerRepository.findByEmailAndPassword(email,dbPass.getPassword());
//				Customer getMail = customerRepository.findByEmail(email);
////				if (getCustomerDetails.isEmpty()) {
////					return null;
////				}
//				return getMail.getCustomerId();
//			}
//		}
//		return  null;
//	}


	@Override
	public String validateAdmin(String email, String password) {
		Customer dbUser=customerRepository.findByEmail(email);
		if(dbUser!=null){
	boolean isMatchPass= BCrypt.checkpw(password, dbUser.getPassword());
			if (isMatchPass && email.equals(dbUser.getEmail())){
				List<Customer> getAdminDetails = customerRepository.findByEmailAndPassword(email,dbUser.getPassword());
				Customer getMail = customerRepository.findByEmail(email);
				return getMail.getCustomerId();
			}
		}
		return  null;
	}

	@Override
	public String getFirstName(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		return admin.getFName();
	}

	@Override
	public String getLastName(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		return admin.getLName();
	}

}
