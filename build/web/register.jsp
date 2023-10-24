<%-- 
    Document   : register
    Created on : Oct 13, 2023, 9:14:47 AM
    Author     : Thanh Tung
--%>

<%@page import="tungnt.registration.RegistrationCreateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <a href="login.html">Back to Home Page</a>
        <h1>Create Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6-30 characters) <br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font> <br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (8-30 characters) <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullName}" /> (2 - 30 chars) <br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Register" name="btAction" /> <br/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
