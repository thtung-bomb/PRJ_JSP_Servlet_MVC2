/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Bill;

import java.util.List;
import tungnt.Order.OrderDTO;

/**
 *
 * @author Thanh Tung
 */
public class BillObject {
    
    private OrderDTO order;
    private List<BillDetail> productDetail;

    public BillObject() {
    }

    public BillObject(OrderDTO order, List<BillDetail> productDetail) {
        this.order = order;
        this.productDetail = productDetail;
    }

    /**
     * @return the order
     */
    public OrderDTO getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    /**
     * @return the productDetail
     */
    public List<BillDetail> getProductDetail() {
        return productDetail;
    }

    /**
     * @param productDetail the productDetail to set
     */
    public void setProductDetail(List<BillDetail> productDetail) {
        this.productDetail = productDetail;
    }
    
    
    
}
