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
        <a href="login.html">Back to Home Page</a>
        <h1>Checkout</h1>
        <div>
            <h2>${requestScope.NAME}</h2>
            <h2>${requestScope.ADDRESS}</h2>
        </div>
        <%
            CartObject cart = (CartObject) session.getAttribute("CART"); //cart.setAttribute
            if (cart != null) {
                Map<String, Integer> items = cart.getItems(); //ngan chua do 
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <%
                    } //end get key value of each items
                %>
                <% session.invalidate();
                } else {
                %>
            <h1>No items to checkout</h1>
            <a href="DispatchServlet?btAction=View Book">Back to shopping</a>
            <%
                }
            %>
        </tbody>
    </table>
</body>
<%--   <%
        CartObject cart = (CartObject) session.getAttribute("CART"); //cart.setAttribute
        if (cart != null) {
            Map<String, Integer> items = cart.getItems(); //ngan chua do 
   %>
    <table border="1">
       <thead>
           <tr>
               <th>No.</th>
               <th>Name</th>
               <th>Quantity</th>
           </tr>
       </thead>
       <tbody>
           <%
               int count = 0;
               for (String key : items.keySet()) {
           %>
           <tr>
               <td>
                   <%= ++count%>
               </td>
               <td>
                   <%= key%>
               </td>
               <td>
                   <%= items.get(key)%>
               </td>
           </tr>
           <%
               } //end get key value of each items
           %>
           <% session.invalidate();
           } else {
           %>
       <h1>No items to checkout</h1>
       <a href="DispatchServlet?btAction=View Book">Back to shopping</a>
       <%
           }
       %>
   </tbody>
</table>        
--%>



<%--        <c:set var="items" value="${sessionScope.CART.items}"/>
    <c:if test="${not empty items}">

            <table border="1">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${items}" var="item" varStatus="counter">

                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
<%--
${item.itemId}

</td>
<td>
<%-- 
${item.quantity}

</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
<c:if test="${empty items}">
<h1>NO ITEM IN CART !!!</h1>
<a href="DispatchServlet?btAction=View Book">Back to Shopping</a>
</c:if>
--%>
</html>
