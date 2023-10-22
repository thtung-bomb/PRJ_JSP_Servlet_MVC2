<%-- 
    Document   : viewBookShop
    Created on : Oct 22, 2023, 12:09:05 PM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Shop Page</title>
    </head>
    <body> 

        <h1>Book Shop</h1>

        <c:set var="products" value="${requestScope.PRODUCT}" />
        <c:if test="${not empty products}">
            <form action="DispatchServlet">
                <input type="submit" value="View Your Cart" name="btAction" />
            </form>
            <table border="1">

                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${products}" var="product" varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                <input type="hidden" name="dllBook" value="${product.name}" />
                                ${product.name}
                            </td>
                            <td>    
                                ${product.unitprice}
                            </td>
                            <td>
                                <input style="text-align:right; width: 70px;" 
                                       type="number" name="txtQuantity" 
                                       min="0" max="${product.quantity}" 
                                       value="0"/>
                            </td>
                            <td>
                                <input type="submit" value="AddBookToCart" name="btAction" />
                            </td>
                        </tr>
                    </form>   
                </c:forEach>    
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty products}">
        <H1>NO RECORDS TO BE DISPLAYED !!!</H1>
        <a href="login.html">Back to Home page</a>
    </c:if>

</body>
</html>
