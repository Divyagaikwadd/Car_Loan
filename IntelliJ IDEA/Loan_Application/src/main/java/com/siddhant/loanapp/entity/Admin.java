	
  package com.siddhant.loanapp.entity;

  import jakarta.persistence.*;
  import jakarta.transaction.Transactional;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  
  @Entity
  @Transactional
  @Data
  @NoArgsConstructor
  @Table(name = "admin") 
  public class Admin {
  
	  	@Id
	  	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "admin_id", nullable = false)
		private String adminId;
	  	
	  	@Column(name = "fname", nullable = false)
		private String fName;
		
		@Column(name = "lname")
		private String lName;
		
		@Column(name = "a_email", nullable = false)
		private String email;
		
		@Column(name = "a_password", nullable = false)
		private String password;	  	
	
  }
 