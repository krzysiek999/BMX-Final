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

int productIndexURL = 0, productIndex = 0, productImageIndex = 0;

ProductDatabaseHandler productDatabase = new ProductDatabaseHandler();


private String browserPartName = "", browserHTML = "";

String[] shopArray = {"bmxlife","avebmx","manyfestbmx","allday"};

private String [][] informationArray = new String[100][2];

ArrayList<ShopProduct> products = new ArrayList<>();
ArrayList<String> pagesArray = new ArrayList<>();

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
    
    public void searchPage(String nameOfPart){
        Elements partPage = doc.select("div.category-miniature.no-image > p > a[href]");
        for(Element e : partPage)
        {
            if(e.absUrl("href").contains(nameOfPart)){
                setHTML(e.absUrl("href"));
                return;
            }
        }
        System.out.println("ALLDAY: " + partPage);
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
     
     this.htmlElements = new String[5];
     
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
        productImageIndex=0;
        
        if(initialized)
        {
            Elements pages = new Elements();
            int indexSearchPage = 0;
            if (getShopName().equals("avebmx")) 
            {
               pages = doc.select("a.page");
               if(pages.size() > 0) numberOfPages = Integer.parseInt(pages.get(pages.size()-2).text());
               else numberOfPages = 1;
                System.out.println("NR STRON: " + numberOfPages);
            }
            else if(getShopName().equals("allday"))
            {
                pages = doc.select("ul.page-list.clearfix.text-xs-center");
                if(pages.size() > 0) numberOfPages = pages.size();//Integer.parseInt(pages.get(pages.size()-1).text());
                else numberOfPages = 1;
                for(int index = 1; index < pages.size(); index++)
                {
                    pagesArray.add(pages.select("a").get(index).absUrl("href"));
                    System.out.println("NR STRON ALLDAY: " + pages.select("a").get(index).absUrl("href"));
                }
                System.out.println("NR STRON: " + numberOfPages);
            }
            
            for(int searchCounter = 0; searchCounter < numberOfPages; searchCounter++)
            {
            
                    Elements div = doc.select(htmlElements[3]);
                    Elements productName = div.select(htmlElements[0]);
                    Elements productPrice = div.select(htmlElements[1]);
                    Elements productURL = div.select(htmlElements[2]);
                    Elements imageURL;
                    String imageAttribute;
                    if(frame.getMainPanel().getShopName().equals("manyfestbmx")) {
                        imageURL = doc.select(htmlElements[4]).select("img[data-full-size-image-url]");
                        imageAttribute = "data-src";
                    }
                    else {
                        imageURL = div.select(htmlElements[4]);
                        System.out.println("IMAGEEEEEE: " + imageURL);
                        imageAttribute = "src";
                    }
                    
                    Elements[] categoryElements =  {productName, productPrice, productURL, imageURL};     
                    
                    for(int i=0;i<categoryElements.length;i++){
                     //j=0;
                     
                        for(Element element: categoryElements[i]){
                            switch (i){
                                    case 0 ->  {
                                        products.add(new ShopProduct(element.text()));
                                    }
                                    case 1 ->  {
                                        products.get(productIndex).setProductPrice(element.text().replaceAll(",", "."));
                                        System.out.println("HAHAL: " + products.get(productIndex).getProductDetails()[1]);
                                        productIndex += 1;
                                    }
                                    case 2 -> {  
                                                products.get(productIndexURL).setProductURL(element.attr("href"));
                                                if(getShopName().equals("avebmx")) products.get(productIndexURL).setProductURL("https://www.avebmx.pl" + element.attr("href"));
                                                System.out.println("L" + productIndexURL + " " + element.attr("href"));
                                                productIndexURL += 1;                           
                                    } 
                                    case 3 -> {
                                                products.get(productImageIndex).setImageURL(element.attr(imageAttribute));
                                                System.out.println("I: " + productImageIndex + " " + element.attr(imageAttribute));
                                                productImageIndex += 1;
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
                    else if(getShopName().equals("allday")){
                        setHTML(pagesArray.get(indexSearchPage));
                        if(indexSearchPage == pagesArray.size() - 1) indexSearchPage = pagesArray.size() - 1;
                        else indexSearchPage++;
                        setConnection();
                    }
                }    
            
                for(int z=0;z<products.size();z++){
                    System.out.println("z: " + z + " l: " + products.get(z).getProductDetails()[0] + " , " + products.get(z).getProductDetails()[1] + " , " + products.get(z).getProductDetails()[2]);   
                }
            
                    String nameDatabase;;
                    String priceDatabase;
                    String URLDatabase;
                    String imageURLDatabase;
                    
                    for(int saveCounter=0;saveCounter<products.size();saveCounter++)
                    {
                        products.get(saveCounter).setProductName(products.get(saveCounter).getProductDetails()[0].replace("'", "")); 
                        nameDatabase = "'" + products.get(saveCounter).getProductDetails()[0] + "'";
                        priceDatabase = "'" + products.get(saveCounter).getProductDetails()[1] + "'";
                        URLDatabase = "'" + products.get(saveCounter).getProductDetails()[2] + "'";
                        imageURLDatabase = "'" + products.get(saveCounter).getProductDetails()[3] + "'";
                        productDatabase.insertElement(nameDatabase, priceDatabase, URLDatabase, imageURLDatabase);
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
        frame.setPartPanel(this);
        frame.setActivePanel(frame.getPartLabel());
        //frame.getFeaturePanel().discountApply();
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
    }
    
    
}
