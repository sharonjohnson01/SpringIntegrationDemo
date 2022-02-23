<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <link href="<c:url value="/resources/js/jquery-ui.css" />" rel="stylesheet">
</head>
<body>
<h2>Employee Directory</h2>
<p1>Search employee here</p1>
<a id="empty-message"></a>

<div>
    <input type="text"  id="w-input-search" value="">
<%--    <span>--%>
<%--	  <button id="button-id" type="button">Search</button>--%>
<%--	</span>--%>
</div>
<table id="employee_table" style ="" border="1px solid black" border-collapse="collapse">

    <tr id="thead">
        <th>EMPLOYEE_ID</th>
        <th>FIRST_NAME</th>
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

<script>
    $(document).ready(function() {
        $('#employee_table').hide();
    });
</script>

<script>
    $(document).ready(function() {
        $('#w-input-search').autocomplete({
            source: "employee",
            minLength: 3,
            response: function(data) {
                // ui.content is the array that's about to be sent to the response callback.
                if (data.content.length === 0) {
                    $("#empty-message").text("No results found");
                } else {
                    $("#empty-message").empty();
                }
            }
        });
    });

</script>
<script>
    $(document).ready(function () {

        $('#w-input-search').on('autocompleteselect', function (e, ui) {

            var url = "/employee/" + ui.item.value + "";
            $.getJSON(url).done(function (data) {
                $("#employee_table td").remove();
              //  console.log(data)
                $.each(data, function (i, item) {
                  // console.log(item.firstName)
                 //   var id=$(item.employeeId).text();
                    var hrefUrl="/employee/getDetails/"+item.employeeId;
                   // console.log(i,id);
                        data += '<tr>';
                        data += '<td><a href="/employee/getDetails/'+item.employeeId+'">' + item.employeeId + '</a></td>';
                         data += '<td>' + item.firstName + '</td>';
                         data += '<td>' + item.lastName + '</td>';
                          data += '<td>' + item.dateOfBirth + '</td>';
                      data += '<td>' + item.address.streetName + '</td>';
                    data += '<td>' + item.address.city + '</td>';
                    data += '<td>' + item.address.state + '</td>';
                    data += '<td>' + item.phoneNumber + '</td>';
                    data += '<td>' + item.emailId + '</td>';
                    data += '<td>' + item.salary + '</td>';


                        data += '</tr>';
                    });
                    $('#employee_table').append(data).show();
                });
            //$('#employee_table').toggle();
            });
    });

</script>


</body>
<div class="ui-widget">

    <div id="tagsname"></div>
</div>
</html>