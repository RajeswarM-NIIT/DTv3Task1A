package com.mysite1.userrest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite1.userbackend1.dao.UserDetailsDAOInt;
import com.mysite1.userbackend1.model.UserDetails;

@Controller
public class HomeController {

	@Autowired
	private UserDetailsDAOInt userDetailsDAOInt;
	
	
	@RequestMapping("/")
	public String displayHomePage(Model m){
		System.out.println("\nController started.....");
		UserDetails userDetails = new UserDetails();
		userDetails.setUsername("Rajeswar");
		userDetails.setUserrole("ROLE_USER");
		userDetails.setPassword("1234");
		userDetails.setEnabled(true);
		UserDetails userDetails1 = userDetailsDAOInt.addUser(userDetails);
		m.addAttribute("userid", userDetails1.getUserid());
		
		return "index";
	}
	
	
}
