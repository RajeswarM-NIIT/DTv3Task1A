package com.mysite1.userbackend1;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mysite1.userbackend1.dao.UserDetailsDAOInt;
import com.mysite1.userbackend1.model.UserDetails;

/**
 * Hello world!
 *
 */
public class App 
{
	private static AnnotationConfigApplicationContext context;
	private static UserDetailsDAOInt userDao;
	private static UserDetails userDetails;
   
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mysite1.userbackend1");
		context.refresh();
		userDao=(UserDetailsDAOInt)context.getBean("userDetailsDAOInt");		
	}
	
	@Test
	public void Test(){
		userDetails = new UserDetails();
		userDetails.setUsername("Rajeswar");
		userDetails.setPassword("1234");
		userDetails.setEnabled(true);
		userDetails.setUserrole("ROLE_USER");
		assertEquals("success",true,userDao.addUser(userDetails));
	}
}
