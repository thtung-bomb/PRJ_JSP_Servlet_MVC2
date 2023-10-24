/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

//    private final String RESULT_SEARCH_PAGE = "search.jsp";
//    private final String ERROR_PAGE = "errorpage";
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
        //0. 
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1. get all parameters
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(MyApplicationConstain.ErrorsPage.ERROR_PAGE);
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                RegistrationDTO userInfo = (RegistrationDTO) session.getAttribute("USER_INFOR");
                if (userInfo != null && !searchValue.trim().isEmpty()) { //check xem chuoi rong hay khong
                    //2. Call DAO
                    //2.1 new DAO
                    RegistrationDAO registrationDAO = new RegistrationDAO();
                    //2.2 call method
                    registrationDAO.searchLastname(searchValue);
                    //3. process
                    List<RegistrationDTO> searchResult = registrationDAO.getAccounts(); //dang o controller -> view
                    //send result -> url
                    //use requestScope
                    url = siteMaps.getProperty(MyApplicationConstain.SearchLastnameFeaturee.RESULT_SEARCH_PAGE);
                    request.setAttribute("SEARCH_RESULT", searchResult);
                } //end user typed valid value 
            } else {
                url = siteMaps.getProperty(MyApplicationConstain.ErrorsPage.ERROR_PAGE);
            }
        } //end username and password are verified
        catch (SQLException ex) {
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
