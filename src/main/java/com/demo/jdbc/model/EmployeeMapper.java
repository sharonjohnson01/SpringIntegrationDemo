package com.demo.jdbc.model;

import com.demo.model.Address;
import com.demo.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultset, int rowNum) throws SQLException {
            Employee employee = new Employee();

            employee.setEmployeeId(resultset.getInt("EMPLOYEE_ID"));
            employee.setFirstName(resultset.getString("First_Name"));
            employee.setLastName(resultset.getString("Last_Name"));
            employee.setPhoneNumber(resultset.getString("PHONE_NUMBER"));
            employee.setEmailId(resultset.getString("EMAILID"));
            employee.setSalary(resultset.getInt("SALARY"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

            employee.setDateOfBirth(sdf.format(resultset.getDate("Date_Of_Birth")));

            Address address = new Address();
            address.setCity(resultset.getString("CITY"));
            address.setState(resultset.getString("STATE"));
            address.setStreetName(resultset.getString("ADDRESS"));
    employee.setAddress(address);
        return employee;
    }

}
