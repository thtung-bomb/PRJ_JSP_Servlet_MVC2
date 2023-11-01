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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungnt.Cart.CartObject;
import tungnt.Order.OrderCheckoutError;
import tungnt.OrderDetail.OrderDetailDAO;
import tungnt.Product.ProductDAO;
import tungnt.util.MyApplicationConstain;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstain.DispatchFeature.VIEW_CART_PAGE);

        ProductDAO dao = new ProductDAO();
        OrderCheckoutError errors = new OrderCheckoutError();
//        float unitprice = 0;
//        String url = siteMaps.getProperty(MyApplicationConstain.CheckoutFeature.CHECKOUT_PAGE);
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        boolean foundError = false;
        try {
            if (name.length() < 2 || name.length() > 30) {
                foundError = true;
                errors.setNameLengError("Name is require typing from 2 to 30 characters");
            }   
            if (address.length() < 5 || address.length() > 250) {
                foundError = true;
                errors.setAddressLengthError("Address is require typing from 5 to 50 characters");
            }
            
            if (foundError) {
                request.setAttribute("CHECKOUT_ERROR", errors);
//                errors.getInvalidQuantityError()
            } else {
                //get orderId
                HttpSession session = request.getSession();
                //get cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart == null) {
                    return;
                }
                String orderId = orderDetailDAO.checkout(cart, name, address, errors);
                
                if (orderId != null) {
                    session.setAttribute("CART", null);
                    request.setAttribute("NAME", name);
                    request.setAttribute("ADDRESS", address);
                    url = siteMaps.getProperty(MyApplicationConstain.CheckoutFeature.BILL_CONTROLLER)
                            + "?orderId=" + orderId;
                }
            }
        } catch (NamingException ex) {
            log("AddItemToCartServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("AddItemToCartServlet_SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("AddItemToCartServlet_ClassNotFound: " + ex.getMessage());
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
