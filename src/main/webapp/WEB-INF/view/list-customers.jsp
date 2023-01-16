<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hibernateprojectmvc.Util.SortUtils" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customers</title>
    <link type="text/css"

          rel="stylesheet"

          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <!-- put new button : Add new Customer -->
        <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd';  return false;"
               class="add-button">

        <form:form action="searchCustomer" method="get">

            Search Customer: <input type="text" name="searchName">
            <input type="submit" value="Search"
                   class="add-button">

        </form:form>

        <!-- construct a sort link for first name -
                <c:url var="sortLinkFirstName" value="/">

                    <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"/>

                </c:url>

                <-- construct a sort link for last name -->
        <c:url var="sortLinkLastName" value="/">

            <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"/>

        </c:url>

        <!-- construct a sort link for email -->
        <c:url var="sortLinkEmail" value="/">

            <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"/>

        </c:url>

        <table>
            <tr>
                <th><a href="${sortLinkFirstName}">First Name</a></th>
                <th><a href="${sortLinkLastName}">Last Name</a></th>
                <th><a href="${sortLinkEmail}">Email</a></th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempCustomer" items="${customers}">

                <c:url var="updateLink" value="/showFormForUpdate">

                    <c:param name="customerID" value="${tempCustomer.id}"/>

                </c:url>

                <c:url var="deleteLink" value="/showFormForDelete">

                    <c:param name="customerID" value="${tempCustomer.id}"/>

                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update | </a> <a href="${deleteLink}"
                                                                 onclick="if (!(confirm('Are you sure you want to delete this customer ?'))) return false; ">Delete</a>
                    </td>
                </tr>

            </c:forEach>
            </tr>

        </table>

    </div>
</div>


</body>
</html>