package com.demo.helper;

import java.util.Comparator;

import com.demo.model.Employee;

public class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee obj1, Employee obj2) {
		// TODO Auto-generated method stub
		int r = obj1.firstName.compareTo(obj2.firstName);
		return r == 0 ? Integer.compare(obj1.getEmployeeId(),obj2.getEmployeeId()) : r;

	}

}
