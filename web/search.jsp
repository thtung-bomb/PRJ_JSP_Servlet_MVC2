<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:52:11 AM
    Author     : Thanh Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page import="java.util.List"%>--%>
<%--<%@page import="tungnt.registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="abc"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        Welcome, 
        <font color="red">
        ${sessionScope.USER_INFOR.fullName}
        </font>
        <br/><br/>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <h1>Search Page</h1>
        <form action="DispatchServlet" method="GET">
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
                        <form action="DispatchServlet" method="POST">
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
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="delete" />
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
<%--<%
    Cookie[] cookies = request.getCookies(); //check cookie co ton tai neu co ton tai thi moi qua day

            if (cookies != null) {
                Cookie newestCookie = cookies[cookies.length - 1];
                String username = newestCookie.getName();
        %>
        <font> 
        <!--end read cookie-->
        Welcome, <%= username%>  
        <font/>
        <%
            } //end cookies have existed
        %>
        <a href="login.html">Back to Home Page</a>
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="Sign Out" name="btAction" />
        </form>
        <h1>Search Page</h1>
        <form action="DispatchServlet" method="GET">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>" />
            <br/>
            <input type="submit" value="search" name="btAction" />
        </form><br/><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            /* neu dung duong dan url -> null */
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

                //render (VIEW)
                if (result != null) { //has one or more records
                    //NEU NHIEU RECORD IN TABLE
                    //JAVA CODE -> SCRIPTELEMENT
        %> 
        <!-- tach code -->

        <table border="1">
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
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <!-- ++count dem truoc roi moi them data sau -->
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <!-- khong co hien giao dien xoa -->
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkRole" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   } //user is admin
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearch" 
                               value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>
            </form>
            <%
                } //end traverse DTO
            %>
        </tbody>
    </table>

    <%
    } else { //no record
    %>
    <h2>
        No record is matched...
    </h2>
    <%
            }
            //scope ko chuyen data, attribute chuyen
        } //end searchValue did not trigger from previous form 
        //result of search in attribute requestscope
        //datatype: (List<RegistrationDTO>)

        // ep kieu du lieu: (List<RegistrationDTO>)
    %> --%>

</html>
