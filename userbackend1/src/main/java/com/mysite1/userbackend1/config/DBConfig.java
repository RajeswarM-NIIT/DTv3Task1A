package com.mysite1.userbackend1.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysite1.userbackend1.model.UserDetails;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DBConfig {

	
	private  Properties getHibernateProperties()
	 {
		  Properties properties=new Properties();
		  properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.setProperty("hibernate.show_sql", "true");
			return properties;
				  
	 }
	
	//create an instance
		@Autowired
		@Bean(name="sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource)
		{
			LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(dataSource);
			localSessionFactoryBuilder.scanPackages("com");
			localSessionFactoryBuilder.addProperties(getHibernateProperties());
			
			localSessionFactoryBuilder.addAnnotatedClass(UserDetails.class);
			
			return localSessionFactoryBuilder.buildSessionFactory();
		}
		
		@Bean
		public DataSource getDataSource() {
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		    dataSource.setUsername("mysite");
		    dataSource.setPassword("password");
		    return dataSource;
		}
		@Autowired
		@Bean(name="transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
		{
			HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager(sessionFactory);
			return hibernateTransactionManager;
			
		}
		
		//////////
}
/*
	@Bean
	public SessionFactory sessionFactory() {
		System.out.println("hello");
		
		LocalSessionFactoryBuilder lsf=new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		//Class modelclasses[] = {UserDetails.class, UserProfile.class, Job.class};
		
		//return lsf.addAnnotatedClasses(modelclasses)
	
		 lsf.scanPackages("com.mysite.userbackend");
		 
		 return lsf.buildSessionFactory();
		
	}
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("mysite");
	    dataSource.setPassword("password");
	    return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		return new HibernateTransactionManager(sessionFactory());
	}
}
*/