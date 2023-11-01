/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.NamingException;
import tungnt.Bill.BillDetail;
import tungnt.Bill.BillObject;
import tungnt.OrderDetail.OrderDetailDAO;
import tungnt.OrderDetail.OrderDetailDTO;
import tungnt.Product.ProductDAO;
import tungnt.Product.ProductDTO;
import tungnt.servlet.BillServlet;
import tungnt.util.DBHelper;

/**
 *
 * @author Thanh Tung
 */
public class OrderBLO {

    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;

    public OrderBLO(ProductDAO productDAO, OrderDAO orderDAO, OrderDetailDAO orderDetailDAO) {
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
        this.orderDetailDAO = orderDetailDAO;
    }

    public BillObject createBill(String orderId)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        BillObject result = null;

        try {
            con = DBHelper.createConnection();

            if (con != null) {
                OrderDTO order = orderDAO.getOrderById(orderId);

                if (order == null) {
                    return null;
                }

                orderDetailDAO.loadOrderDetailsByOrderId(orderId);
                List<OrderDetailDTO> orderDetail = orderDetailDAO.getOrderDetails();

                if (orderDetail == null) {
                    return null;
                }

                Set<String> keySet = new HashSet<>();
                orderDetail.forEach((dto) -> {
                    keySet.add(dto.getProductId());
                });

                productDAO.getProductOfId(keySet);
                List<ProductDTO> products = productDAO.getBooks();

                if (products == null) {
                    return null;
                }
                
                List<BillDetail> billBooks = new ArrayList<>();
                products.forEach((product) -> {
                    orderDetail.stream().filter((detail) -> (product.getId().equals(detail.getProductId())))
                            .forEachOrdered((detail) -> {
                                BillDetail bD = new BillDetail(detail.getId(), detail.getProductId(), product.getName(),
                                        detail.getQuantity(), detail.getPrice(), detail.getTotal());
                                billBooks.add(bD);
                            });
                });

                result = new BillObject(order, billBooks);

                return result;
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

}
