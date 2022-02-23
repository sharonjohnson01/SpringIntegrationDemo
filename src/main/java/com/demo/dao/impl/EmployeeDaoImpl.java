package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.EmployeeDao;
import com.demo.model.Address;
import com.demo.model.Employee;

/**
 * Database Class to load data from database table
 * @author sharo
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class);

@Autowired
TreeSet<Employee> employeeTreeset;

@Autowired
DataSource ds;
 
 /**
  * Filters from TreeSet names matching to input parameter
  *@return TreeSet of employee Object
  */
  
	public Set<Employee> getAllEmployeeName(String name) {
		Set<Employee> emp = new LinkedHashSet<Employee>();
		if(name.contains(" ")) {
			String[] names= name.split(" ");
			String fName=names[0];
			String lastName =names[1];
			Predicate<Employee> matchFn=e->e.getFirstName().equalsIgnoreCase(fName);
			Predicate<Employee>matchLn =e->e.getLastName().equalsIgnoreCase(lastName);
			emp=employeeTreeset.stream().filter(a->a.getFirstName().equalsIgnoreCase(fName) && a.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toCollection(LinkedHashSet::new));
			if(emp.size()==0) {
				emp=employeeTreeset.stream().filter(matchFn).collect(Collectors.toCollection(LinkedHashSet::new));
				if(emp.size()==0) {
					emp=employeeTreeset.stream().filter(matchLn).collect(Collectors.toCollection(LinkedHashSet::new));
				}
			}

	}else{
			emp.addAll(employeeTreeset.stream().filter(a->a.getFirstName().startsWith(name)).collect(Collectors.toCollection(LinkedHashSet::new)));
			if(emp.size()<10||emp.size()==0){
				emp.addAll(employeeTreeset.stream().filter(a->a.getLastName().startsWith(name)).collect(Collectors.toCollection(LinkedHashSet::new)));
			}if(emp.size()<10||emp.size()==0) {
				emp.addAll(employeeTreeset.stream().filter(a->a.getFirstName().contains(name)).collect(Collectors.toCollection(LinkedHashSet::new)));
			}if(emp.size()<10||emp.size()==0) {
				emp.addAll(employeeTreeset.stream().filter(a->a.getLastName().contains(name)).collect(Collectors.toCollection(LinkedHashSet::new)));
			}


		}
		
	return emp;
		
	}


	@Override
	public Employee getEmployeeDetails(int employeId) throws SQLException {
		Connection conn = ds.getConnection();
		String query = "Select * from Employee_Details where EMPLOYEE_ID =?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1,employeId);
		ResultSet	resultSet = pstmt.executeQuery();
		Employee employee =employeeTreeset.stream().filter(e-> e.getEmployeeId()==employeId).findAny().orElse(null);
		while(resultSet.next()) {
			employee.setDepartment(resultSet.getString("Department"));
			employee.setEmploymentType(resultSet.getString("Employment_Type"));
			employee.setHomeAddress(resultSet.getString("Home_Address"));
			employee.setEmergencyContactNumber(resultSet.getString("Emergency_Phone_Number"));
		}
		return employee;
	}

	@Override
	public Boolean updateEmployee(Employee emp) throws SQLException {
	String query ="update employee set emailid = '"+emp.getEmailId()+"',PHONE_NUMBER='"+emp.getPhoneNumber()+"' where employee_id="+emp.getEmployeeId()+"";
	
	Connection conn = ds.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(query);
int count=pstmt.executeUpdate();	

if(count==1) {
	Employee employee =employeeTreeset.stream().filter(e-> e.getEmployeeId()==emp.getEmployeeId()).findAny().orElse(null);
	employee.setPhoneNumber(emp.getPhoneNumber());
	employee.setEmailId(emp.getEmailId());
	employeeTreeset.add(employee);
}
	return (count==1)?true:false ;
	}

	
	

}
