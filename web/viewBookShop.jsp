<%-- 
    Document   : viewBookShop
    Created on : Oct 22, 2023, 12:09:05 PM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Shop Page</title>
        <style>
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>
    </head>
    <body> 

        <h1>Book Shop</h1>
        <c:set var="error" value="${requestScope.CREATE_ERROR}" />
        <c:set var="productPage" value="${requestScope.PRODUCTS_PAGE}" />
        <c:if test="${not empty productPage.content}">
            <c:set var="products" value="${productPage.content}" />
            <c:set var="pageCount" value="${productPage.numberOfPages}" />
            <%-- get page information --%>
            <c:if test="${not empty param.pageNumber}" >
                <c:set var="pageNumber" value="${param.pageNumber}" />
            </c:if>
            <c:if test="${empty param.pageNumber}" >
                <c:set var="pageNumber" value="${1}" />
            </c:if>

            <%-- set size number --%>
            <c:set var="pageSize" value="${10}" />
            <c:if test="${empty param.sizeNumber}" >
                <c:set var="pageSize" value="${10}" />
            </c:if>
            <c:if test="${not empty param.sizeNumber}" >
                <c:set var="pageSize" value="${param.sizeNumber}" />
            </c:if>
            
            <c:if test="${not empty products}">
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
                        <form action="addItemToCartController">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    <input type="hidden" name="dllBook" value="${product.id}" />
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
                                    <input type="hidden" name="pageNumber" value="${pageNumber}" />
                                    <input type="submit" value="AddBookToCart" name="btAction" />
                                </td>
                            </tr>
                        </form>   
                    </c:forEach>    
                </tbody>
            </table>
            <br/>
            <form action="viewCartPage">
                <input type="submit" value="View Your Cart" name="btAction" />
            </form>
            <ul class="pagination">
                Page: 
                <c:forEach var="pageIndex" begin="1" end="${pageCount}" varStatus="counter" >
                    <c:url var="pageUrl" value="viewBookController" >
                        <c:param name="pageNumber" value="${counter.count}" />
                        <c:param name="sizeNumber" value="${pageSize}" />
                    </c:url>
                    <c:if test="${pageNumber == counter.count}" >
                        ${counter.count}
                    </c:if>
                    <c:if test="${pageNumber != counter.count}" >
                        <a href="${pageUrl}">${counter.count}</a>
                    </c:if>
                    |
                </c:forEach>
            </ul>
            
        </c:if>

    </c:if>

</body>
</html>
