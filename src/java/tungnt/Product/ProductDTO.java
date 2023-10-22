/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Product;

import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class ProductDTO implements Serializable {

    private String id;
    private String name;
    private int quantity;
    private float unitprice;
    private boolean status;

    public ProductDTO() {
    }
    
    public ProductDTO(String id, String name, int quantity, float unitprice, boolean status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitprice
     */
    public float getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
