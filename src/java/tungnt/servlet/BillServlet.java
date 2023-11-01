/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import tungnt.Bill.BillObject;
import tungnt.Order.OrderBLO;
import tungnt.Order.OrderDAO;
import tungnt.OrderDetail.OrderDetailDAO;
import tungnt.Product.ProductDAO;
import tungnt.util.MyApplicationConstain;

/**
 *
 * @author Thanh Tung
 */
@WebServlet(name = "BillServlet", urlPatterns = {"/BillServlet"})
public class BillServlet extends HttpServlet {

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

        ServletContext context = request.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderBLO blo = new OrderBLO(productDAO, orderDao, orderDetailDAO);

        String orderId = request.getParameter("orderId");
        String url = siteMaps.getProperty(MyApplicationConstain.CheckoutFeature.BILL);
        try {
            BillObject bill = blo.createBill(orderId);
//            bill.getProductDetail()
//            bill.getOrder()
            if (bill != null) {
                request.setAttribute("BILL", bill);
            }

        } catch (SQLException ex) {
            log(ex.getMessage());
        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log(ex.getMessage());
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
