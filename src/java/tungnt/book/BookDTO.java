/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.book;

import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class BookDTO implements Serializable {
    
    private String id;
    private String name;
    private int quantity;
    private float unitprice;
    private boolean status;

    public BookDTO() {
    }

    public BookDTO(String id, String name, int quantity, float unitprice, boolean status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
