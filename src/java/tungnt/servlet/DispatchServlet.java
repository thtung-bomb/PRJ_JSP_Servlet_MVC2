/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
//thay doi moi dung addnotation

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String LOG_OUT_CONTROLLER = "LogoutServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String REMOVE_CART_CONTROLLER = "RemoveCartServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "RigisterServlet";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //1. Which button did user click?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) { //welcome file trigger
                url = START_UP_CONTROLLER;
            } else if (button.equals("Login")) { //map tinh nang moi vao ben trong dieu phoi/dispatch
                url = LOGIN_CONTROLLER;
            } else if (button.equals("search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER; //b2 create new dispatch
            } else if (button.equals("Sign Out")) {
                url = LOG_OUT_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_CART_CONTROLLER;
            } else if (button.equals("Register")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Checkout")) {
                url = CHECKOUT_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
