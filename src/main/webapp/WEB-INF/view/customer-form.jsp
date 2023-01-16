<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FS23
  Date: 14.01.2023
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save the Customer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>
<div id="container">
    <h3>Save the Customer</h3>
    <form:form action="saveCustomer" modelAttribute="customer" method="post">

        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>First Name</label></td>
                <td><form:input path="firstName"></form:input></td>
            </tr>

            <tr>
                <td><label>Last Name</label></td>
                <td><form:input path="lastName"></form:input></td>
            </tr>

            <tr>
                <td><label>Email</label></td>
                <td><form:input path="email"></form:input></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"></td>

            </tr>

            </tbody>

        </table>

    </form:form>

    <div style="clear: both;">

        <p>
            <a href="${pageContext.request.contextPath}/">Back to List</a>
        </p>
    </div>

</div>

</body>
</html>
