package com.demo.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jndi.JndiTemplate;

import com.demo.model.Address;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;

/**
 * Database configuration class
 * 
 * @author sharon
 *
 */
@Configuration
@ComponentScan
@PropertySource("classpath:config/db.properties")
public class DatabaseConfig {
	private static final Logger LOGGER = Logger.getLogger(EmployeeService.class);
	
	 @Value("${db.jndiUrl}")
	    private String jndiUrl;
	 
	   /**
	     * Sets Database connection
	     * @return dataSource bean
	     */
	@Bean
	public DataSource getDataSource() throws SQLException {

		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = jndi.lookup(jndiUrl, DataSource.class);
		} catch (NamingException e) {
			LOGGER.error("NamingException for java:comp/env/jdbc/primaryDB", e);
			throw new SQLException("unable to connect tp db "+e.getMessage());
		}
		return dataSource;
	}
	
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	 
	 
	}
	
	

