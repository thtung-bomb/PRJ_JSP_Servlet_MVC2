/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.OrderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tungnt.Cart.CartObject;
import tungnt.Order.OrderCheckoutError;
import tungnt.Order.OrderDAO;
import tungnt.Order.OrderDTO;
import tungnt.Product.ProductDAO;
import tungnt.Product.ProductDTO;
import tungnt.util.DBHelper;

/**
 *
 * @author Thanh Tung
 */
public class OrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String orderId, String productId, int cartQuantity, float unitprice, float productTotal)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "Insert into [OrderDetail] "
                        + "(productId, quantity, price, total, orderId) "
                        + "Values (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, productId); //productId
                stm.setInt(2, cartQuantity); //CartQuantity
                stm.setFloat(3, unitprice); //price
                stm.setFloat(4, productTotal); //total
                stm.setString(5, orderId); //orderId

                int effectRows = stm.executeUpdate();

                if (effectRows > 0) {
                    result = true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public String checkout(CartObject cart, String name, String address, OrderCheckoutError erros)
            throws NamingException, SQLException, ClassNotFoundException {
        boolean validate = true;
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDetailDAO detailDAO = new OrderDetailDAO();
        Map<String, Integer> items = cart.getItems();

        Connection con = null;

        try {
            con = DBHelper.createConnection();
            if (con != null) {
                con.setAutoCommit(false);
                OrderDTO order = orderDAO.createOrder(con);
                //get id of Order
                String orderId = order.getId();

                productDAO.getProductOfId(items.keySet());
                List<ProductDTO> products = productDAO.getBooks();

                float total = 0;
                //create detail for each item in cart
                for (ProductDTO product : products) {
                    //get DB for product quantity
                    int productQuantity = product.getQuantity();
                    //get quantity for product in cart
                    String productId = product.getId();
                    int cartQuantity = items.get(productId);
                    //check if product has no sufficient quantity
                    if (productQuantity < cartQuantity) {
                        erros.setInvalidQuantityError("Product name: " + product.getName() + " does not have enough quantity");
                        con.rollback();
                        return null;
                    }
                    //create detail 
                    float productTotal = product.getUnitprice() * cartQuantity;
                    boolean detailCreated = this.createOrderDetail(orderId, product.getId(), cartQuantity, product.getUnitprice(), productTotal);
                    boolean updateResult = productDAO.updateQuantity(con, product.getId(), productQuantity - cartQuantity);
                    //if cant create then rollback
                    if (!detailCreated || !updateResult) {
                        con.rollback();
                        return null;
                    }
                    total = total + productTotal;
                }

                boolean result = orderDAO.updateOrderTotalPrice(orderId, total);

                if (result) {
                    con.commit();
                    return orderId;
                } else {
                    con.rollback();
                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
                return null;
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return null;
    }

    private List<OrderDetailDTO> orderDetails;

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void loadOrderDetailsByOrderId(String orderId)
            throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "Select id, productId, quantity, price, total "
                        + "From [OrderDetail] "
                        + "Where orderId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String productId = rs.getString("productId");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    float total = rs.getFloat("total");

                    OrderDetailDTO dto = new OrderDetailDTO(id, productId, quantity, price, total, orderId);
                    if (orderDetails == null) {
                        orderDetails = new ArrayList<>();
                    }
                    orderDetails.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
