/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.registration;

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
public class RegistrationDAO implements Serializable {
    
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException, ClassNotFoundException { //bat loi con.close();
        
        Connection con = null;//khai bao
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. Process 
                if (rs.next()) {
                    //result = true;
                    //mapping
                    //5.1 get Data from result set
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set Data to DTO
                    result = new RegistrationDTO(username, null, fullName, role); //pass -> null
                } //end username and password are existed
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
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    } //getA ctrl + space

    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException, NamingException { //bat loi con.close();

        Connection con = null;//khai bao
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where lastname Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
//                System.out.println("Search: " + searchValue);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. Process 
                while (rs.next()) {
                    //5.1 get data from rs 
                    //khong nen truyen theo thu tu cot (int)
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    //5.2 set data into DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);

                    //5.3 add DTO into List
                    if (this.accounts == null) {
                        accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
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

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException, NamingException { //bat loi con.close();

        Connection con = null;//khai bao
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Excute Query
//                rs = stm.executeQuery();
                int effecRows = stm.executeUpdate();
                //5. Process 

                if (effecRows > 0) {
                    result = true;
                }
            } //end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException, ClassNotFoundException {

        //connect
        Connection con = null; //declare con
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set password = ?, "
                        + "isAdmin = ? "
                        + "Where username = ?";

                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);

                //4. Execute Query  
//                stm.executeQuery();
                //5. Process
                int effectRow = stm.executeUpdate();

                if (effectRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean createNewAccount(String username, String password, String lastName, boolean isRole)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO Registration (username, password, lastname, isAdmin) "
                        + "VALUES (? , ?, ?, ?);";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastName);
                stm.setBoolean(4, isRole);
                //4. Execute Query  
                //5. Process
                int effectRow = stm.executeUpdate();

                if (effectRow > 0) {
                    return true;
                }
            }
        } finally {
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
