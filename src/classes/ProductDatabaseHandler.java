/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;


import static interferences.DatabaseInterference.DB_URL;
import static interferences.DatabaseInterference.JDBC_DRIVER;
import static interferences.DatabaseInterference.PASS;
import static interferences.DatabaseInterference.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sluja
 */
public class ProductDatabaseHandler extends DatabaseHandler{

   private String tableName = "ProductTable";
       
      //  Initializing connection, statement and results variables
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet rsSearchElement = null;
   private String result = "";
    
   public ProductDatabaseHandler() {
       try { 
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL,USER,PASS); 
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }catch(SQLException ex1)
       {
       }
    }
   
   public String getTableTitle(){
       return this.tableName;
   }
   
    public void createTable() {
        try { 
           statement = connection.createStatement();
           StringBuilder sql = new StringBuilder("CREATE TABLE ").append(this.tableName).append(" (ID int, ")
                   .append("productName VARCHAR(255), ").append(" price VARCHAR(255), ").append(" URL VARCHAR(255), ")
                   .append("category VARCHAR(255), ").append(" imageURL VARCHAR(255))");
            
         statement.executeUpdate(sql.toString());
         statement.close();
         }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TABLE NOT INITIALIZED","ERROR",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
       }
    }

   @Override
    public void updateElement(int id, String value){
        try {
           statement = connection.createStatement();
           StringBuilder request = new StringBuilder("UPDATE ").append(this.tableName).append(" SET price = ")
                                   .append(value).append("WHERE ID =").append(id).append(";");   
           statement.executeUpdate(request.toString()); 
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @Override
    public void insertElement(String productName, String price, String url, String imageURL, String category) {
        try {
           statement = connection.createStatement();
           StringBuilder request = new StringBuilder("INSERT INTO ").append(this.tableName).append("(ID, productName, price, URL, category, imageURL) VALUES (")
                                   .append(index).append(", ").append(productName).append(", ").append(price).append(", ").append(url)
                                   .append(", ").append(category).append(", ").append(imageURL).append(");");
           
           statement.executeUpdate(request.toString()); 
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
       index++;
    }
    
    public String getProductURL(String productName){
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("SELECT URL FROM ").append(tableName).append(" WHERE productName = ").append(productName).append(";");
            rsSearchElement = statement.executeQuery(query.toString());
            if(rsSearchElement.next()) result = rsSearchElement.getString("URL");
            statement.close();
            } catch(SQLException s){		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return result;
    }
    
    public String getImageURL(String productName){
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("SELECT imageURL FROM ").append(tableName).append(" WHERE productName = ").append(productName).append(";");
            rsSearchElement = statement.executeQuery(query.toString());
            if(rsSearchElement.next()) result = rsSearchElement.getString("imageURL");
            statement.close();
            } catch(SQLException s){		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return result;
    }
    
    public String getPrice(int id){
        try{
            statement = connection.createStatement();
	    StringBuilder query = new StringBuilder("SELECT price FROM ").append(tableName).append(" WHERE id = ").append(id).append(";");
            rsSearchElement = statement.executeQuery(query.toString());
            if(rsSearchElement.next()) result = rsSearchElement.getString("price");
            statement.close();
            } catch(SQLException s){		
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return result;
    }
    
    public void dropTable(){
                Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "DROP TABLE ProductTable";
            
            stmt.execute(sql);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
               
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    @Override
    public boolean tableExists() {
        try (ResultSet rsTableExists = connection.getMetaData().getTables(null, null, tableName, null)) {
            if(rsTableExists.next()) { 
               return true;
            }
        }catch(SQLException ex2)
        {
            return false;
        }
        return false;
    }
    
}
