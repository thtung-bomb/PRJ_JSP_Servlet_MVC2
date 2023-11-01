<%-- 
    Document   : bill
    Created on : Nov 1, 2023, 11:26:59 AM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
    </head>

    <body>
        <a href="loginPage">Back to home page</a>
        <c:set var="bill" value="${requestScope.BILL}" />
        <c:if test="${not empty bill}">
            <c:set var="order" value="${bill.order}"/>
            <c:set var="details" value="${bill.productDetail}" />
            <h1>Bill ID: ${order.id}</h1>
            <h2>Customer name: ${requestScope.NAME}</h2>
            <h2>Customer address: ${requestScope.ADDRESS}</h2>
            <h2>Date: ${order.orderDate}</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${details}" varStatus="counter">
                        <tr style="text-align: right">
                            <td>${counter.count}</td>
                            <td>${detail.name}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.price}</td>
                            <td>${detail.quantity * detail.price}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="text-align: right">
                            Total Bill: ${order.total}
                        </td>
                    </tr>   
                </tbody>

            </table>
        </c:if>
        <c:if test="${empty bill}">
            <h2>
                This order does not exist!
            </h2>
        </c:if>
        <a href="viewBookController">Continue shopping</a>
    </body>

</html>
