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
import tungnt.Product.ProductDAO;
import tungnt.Product.ProductDTO;
import tungnt.util.MyApplicationConstain;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String QUANTITY_ERROR = "Quantity must not be empty";

    //b3 tao servlet chuc nang
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
        ServletContext context = request.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String urlRewriting = siteMaps.getProperty(MyApplicationConstain.ShoppingFeatures.BOOK_CONTROLLER);
        OrderCheckoutError errors = new OrderCheckoutError();
        boolean foundError = false;
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
//            ProductDTO dto = dao.getBooksById(itemId);
            if (quantityRequest != null && !"".equals(quantityRequest)) {
                int quantity = Integer.parseInt(quantityRequest);
                cart.addItemToCart(itemId, quantity);
                //items must be setAttribute
                //name copy from cart = (CartObject)
                session.setAttribute("CART", cart);
            } else {
                foundError = true;
                errors.setInvalidQuantityError(QUANTITY_ERROR);
            }
            
            if (foundError) {
                request.setAttribute("CREATE_ERROR", errors);
            }

            //4. Customer goes to shopping -> returned BookStore.html
            //chua method <-> model 
        } /*catch (SQLException | ClassNotFoundException | NamingException ex) {
            log(ex.getMessage());
        } */ finally {
            //responde tra ve mat gi do moi nghi den fw
//            response.sendRedirect(urlRewiting);

            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
