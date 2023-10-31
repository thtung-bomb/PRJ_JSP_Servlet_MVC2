/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tungnt.util.MyApplicationConstain;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    //thay doi moi dung addnotation
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        //0. get current context
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
//        //1. Which button did user click?
//        String button = request.getParameter("btAction");
//        
//        String url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.LOGIN_PAGE);
//          
//        try {
//            if (button == null) { //welcome file trigger
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.START_UP_CONTROLLER);
//            } else if (button.equals("Login")) { //map tinh nang moi vao ben trong dieu phoi/dispatch
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.LOGIN_CONTROLLER);
//            } else if (button.equals("search")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
//            } else if (button.equals("delete")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Update")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("AddBookToCart")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER); //b2 create new dispatch
//            } else if (button.equals("Logout")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.LOG_OUT_CONTROLLER);
//            } else if (button.equals("View Your Cart")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.VIEW_CART_PAGE);
//            } else if (button.equals("Remove Selected Items")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.REMOVE_CART_CONTROLLER);
//            } else if (button.equals("Register")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.CREATE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Checkout")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.CHECKOUT_CONTROLLER);
//            } else if (button.equals("View Book")) {
//                url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.VIEW_BOOK_CONTROLLER);
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
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
