/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import panel.MainPanel;

/**
 *
 * @author krzys
 */
public class BrowserHandler {
    
    private String nameOfPart;
    private String html;
    private String nameOfShop;
    private String category;
    private int shopNumber = 0;

    private MainPanel mainPanel;
    private FilesHandler filesHandler = new FilesHandler();

    public BrowserHandler() 
    {
        
    }
    
    public void setMainPanel(MainPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public void setShop(String nameOfShop)
    {
        try{
        this.nameOfShop = nameOfShop;
        shopNumber = mainPanel.getResearcher().getShopNumber(nameOfShop);
        if(shopNumber<0) throw new Exception();
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "WRONG SHOP!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setHTML(){
        StringBuilder sqlNamePart = new StringBuilder();
        sqlNamePart.append("'").append(this.nameOfPart).append("'");
        this.html = this.mainPanel.getResearcher().getProductDatabaseHandler().getProductURL(sqlNamePart.toString());
        System.out.println("HTML: " + this.html);
    }
    
    public void setNameOfPart(String nameOfPart)
    {
        this.nameOfPart = nameOfPart;
    }
    
    public String getNameOfPart()
    {
        return this.nameOfPart;
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
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
