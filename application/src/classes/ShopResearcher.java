/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frame.MainFrame;
import frame.PartFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author sluja
 */
public class ShopResearcher 
{

private String html, nameOfShop;
private int numberOfElements;
private Elements[] arrayOfElements;
private String[] htmlElements;
private boolean initialized = false;
private Document doc;
private int number, helpNumber;
private MainFrame frame = new MainFrame();
private String category;
PartFrame partFrame; 

private String[][] informationArray = new String[100][2];

    public ShopResearcher(String html, String nameOfShop, int numberOfElements) 
    {
    
    this.html = html;
    this.nameOfShop = nameOfShop;
    this.numberOfElements = numberOfElements;
    
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public void setConnection()
    {
        
        try {
            doc = Jsoup.connect(this.html).timeout(6000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36").get();
        } catch (IOException ex) {
            Logger.getLogger(ShopResearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void initializeArrayOfElements(String[] htmlElements)
    {
     
     this.htmlElements = new String[numberOfElements];
     
     for(int i = 0;i < htmlElements.length; i++)
     {
         this.htmlElements[i] = htmlElements[i];
     }
     
     initialized = true;
     
    }
    
    public void searchHTMLElements()
    {
        this.arrayOfElements = new Elements[numberOfElements];
        int numberOfPages = 1;
        
        if(initialized)
        {
            if (getShopName().equals("avebmx")) numberOfPages = 5;
            
            for(int searchCounter = 0; searchCounter < numberOfPages; searchCounter++)
            {
                for(int i = 0; i < this.arrayOfElements.length; i++)
                {
                    this.arrayOfElements[i] = doc.select(htmlElements[i]);
                }
            
                int i =0;
                int z = 0;
                int j = 0;
              
                for(j = 0; j < this.arrayOfElements.length; j++)
                {
                    z = searchCounter * 20;
                    for(Element list: this.arrayOfElements[j])
                    {
                        i++;
                    
                        if(!(this.nameOfShop.equals("manyfestbmx") && j == 1 && i >= (helpNumber + 1) && i  <= (3 + helpNumber))) 
                        {
                            System.out.println(i + " " + list.text());//list.getElementsByTag("a").first().
                            informationArray[z][j] = list.text();
                            z++;
                        }
                        number = z;
                    }
                    helpNumber = number;
                }
                
                if(getShopName().equals("avebmx"))
                {
                    int htmlLength = this.html.length();
                    int addPageNumberInt = searchCounter + 2;
                    String addPageNumber = "" + addPageNumberInt;
                    this.html = this.html.substring(0, htmlLength-1);
                    this.html = this.html + addPageNumber;
                    System.out.println("xd: " + this.html);
                    setConnection();
                }
            }
            
            
        }
        else System.out.println("ARRAY NOT INITIALIZED");
            
    }
    
    public String[][] getInformations()
    {
        return this.informationArray;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public String getHTML()
    {
        return this.html;
    }
    
    public void setFrame(MainFrame frame)
    {
        this.frame = frame;
    }
    
    public void initializePartPanel()
    {
        partFrame = new PartFrame(this, this.frame);
        partFrame.setVisible(true);
        frame.dispose();
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
    }
    
    
}
