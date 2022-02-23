

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>


    <link href="<c:url value="/resources/js/jquery-ui.css" />" rel="stylesheet">
    <%@ page isELIgnored="false" %>
</head>
<body>
<h2>Employee Details</h2>





<table id="employee_table" align="left">



    <tr>
        <th width="30%">Employee Id</th>
        <td>${employeeId}</td>
    </tr>
    <tr>
        <th width="30%">First Name</th>
        <td>${emp.firstName}</td>
    </tr>
<tr>
    <th  width="30%">Last Name</th>
    <td>${emp.lastName}</td>
</tr>
    <tr>
    <th width="30%">Date Of Birth</th>
        <td>${emp.dateOfBirth}</td>
    </tr>
        <tr>
            <th width="30%">Email Id</th>
            <td>${emp.emailId}</td>
            <td><a href="update/${emp.employeeId}">Edit</a></td>
        </tr>
        <tr>
            <th width="30%">Phone Number</th>
            <td>${emp.phoneNumber}</td>
        </tr>
        <tr>
            <th width ="30%">Employment Type</th>
            <td>${emp.employmentType}</td>
        </tr>
        <tr>
            <th width ="30%">Emergency Contact Number</th>
            <td>${emp.emergencyContactNumber}

        <tr>
            <th>Department</th>
            <td>${emp.department}</td>
        </tr>
        <tr>
            <th>Salary</th>
            <td>${emp.salary}</td>
        </tr>
    <tr></tr>
    <tr>
        <td>
            <table align ="centre">
                <tr>
                    <h4>Home Address</h4>
                </tr>
                <tr>
                    <th width="30%">City</th>
                    <td>${emp.address.city}</td>
                </tr>
                <tr>
                    <th width ="30%">Street Name</th>
                    <td>${emp.address.streetName}</td>
                </tr>
                <tr>
                    <th width ="30">State</th>
                    <td>${emp.address.state}</td>
                </tr>

            </table>

        </td>



    </tr>



</table>
</body>
</html>
