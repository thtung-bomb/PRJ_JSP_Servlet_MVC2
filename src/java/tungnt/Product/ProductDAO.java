/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tungnt.util.DBHelper;

/**
 *
 * @author Thanh Tung
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> books;

    public List<ProductDTO> getBooks() {
        return books;
    }

    public void getAllBook()
            throws SQLException, ClassNotFoundException, NamingException { //bat loi con.close();

        Connection con = null;//khai bao
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select id, name, quantity, unitprice, status "
                        + "From Product "
                        + "Where status = 1 And quantity > 0";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. Process 
                while (rs.next()) {
                    //5.1 get data from rs 
                    //khong nen truyen theo thu tu cot (int)
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitprice = rs.getFloat("unitprice");
                    boolean status = rs.getBoolean("status");
                    //5.2 set data into DTO
                    ProductDTO dto = new ProductDTO(id, name, quantity, unitprice, status);

                    //5.3 add DTO into List
                    if (this.books == null) {
                        books = new ArrayList<>();
                    }
                    this.books.add(dto);
                } //end traverse rs to get data
            } //end connection is available
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

    public float getPrice(String productId)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;//khai bao
        PreparedStatement stm = null;
        ResultSet rs = null;
        float result = 0;

        try {

            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "Select unitprice "
                        + "From product "
                        + "Where name = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, productId);

                rs = stm.executeQuery();

                if (rs.next()) {
                    result = rs.getFloat("unitprice");
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

}
