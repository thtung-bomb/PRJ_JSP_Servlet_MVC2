<%-- 
    Document   : checkout
    Created on : Oct 17, 2023, 12:07:38 AM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="tungnt.Cart.CartObject"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <a href="loginPage">Back to Home Page</a>
        <h1>Checkout</h1>
        <div>
            <h2>${requestScope.NAME}</h2>
            <h2>${requestScope.ADDRESS}</h2>
        </div>

        <c:set var="items" value="${sessionScope.CART.items}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>NO.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="display" varStatus="count">
                    <tr>
                        <td>${count.count}</td>
                        <td>${display.key}</td>
                        <td>${display.value}</td>
                        <td>${display}</td>
                        <td>${display}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="viewBookController">Back to shopping</a>

    </body>
</html>
