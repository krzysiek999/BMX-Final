/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frame.MainFrame;
import frame.PartFrame;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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

final int MAX_TRIES = 5;    
    
private String html, nameOfShop, category;
private int numberOfElements = 0, counter = 0, number = 0, helpNumber = 0;
private Elements[] arrayOfElements;
private String[] htmlElements;
private boolean initialized = false;
protected Document doc;
private MainFrame frame;
private boolean browserActivated = false;
PartFrame partFrame; 

int productIndexURL = 0;
int productIndex = 0;

ProductDatabaseHandler productDatabase = new ProductDatabaseHandler();

private String browserPartName = "", browserHTML = "";

String[] shopArray = {"bmxlife","avebmx","manyfestbmx","allday"};

private String [][] informationArray = new String[100][2];

ArrayList<ShopProduct> products = new ArrayList<>();

    public ShopResearcher()
    {
        
    }

    public ShopResearcher(String html)
    {
        this.html = html;
    }

    public ShopResearcher(String html, String nameOfShop, int numberOfElements) 
    {
    
    this.html = html;
    this.nameOfShop = nameOfShop;
    this.numberOfElements = numberOfElements;
    
    }
    
    public void createTable(){
        this.productDatabase.createTable();
    }
    
    public PartFrame getPartFrame()
    {
        return this.partFrame;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public int getShopNumber(String name)
    {
        for(int i=0;i<shopArray.length;i++)
            if(name.equals(shopArray[i])) return i;
        return -1;
    }
    
    public void setHTML(String html)
    {
        this.html = html;
    }
    
    
    public void setConnection()
    {
        while(counter < MAX_TRIES)
        {
            try {
                doc = Jsoup.connect(this.html).timeout(6000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36").get();
                break;
            } catch (IOException ex) {
                if(++counter == MAX_TRIES) JOptionPane.showMessageDialog(null, this.frame.getResourceBundle().getString("warningTimeoutInformation"), this.frame.getResourceBundle().getString("warning"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void initializeArrayOfElements(String[] htmlElements)
    {
     
     this.htmlElements = new String[4];
     
     for(int i = 0;i < htmlElements.length; i++)
     {
         this.htmlElements[i] = htmlElements[i];
     }
     
     initialized = true;
     
    }
    
    public ProductDatabaseHandler getProductDatabaseHandler()
    {
        return this.productDatabase;
    }
    
    public void searchHTMLElements()
    {
        this.arrayOfElements = new Elements[numberOfElements];
        int numberOfPages = 1;

        productIndex=0;
        productIndexURL=0;
        
        if(initialized)
        {
            if (getShopName().equals("avebmx")) 
            {
               Elements pages = doc.select("a.page");
               if(pages.size() > 0) numberOfPages = Integer.parseInt(pages.get(pages.size()-2).text());
               else numberOfPages = 1;
                System.out.println("NR STRON: " + numberOfPages);
            }
            
            for(int searchCounter = 0; searchCounter < numberOfPages; searchCounter++)
            {
            
                    Elements div = doc.select(htmlElements[3]);
                    Elements productName = div.select(htmlElements[0]);
                    Elements productPrice = div.select(htmlElements[1]);
                    Elements productURL = div.select(htmlElements[2]); 
        
                    Elements[] categoryElements =  {productName, productPrice, productURL};     
                    
                    for(int i=0;i<categoryElements.length;i++){
                     //j=0;
                     
                        for(Element element: categoryElements[i]){
                            switch (i){
                                    case 0 ->  {
                                        products.add(new ShopProduct(element.text(), "", ""));
                                    }
                                    case 1 ->  {
                                        products.get(productIndex).setProductPrice(element.text());
                                        System.out.println("HAHAL: " + products.get(productIndex).getProductDetails()[1]);
                                        productIndex += 1;
                                    }
                                    case 2 -> {
                                                products.get(productIndexURL).setProductURL(element.attr("href"));
                                                if(getShopName().equals("avebmx")) products.get(productIndexURL).setProductURL("https://www.avebmx.pl" + element.attr("href"));
                                                productIndexURL += 1;
                                    } 
                            }
                            
                        }
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
            
                for(int z=0;z<products.size();z++){
                    System.out.println("z: " + z + " l: " + products.get(z).getProductDetails()[0] + " , " + products.get(z).getProductDetails()[1] + " , " + products.get(z).getProductDetails()[2]);   
                }
            
                    String nameDatabase;;
                    String priceDatabase;
                    String URLDatabase;
                    
                    for(int saveCounter=0;saveCounter<products.size();saveCounter++)
                    {
                        nameDatabase = "'" + products.get(saveCounter).getProductDetails()[0] + "'";
                        priceDatabase = "'" + products.get(saveCounter).getProductDetails()[1] + "'";
                        URLDatabase = "'" + products.get(saveCounter).getProductDetails()[2] + "'";
                        productDatabase.insertElement(nameDatabase,priceDatabase,URLDatabase);
                    }
                    
                    productDatabase.closeConnection();
                        
                
                
            
            }
        
        else System.out.println("ARRAY NOT INITIALIZED");        
    }
    
    public String getDescription(String className, String desClass)
    {
        Elements descriptionClass = doc.select(className);
        String desc = "";
        for(Element e: descriptionClass.select("li"))
        {
           desc = desc + e.text() + "\n";
        }
        return desc;
    }
    
    public ArrayList<ShopProduct> getInformations()
    {
        return this.products;
    }
    
    public String searchProduct(String html, String nameOfPart)
    {
        this.setHTML(html);
        this.setConnection();
        initialized = true;
        browserActivated = true;
        this.searchHTMLElements();
        return this.browserHTML;
        
    }
    
    public void setShopName(String name)
    {
        this.nameOfShop = name;
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
