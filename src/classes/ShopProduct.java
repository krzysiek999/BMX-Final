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
    String[] details = new String[4];
    
    public ShopProduct(String productName) {
        details[0] = productName;
    }
    
    public String[] getProductDetails(){
        return details;
    }
    
    public void setImageURL(String imageURL){
        details[3] = imageURL;
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
