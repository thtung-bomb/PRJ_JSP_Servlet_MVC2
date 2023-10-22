<%-- 
    Document   : register
    Created on : Oct 13, 2023, 9:14:47 AM
    Author     : Thanh Tung
--%>

<%@page import="tungnt.registration.RegistrationCreateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            Username* <input type="text" name="txtUsername" value="" /> (6-30 characters) <br/>
            Password* <input type="password" name="txtPassword" value="" /> (8-30 characters) <br/>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            Full name* <input type="text" name="txtFullName" value="" /> (2 - 30 chars) <br/>
            <input type="submit" value="Register" name="btAction" /> <br/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
