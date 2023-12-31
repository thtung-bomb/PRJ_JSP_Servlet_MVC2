/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungnt.registration.RegistrationDAO;
import tungnt.registration.RegistrationDTO;
import tungnt.util.MyApplicationConstain;

/**
 *
 * @author Thanh Tung
 */
public class LoginServlet extends HttpServlet {
    
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
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = siteMaps.getProperty(MyApplicationConstain.LoginFeature.LOGIN_PAGE);
        
        try {
            //ghi o phia client                 
            //2. call DAO
            //2.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call 
//            boolean resutlt = dao.checkLogin(username, password);
            RegistrationDTO resutlt = dao.checkLogin(username, password);
            //3. process result
            if (resutlt != null) {
//                url = SEARCH_PAGE;
//                url = siteMaps.getProperty(SEARCH_PAGE);
                url = siteMaps.getProperty(MyApplicationConstain.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession(); //login thanh cong chac chan phai luu lai
                //luu lai thi bang true             
                session.setAttribute("USER_INFOR", resutlt);
            } //end username and password are verified
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.getStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * if Handles the HTTP <code>GET</code> method.
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
