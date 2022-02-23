package com.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;


/**
 * RestController class for Employee
 * 
 * 
 * @author sharon
 *
 */
@RestController

public class EmployeeController {
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;


    /**
     * Gets complete employee Details of a given employee Name
     * @param employeeName
     * @return set of employee details for the given employee Name
     */
    @GetMapping(value = "/employee/{employeeName}")
    public Set<Employee> getEmployeeDetailsByFirstName(
            @PathVariable String employeeName) {
    	long startTime =System.currentTimeMillis();
        LOGGER.info("getting employee with first name");
        employeeName = employeeName.toUpperCase();
        long totalTime=System.currentTimeMillis()-startTime;
        LOGGER.info("time take to get the details : "+totalTime+ "ms");
        return employeeService.getEmployee(employeeName);


    }
    /**
     * Gets List of employee Names
     * @param employeeName
     * @return list of employee Names
     */

    @GetMapping(value = "/employee")
    public List<String> getEmployeeNames(@RequestParam(value = "term") String employeeName) {
        return simulateSearchResult(employeeName);

    }
    
    /**
     *Fetches Full Employee Details
     * @param EmployeeId
     * @return Complete employee Details by employee Id
     * @throws SQLException 
     */

    @GetMapping(value = "/employee/getDetails/{employeeId}")
    public ModelAndView getCompleteEmployeeDetailsById(@PathVariable int employeeId ) throws SQLException {
        Long startTime = System.currentTimeMillis();
       Employee employee=employeeService.getCompleteEmployeeDetailsById(employeeId); 
       // m.addAttribute("emp", employee);
       ModelAndView mav = new ModelAndView("viewemp");
       mav.addObject("emp",employee);
       mav.addObject("hello", "employeeDetails");
       Long totalTime =startTime- System.currentTimeMillis();
       LOGGER.info("total time take get full details of employee "+totalTime);
    	return mav;

    }
    

    
    @RequestMapping(value="/employee/getDetails/update/{employeeId}")
    public ModelAndView edit(@PathVariable int employeeId, Model m) throws SQLException{

        Employee emp=employeeService.getCompleteEmployeeDetailsById(employeeId);    
        m.addAttribute("command",emp);
        ModelAndView mav = new ModelAndView("editemp");

        return mav;
    }    
    
    @RequestMapping(value="/employee/updateEmployee",method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("emp") Employee employee) throws SQLException{
        Long startTime = System.currentTimeMillis();
       Boolean flag=employeeService.updateEmployee(employee);
        Long totalTime =startTime- System.currentTimeMillis();
        LOGGER.info("total time take update employee "+totalTime);
        return flag? new ModelAndView("success"):new ModelAndView("error");    
    }    
    
  

    private List<String> simulateSearchResult(String employeeName) {
    	long startTime =System.currentTimeMillis();
        LOGGER.info("getting all employees");
        employeeName = employeeName.toUpperCase();
        Set<Employee> returnValue = employeeService.getEmployee(employeeName);
        List<Employee> data = new ArrayList<Employee>(returnValue);
        List<String> result = new ArrayList<>();
        for (Employee emp : data) {
            if(result.size()<10) {
                result.add(emp.firstName + " " + emp.lastName);

            }else{
                break;
            }

        }
        long stopTime =System.currentTimeMillis();
        Long totalTime=startTime-stopTime;
        LOGGER.info("time taken to complete : "+totalTime+"ms");
        return result;
    }


}
