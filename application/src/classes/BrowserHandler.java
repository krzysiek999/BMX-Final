/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krzys
 */
public class BrowserHandler {
    
    private String nameOfPart;
    private String html;
    private String nameOfShop;
    private String category;

    public BrowserHandler() 
    {
    
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public void setShop(String nameOfShop)
    {
        this.nameOfShop = nameOfShop;
    }
    
    public void setNameOfPart(String nameOfPart)
    {
        if(this.nameOfShop.equals("bmxlife") || this.nameOfShop.equals("avebmx"))
        {
            this.nameOfPart = nameOfPart;
            this.nameOfPart = this.nameOfPart.replace(" ", "-");
            this.nameOfPart = this.nameOfPart.replace("(", "");
            this.nameOfPart = this.nameOfPart.replace(")", "");
        }
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
    }
    
    public void setHTML()
    {
        this.html = "https://" + this.nameOfShop + ".pl" + "/" + category;
        this.html = this.html + "/" + this.nameOfPart;
        System.out.println(this.html);
    }
    
    public void openBrowser()
    {
        Runtime rt = Runtime.getRuntime();
        String url = this.html;
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException ex) {
            Logger.getLogger(BrowserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
