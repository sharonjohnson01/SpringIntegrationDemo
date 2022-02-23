package com.demo.config;

import com.demo.helper.EmployeeComparator;
import com.demo.jdbc.model.EmployeeMapper;
import com.demo.model.Address;
import com.demo.model.Employee;
import com.demo.model.EmployeeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TreeSet;



/**
 * 
 * Creates Treeset Bean to cache data
 * @author sharo
 *
 */

@Configuration
public class CachingConfig {
	
	@Autowired
	public DataSource datasource;
	
	 @Bean
	 public TreeSet<Employee> employeeSet(){
		return this.loadAllData();		 
	 }

		public TreeSet<Employee> loadAllData() {
		
			TreeSet<Employee> employeeTreeset = new TreeSet<>(new EmployeeComparator());
			try {
				Connection conn = datasource.getConnection();
				Statement stmt = conn.createStatement();
				String query = "Select * from Employee";
				ResultSet resultSet = stmt.executeQuery(query);

				while (resultSet.next()) {
					Employee employee = new Employee();
					employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
					employee.setFirstName(resultSet.getString("First_Name"));
					employee.setLastName(resultSet.getString("Last_Name"));
					employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
					employee.setEmailId(resultSet.getString("EMAILID"));
					employee.setSalary(resultSet.getInt("SALARY"));
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					employee.setDateOfBirth(sdf.format(resultSet.getDate("Date_Of_Birth")));
					Address address = new Address();
					address.setCity(resultSet.getString("CITY"));
					address.setState(resultSet.getString("STATE"));
					address.setStreetName(resultSet.getString("ADDRESS"));
					employee.setAddress(address);
					employeeTreeset.add(employee);
				
				}

			} catch (SQLException e) {
				System.out.println("error occured while loading data "+e.getMessage());
				

			}
			return employeeTreeset;
		}
   
	
    
}

