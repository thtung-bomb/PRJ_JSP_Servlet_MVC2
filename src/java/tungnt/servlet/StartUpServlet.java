/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tungnt.registration.RegistrationDAO;
import tungnt.registration.RegistrationDTO;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

    private final String LOGIN__PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN__PAGE;
        
        try {
            /* TODO output your page here. You may use following sample code. */
            //2. tao servlet chuc nang
            //kiem tra servlet ton tai khong
            //1. get all cookies
            //chieu doc tu chieu client den server
            //doc o server
            //doi tuong chua la requestObject
            Cookie[] cookies = request.getCookies();
            if (cookies != null) { //check cookies has exist
                //if not change to login page
                //2. Get username and password from newest cookie
                Cookie newestCookie = cookies[cookies.length - 1];
                String username = newestCookie.getName(); //lay username (key)
                String password = newestCookie.getValue(); //get password (value)
                //3. Call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method DAO
//                boolean result = dao.checkLogin(username, password);
                    RegistrationDTO result = dao.checkLogin(username, password);
                //4. Process result
                if (result != null) {
                    url = SEARCH_PAGE;
                } //user is authenticated
            } //end cookies has existed
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            //cookie saved in file
            response.sendRedirect(url); //dang dung o controller
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
