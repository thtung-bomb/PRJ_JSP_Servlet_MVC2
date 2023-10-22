/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungnt.Cart.CartObject;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    //b3 tao servlet chuc nang
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            //1. Cus -> cart place
            HttpSession session = request.getSession(); //check kiem tra
            //luon luon co thi phai true -> default
            //2. Cus take cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            } //cart has init

            //3. cus drop item to cart
            String itemId = request.getParameter("dllBook");
            String quantityRequest = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(quantityRequest);
            cart.addItemToCart(itemId, quantity);
            
            //cart.addItemToCart(itemId, 1); //form tinh~
            //items must be setAttribute
            //name copy from cart = (CartObject)
            session.setAttribute("CART", cart);
            
            //4. Customer goes to shopping -> returned BookStore.html
            //chua method <-> model 
        } finally {
            //responde tra ve mat gi do moi nghi den fw
            String urlRewiting = "DispatchServlet"
                    + "?btAction=View Book";
            response.sendRedirect(urlRewiting);

//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
