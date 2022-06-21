/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author sluja
 */
public class ShopProduct {
    
    String productName, productPrice, URL;
    String[] details = new String[3];
    
    public ShopProduct(String productName, String productPrice, String URL) {
        details[0] = productName;
        details[1] = productPrice;
        details[2] = URL;
    }
    
    public String[] getProductDetails(){
        return details;
    }
    
    
    public void setProductName(String name){
        details[0] = name;
    }
    
    public void setProductPrice(String price){
        details[1] = price;
    }
    
    public void setProductURL(String URL){
        details[2] = URL;
    }
}
