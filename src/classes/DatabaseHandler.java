/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import interferences.DatabaseInterference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import panel.MainPanel;
/**
 *
 * @author sluja
 */
public class DatabaseHandler implements DatabaseInterference{
    
   
   private String basketTableName = "BasketTable";
   
   int index = 0;

   private MainPanel mainPanel;
   
      //  Initializing connection, statement and results variables
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet rsSearchElement = null;
   
   private ArrayList<String[]> allElementsArray = new ArrayList<>();
   
    public DatabaseHandler() {
    
       try { 
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL,USER,PASS); 
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }catch(SQLException ex1)
       {
           
       }
    }
    
    public void setMainPanel(MainPanel panel)
    {
        this.mainPanel = mainPanel;
    }
    
    public MainPanel getMainPanel()
    {
        return this.mainPanel;
    }
    
    public void createTable(String tableName)
    {
       try { 
           statement = connection.createStatement();
           StringBuilder sql = new StringBuilder("CREATE TABLE ").append(basketTableName).append(" (ID int, ")
                   .append("productName VARCHAR(255), ").append(" category VARCHAR(255), ").append(" shopName VARCHAR(255))");
            
         statement.executeUpdate(sql.toString());
         statement.close();
       }catch (SQLException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
    

    
    public void deleteAllElements(String tableName)
    {
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("DELETE FROM ").append(tableName).append(";");
            int deletedRows=statement.executeUpdate(query.toString());
            if (deletedRows <= 0) ;	
            statement.close();
            } catch(SQLException s){
                System.out.println("Deleted All Rows In  Table Error. ");		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
    
    public void deleteOneElement(String tableName, String productName)
    {
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE productName = ").append(productName).append(";");
            statement.executeUpdate(query.toString());
            statement.close();
            } catch(SQLException s){
                System.out.println("Deleted All Rows In  Table Error. ");		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
    
    public String getTableTitle()
    {
        return this.basketTableName;
    }
    
    
    public String searchElement(String tableName, String attribute, String productName)
    {
        String result = "";
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("SELECT ").append(attribute).append(" FROM ").append(tableName).append(" WHERE productName = ").append(productName).append(";");
            rsSearchElement = statement.executeQuery(query.toString());
            if(rsSearchElement.next()) result = rsSearchElement.getString(attribute);
            statement.close();
            } catch(SQLException s){		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return result;
    }
    
    public ArrayList<String[]> searchAllElements(String tableName)
    {
        String[] result = new String[4];
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("SELECT * FROM ").append(tableName).append(";");
            rsSearchElement = statement.executeQuery(query.toString());
            if(rsSearchElement.next()) {
                result[0] = "" + rsSearchElement.getInt("ID");
                result[1] = rsSearchElement.getString("productName");
                result[2] = rsSearchElement.getString("category");
                result[3] = rsSearchElement.getString("shopName");
                allElementsArray.add(result);
            }
            statement.close();
            } catch(SQLException s){		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return allElementsArray;
    }
    
    public void insertElement(String productName, String category, String shopName)
    {
       try {
           statement = connection.createStatement();
           StringBuilder request = new StringBuilder("INSERT INTO ").append(basketTableName).append("(ID, productName, category, shopName) VALUES (").
                                   append(index).append(", ").append(productName).append(", ").append(category).append(", ").append(shopName).append(");");
           
           statement.executeUpdate(request.toString()); 
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
       index++;
    }
    
    public void closeConnection()
    {
       try {
           this.connection.close();
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public boolean tableExists()
    {
        try (ResultSet rsTableExists = connection.getMetaData().getTables(null, null, basketTableName, null)) {
            if(rsTableExists.next()) { 
               return true;
            }
        }catch(SQLException ex2)
        {
            return false;
        }
        return false;
    }

    @Override
    public void dropTable(String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertElement(String productName, String category, String shopName, String imageURL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
   
    
}
