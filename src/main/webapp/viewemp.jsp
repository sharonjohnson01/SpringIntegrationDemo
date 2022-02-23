

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>


    <link href="<c:url value="/resources/js/jquery-ui.css" />" rel="stylesheet">
    <%@ page isELIgnored="false" %>
</head>
<body>

<h1>${hello}</h1>;
<h3>${employeeDetails}</h3>

<h2>Employee Details</h2>
<p>${emp}<p>

<table id="employee_table" style ="" border="1px solid black" border-collapse="collapse">



    <tr id="thead">
        <td>${employee.employeeId}</td>
        <td>${employeeId}</td>
        <td>FIRST_NAME</td></tr>
      
        <th>LAST_NAME</th>
        <th>DATE_OF_BIRTH</th>
        <th>ADDRESS</th>
        <th>CITY</th>
        <th>STATE</th>
        <th>PHONE_NUMBER</th>
        <th>EMAILID</th>
        <th>SALARY</th>
    </tr>
    


</table>
</body>
</html>
