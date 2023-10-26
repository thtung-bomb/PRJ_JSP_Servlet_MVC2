<%-- 
    Document   : viewCart
    Created on : Oct 16, 2023, 11:42:40 PM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="tungnt.Cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <a href="login.html">Back to Home Page</a>
        <h1>Book Store</h1>

        <c:set var="cart" value="${sessionScope.CART.items}" />
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart}" var="cart" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${cart.key}
                            </td>
                            <td>
                                ${cart.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${cart.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"> 
                            <a href="DispatchServlet?btAction=View Book">Add more book to your cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>        
        </form>
        
        <form action="DispatchServlet" method="POST">
            Name* <input type="text" name="txtName" value="" /><br/>
            Address* <textarea name="txtAddress" value="" 
                               rows="5" cols="20"
                               style="overflow-y: scroll; resize: none"></textarea><br/>
            <input type="submit" value="Checkout" name="btAction" />
        </form>
        
        <a href="DispatchServlet?btAction=View Book">Back to shopping</a>
    </body>
</html>
