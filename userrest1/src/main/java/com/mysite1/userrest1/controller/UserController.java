package com.mysite1.userrest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysite1.userbackend1.dao.UserDetailsDAOInt;
import com.mysite1.userbackend1.model.UserDetails;

@RestController
public class UserController {
	@Autowired
	private UserDetailsDAOInt userDetailsDAOInt;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserDetails userdetails){
		logger.debug("UserController : register(): " + userdetails.getUserid());
		userdetails.setEnabled(true);		
		userdetails.setUserrole("ROLE_USER");
		
		UserDetails newUser = userDetailsDAOInt.addUser(userdetails);
		if(newUser==null){
			logger.debug("User is not registerd");
			Error error = new Error(2,"Could not insert user details");
			return new ResponseEntity<Error> (error,HttpStatus.CONFLICT);// 
		}
		else{
			logger.debug("User id is generated");
			//String username = newUser.getUsername();		
			return new  ResponseEntity<UserDetails> (newUser,HttpStatus.OK);
		}	
		
	}
	
	
	
	@RequestMapping(value="/logincheck", method=RequestMethod.POST)
	public ResponseEntity <?> logincheck(@RequestBody UserDetails userdetails,HttpSession session){
		logger.debug("Entering UserController : Login()");
		//String userid=userdetails.getUserid();
		UserDetails validuser = userDetailsDAOInt.loginCheck(userdetails);
		
		logger.debug("\n" + userdetails.getUserid());
		
		if(validuser==null){
			logger.debug("validuser is null");
			Error error = new Error(1,"User does not exists");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);// 401
		}
		else{		
			session.setAttribute("presentUser", validuser);
			return new  ResponseEntity<UserDetails> (validuser, HttpStatus.OK);
		}		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public ResponseEntity<?> logout(HttpSession session){	
		System.out.println("\nLogout in controller");
		session.removeAttribute("presentUser");		
		session.invalidate();
		return new  ResponseEntity<Void> (HttpStatus.OK);		
	}
	
}
