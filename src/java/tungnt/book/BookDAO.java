/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.book;

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
public class BookDAO {
    
    private List<BookDTO> books;
    
    public List<BookDTO> getBooks() {
        return books;
    }
    
    public void listBook() throws SQLException, NamingException, ClassNotFoundException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Create connection
            con = DBHelper.createConnection();
            
            if (con != null) {
                //2. Create String SQL
                String sql = "SELECT id, "
                        + "name, "
                        + "quantity, "
                        + "unitprice, "
                        + "status, "
                        + "FROM Book";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                
                //4. Execute Query
                rs = stm.executeQuery();
                
                //5. Process
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitprice = rs.getFloat("unitprice");
                    boolean status = rs.getBoolean("status");

                    //5.2 set data into DTO
                    BookDTO dto = new BookDTO(id, name, quantity, unitprice, status);

                    //5.2 Add DTO into list
                    if (this.books == null) {
                        this.books = new ArrayList<>();
                    }
                    this.books.add(dto);
                } //end traversal rs to get data
            } //end condition is available
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

}
