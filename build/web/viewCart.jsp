<%-- 
    Document   : viewCart
    Created on : Oct 16, 2023, 11:42:40 PM
    Author     : Thanh Tung
--%>

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
        <%
            //1. Cust goes to cart place
            //session scope
            if (session != null) {
                //check co ton tai khong
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART"); //cart.setAttribute
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems(); //ngan chua do 
                    if (items != null) {
        %>
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
                            <input type="checkbox" name="chkItem" value="<%= key%>" />
                        </td>
                    </tr>
                    <%
                        } //end get key value of each items
                    %>
                    <tr>
                        <td colspan="3"> 
                            <a href="viewBookShopServlet">Add more book to your cart</a>
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
        <%
                        return;
                    } //end items have existed
                } //cart is existed
            } //cart place must be existed
        %>
        <h2>No cart is existed</h2>
        <a href="DispatchServlet?btAction=View Book">Back to shopping</a>
    </body>
</html>
