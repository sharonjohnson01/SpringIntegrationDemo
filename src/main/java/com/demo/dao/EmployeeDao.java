package com.demo.dao;

import java.sql.SQLException;
import java.util.Set;

import com.demo.model.Employee;

public interface EmployeeDao {
Set<Employee> getAllEmployeeName(String firstName);
Employee getEmployeeDetails(int employeId) throws SQLException;
Boolean updateEmployee(Employee emp) throws SQLException;

}