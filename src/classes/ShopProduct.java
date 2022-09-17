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
    
    String productName, productPrice, URL, category;
    String[] details = new String[5];
    
    public ShopProduct(String productName, String category) {
        details[0] = productName;
        details[4] = category;       
    }
    
    public String[] getProductDetails(){
        return details;
    }
    
    public String getCategory(){
        return this.details[4];
    }
    
    public String getProductName() {
        return this.details[0];
    }
    
    public String getProductPrice() {
        return this.details[1];
    }
    
    public String getProductURL() {
        return this.details[2];
    }
    
    public String getImageURL() {
        return this.details[3];
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
    
    public void setCategory(String category){
        details[4] = category;
    }
}
