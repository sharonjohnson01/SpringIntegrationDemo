package com.demo.service;

import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeDao;
import com.demo.model.Employee;

@Service
public class EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeService.class);
    @Autowired
    EmployeeDao employeeDao;



    
    
    public Set<Employee> getEmployee(String firstName) {
        LOGGER.info("In employee service class");      
        Set<Employee> empSet = employeeDao.getAllEmployeeName(firstName);
        return empSet;

    }

    public Employee getCompleteEmployeeDetailsById(int employeeId) throws SQLException {
        return employeeDao.getEmployeeDetails(employeeId);

    }
    
    public Boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDao.updateEmployee(employee);

    }

}
