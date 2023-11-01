package tungnt.Order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import javax.naming.NamingException;
import tungnt.util.DBHelper;
import tungnt.util.StringHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thanh Tung
 */
public class OrderDAO implements Serializable {

    public OrderDTO createOrder(Connection con)
            throws ClassNotFoundException, SQLException, NamingException {
        con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO result = null;
        try {
            con = DBHelper.createConnection();

            String sql = "Insert Into [Order] "
                    + "(id, orderDate, total) "
                    + "Values(?, ?, 0)";

            String id;
            do {
                //Create orderId
                id = StringHelper.randomOrderId();
            } while (checkExistId(id));

            Timestamp now = Timestamp.from(Instant.now());
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setTimestamp(2, now);

            int affectRows = stm.executeUpdate();

            if (affectRows > 0) {
                result = new OrderDTO(id, now, 0);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    public boolean updateOrderTotalPrice(String orderId, float total)
            throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            if (con != null) {

                String sql = "Update [Order] "
                        + "Set total = ? "
                        + "Where id = ?";

                stm = con.prepareStatement(sql);
                stm.setFloat(1, total);
                stm.setString(2, orderId);

                int affectedRows = stm.executeUpdate();

                if (affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

    private boolean checkExistId(String id)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.createConnection();
            if (con != null) {

                String sql = "SELECT id "
                        + "From [Order] "
                        + "Where id=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, id);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
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

        return false;
    }

    public OrderDTO getOrderById(String orderId)
            throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {

            con = DBHelper.createConnection();
            
            if (con != null) {
                
                String sql = "Select id, [orderDate], total "
                        + "From [Order] "
                        + "Where id = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                
                rs = stm.executeQuery();
                
                if (rs.next()) {
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    float total = rs.getFloat("total");
                    OrderDTO dto = new OrderDTO(orderId, orderDate, total);
                    return dto;
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

        return null;

    }

}
