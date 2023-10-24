/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tungnt.registration.RegistrationCreateError;
import tungnt.registration.RegistrationDAO;
import tungnt.registration.RegistrationDTO;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "RigisterServlet", urlPatterns = {"/RigisterServlet"})
public class RigisterServlet extends HttpServlet {

    private final String USER_LENGTH_ERROR = "Username is required typing from 6 to 30 characters";
    private final String PASSWORD_LENGTH_ERROR = "Password is required typing from 8 to 30 characters";
    private final String FULLNAME_LENGTH_ERROR = "Fullname is required typing from 2 to 30 characters";
    private final String CONFIRM_PASSWORD_ERROR = "Confirm must match Password";
    private final String CREATE_ERROR_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String USERNAME_EXISTED = "is existed";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Get all params
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        String url = CREATE_ERROR_PAGE;
        try {
            //2. verify all users' error
            if (username.trim().length() < 6
                    || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthError(USER_LENGTH_ERROR); //set error
            }
            if (password.trim().length() < 8
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError(PASSWORD_LENGTH_ERROR);
            } else if (!confirm.trim().equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched(CONFIRM_PASSWORD_ERROR);
            }
            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 30) {
                foundErr = true;
                errors.setFullnameLengthError(FULLNAME_LENGTH_ERROR);
            }
            if (foundErr) { //error occur
                //cache to attribute then fw page to displayPage
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                //Neu khong co loi thi
                //3.  Call DAO
                //3.1. new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2. Call method of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createNewAccount(dto);
                //4. Process result
                if (result) {
                    url = LOGIN_PAGE;
                } //insert action is success
            } //end no error
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            //JPA
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + ex.getMessage());
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " " + USERNAME_EXISTED);
                request.setAttribute("CREATE_ERRORS", errors);
            } //username is duplicate
            //modifile error object
            //doc loi tren man hinh Log
        } catch (ClassNotFoundException ex) {
            log(ex.getMessage());
        } finally {
            ///response tra ve co mat gi khong
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
