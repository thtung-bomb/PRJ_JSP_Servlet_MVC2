<%-- 
    Document   : BookStore
    Created on : Oct 12, 2023, 3:55:37 PM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <a href="login.html">Back to Home Page</a>
        <div>Book Store</div>
        <form action="DispatchServlet">
            <c:set var="listBook" value="${requestScope.BOOK_RESULT}"/>
            Choose Your Book <select name="ddlBook">
                <c:forEach var="bookDTO" items="listBook" >
                    <option>${bookDTO.name}</option>
                </c:forEach>    
            </select> <br/>
            <input type="text" name="bookQuantity" value="" /><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>    
    </body>
    
    <!--               
    <option>Java</option>
    <option>C#</option>
    <option>C++</option>
    <option>C</option>
    <option>JS</option>-->

</html>
