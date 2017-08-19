package com.mysite1.userbackend1.dao;


import org.springframework.stereotype.Component;

import com.mysite1.userbackend1.model.UserDetails;

@Component
public interface UserDetailsDAOInt {

	public abstract UserDetails addUser(UserDetails userDetails);
	public UserDetails loginCheck(UserDetails userDetails);
}
