package com.siddhant.loanapp.controller;

import com.siddhant.loanapp.repository.CustomerRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siddhant.loanapp.entity.Customer;
import com.siddhant.loanapp.entity.Loan;
import com.siddhant.loanapp.service.CustomerService;
import com.siddhant.loanapp.service.LoanService;
import com.siddhant.loanapp.service.PaymentService;

import java.util.List;

@Controller
@SessionAttributes("customer_id")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoanService loanService;

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private PaymentService paymentService;

	// handle method to handle list of customers and return mode and view

	@PostMapping("/addcustomer")
	public String registerCustomer(@ModelAttribute("customer") Customer customer) {
		String encodePass= BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt(12));
		customerService.registerCustomer(customer, encodePass );
		return "redirect:/RegisterSuccess";
	}
	@GetMapping("/AdminDashboard")
	public  String getAdminDashboard(Model model){
		List<Loan> loans = loanService.getPendingLoan();
		model.addAttribute("loans", loans);
		return "AdminDashboard";
	}

	@GetMapping("/RegisterSuccess")
	public String registerSuccess() {
		return "RegisterSuccess";
	}

	@PostMapping("/verifycustomer")
	public String verifyCustomer(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password){
		Customer dbData=customerRepository.findByEmail(email);
		String role=dbData.getRole();
		String customerId = customerService.getCustomerDetails(email, password);
		model.addAttribute("customer_id", customerId);
		if (customerId != null) {
			if(role.equals("admin")){
				//model.addAttribute("name", dbData.getFirstName());
				return  "redirect:/AdminDashboard";
			}else{
				return "redirect:/CustomerDashBoard";
			}
		} else
			return "redirect:/Login";
	}

	@GetMapping("/CustomerDashBoard")
	public String getDashBoard(Model model) {
		String fkCustomerId = (String) model.getAttribute("customer_id");
		model.addAttribute("loans", loanService.getByFkCustomerId(fkCustomerId));
		model.addAttribute("payments", paymentService.getPayment(loanService.getByFkCustomerId(fkCustomerId)));
		Loan emptyLoan = new Loan();
		model.addAttribute("emptyLoan", emptyLoan);
		return "CustomerDashBoard";
	}

	@GetMapping("/CustomerProfile")
	public String getProfile(Model model) {
		String customerId = (String) model.getAttribute("customer_id");
		System.out.println("this --->"+customerId);
		System.out.println("See this => "+customerService.getCustomerProfile(customerId));
		model.addAttribute("profile", customerService.getCustomerProfile(customerId));
		return "CustomerProfile";
	}

}
