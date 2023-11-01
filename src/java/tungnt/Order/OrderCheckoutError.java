/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Order;

/**
 *
 * @author Thanh Tung
 */
public class OrderCheckoutError {
    
    private String nameLengError;
    private String addressLengthError;
    private String invalidQuantityError;
    
    public OrderCheckoutError() {
    }
    
    /**
     * @return the nameLengError
     */
    public String getNameLengError() {
        return nameLengError;
    }
    
    /**
     * @param nameLengError the nameLengError to set
     */
    public void setNameLengError(String nameLengError) {
        this.nameLengError = nameLengError;
    }
    
    /**
     * @return the addressLengthError
     */
    public String getAddressLengthError() {
        return addressLengthError;
    }
    
    /**
     * @param addressLengthError the addressLengthError to set
     */
    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }
    
    /**
     * @return the invalidQuantityError
     */
    public String getInvalidQuantityError() {
        return invalidQuantityError;
    }
    
    /**
     * @param invalidQuantityError the invalidQuantityError to set
     */
    public void setInvalidQuantityError(String invalidQuantityError) {
        this.invalidQuantityError = invalidQuantityError;
    }    
}
