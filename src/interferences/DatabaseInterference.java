/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interferences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author sluja
 */
public interface DatabaseInterference {
    
    // JDBC driver name and database URL 
   static final String JDBC_DRIVER = "org.h2.Driver";   
   static final String DB_URL = "jdbc:h2:~/test";  
   
   //  Database credentials 
   static final String USER = "sa"; 
   static final String PASS = ""; 
   
public void createTable(String tableName);
public void deleteAllElements(String tableName);
public String searchElement(String tableName, String attribute, String productName);
public void insertElement(String productName, String category, String shopName);
public void closeConnection();
public boolean tableExists();
}
