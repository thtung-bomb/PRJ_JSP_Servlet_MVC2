/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Thanh Tung
 */
public class DBHelper implements Serializable {

    public static Connection createConnection()
            throws ClassNotFoundException, SQLException, NamingException {
        
        //1. get current context
        Context currentContext = new InitialContext();
        //2. look up tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3. Look up our datasource
        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DS");
        //4. Open connection
        Connection con = ds.getConnection();
        
        return con;
        
        
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////        Class.forName(com.microsoft.sqlserver.jdbc.SQLServerDriver)
//        //2. Create Connection String URL
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Registration"; //them instancename o sau neu co cai nay
//        //nam o phia 
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//
//        return con;
    }
    
}
