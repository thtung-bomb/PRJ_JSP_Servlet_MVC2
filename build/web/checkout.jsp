<%-- 
    Document   : checkout
    Created on : Oct 17, 2023, 12:07:38 AM
    Author     : Thanh Tung
--%>

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

        <%
            String name = (String) request.getAttribute("NAME");
            String address = (String) request.getAttribute("ADDRESS");
            CartObject cart = (CartObject) session.getAttribute("cart"); //cart.setAttribute
            if (cart != null) {
                Map<String, Integer> items = cart.getItems(); //ngan chua do 
        %>
        <div>
            <%= name%>
        </div>
        <div> 
            <%= address%>
        </div>

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
            <a href="BookStore.jsp">Back to shopping</a>
            <%
                }
            %>
        </tbody>
    </table>        

</body>
</html>
