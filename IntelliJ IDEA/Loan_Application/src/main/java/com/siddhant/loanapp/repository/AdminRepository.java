package com.siddhant.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.loanapp.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	List<Admin> findByEmailAndPassword(String email, String password);
	
	Admin findByEmail(String email);
	
	Admin findByAdminId(String adminId);


}
