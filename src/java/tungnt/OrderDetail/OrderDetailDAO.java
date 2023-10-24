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
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tungnt.Cart.CartObject;
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

    public boolean createDetail(Connection con, String orderId, String id, int cartQuantity, float unitprice)
            throws SQLException, ClassNotFoundException, NamingException {
        con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            String sql = "Insert into [OrderDetail] "
                    + "(id, productId, quantity, price, total, orderId) "
                    + "Values (?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, id); //id
            stm.setString(2, "h"); //productId
            stm.setInt(3, cartQuantity); //CartQuantity
            stm.setFloat(4, unitprice); //price
            stm.setFloat(5, 0); //total
            stm.setString(6, orderId); //orderId

            int effectRows = 0;

            if (effectRows > 0) {
                result = true;
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

    public String checkout(CartObject cart, String name, String address)
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
                OrderDTO order = orderDAO.createDetail(con); //--??????--- 
                //get in in OrderDAO
                String orderId = order.getId();

//                productDAO.loadProductsOfSkus(con, items.keySet());
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
                        con.rollback();
                        return null;
                    }
                    //create detail 
                    float productTotal = product.getUnitprice() * cartQuantity;
//                    boolean detailCreated = detailDAO.createDetail(orderId, product.getId(), cartQuantity, product.getUnitprice());
                    boolean updateResult = productDAO.updateQuantity(con, product.getId(), productQuantity - cartQuantity);
                    //if cant create then rollback
                    if (/* !detailCreated ||*/ !updateResult) {
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

}
