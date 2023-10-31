<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:52:11 AM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="abc"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>  
        
        <font color="red">
        Welcome, ${sessionScope.USER_INFOR.fullName}
        </font>
        <br/><br/>
        <form action="loginController" method="POST">
            <input type="submit" value="Sign Out" name="btAction" />
        </form>
        <h1>Search Page</h1>
        <form action="searchController">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <br/>
            <input type="submit" value="search" name="btAction" >
        </form><br/><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty param.txtSearchValue}">
            <!-- ket qua search dang duoc dat o dau -->
            <!--lay o request session-->
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}">
                <table border="2">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="updateAccountController" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                    ${dto.username}
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRole" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>    
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteAccountController">
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearch" value="${searchValue}" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h1>
                No record is matched !!!
            </h1>       
        </c:if>
    </c:if>
</body>
</html>
