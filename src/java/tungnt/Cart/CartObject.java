/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thanh Tung
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public CartObject() {
    }

    public void addItemToCart(String itemID, int quantity) {
        //1. check item existed
        
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        if (quantity <= 0) {
            return;
        }
        
        //2. check items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        
        //3. check item existed
        if (this.items.containsKey(itemID)) {
            quantity = quantity + this.items.get(itemID);
        } //end item has already existed
        
        //4. Put item to items
        this.items.put(itemID, quantity);
        
    }
    
    public void removeItemFromCart(String itemID) {
        //phuong thuc nay bo het 
        //1. Check item existed
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        //2. check items existed
        if (this.items == null) {
            return;
        }
        //3. check item existed
        if (this.items.containsKey(itemID)) {
            this.items.remove(itemID);
            //check null ko co thi xoa 
            //check null -> false -> chac chan >= 1 items
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    
    }
    
}
