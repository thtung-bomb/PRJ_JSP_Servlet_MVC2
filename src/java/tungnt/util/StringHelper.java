/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.util;

import java.util.Random;

/**
 *
 * @author Thanh Tung
 */
public class StringHelper {
    
    public static String randomOrderId() {
        String prefix = "O";
        StringBuilder orderID = new StringBuilder(prefix);
        Random random = new Random();
        
        // Generate three random characters
        for (int i = 0; i < 4; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Uppercase letters
            orderID.append(randomChar);
        }
        
        return orderID.toString();
    }

}
