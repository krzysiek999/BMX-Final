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
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 *
 * @author sluja
 */
public class ShopResearcher 
{

final int MAX_TRIES = 20;    
    
private String html, nameOfShop, category;
private int numberOfElements = 0, counter = 0, number = 0, helpNumber = 0, productIndex = 0, productIndexURL = 0, productImageIndex=0, pageSearchCounter = 0, numberOfPages = 0, indexSearchPage = 0;
private Elements[] arrayOfElements;
private String[] htmlElements;
private boolean initialized = false;
protected Document doc;
private MainFrame frame;
private boolean browserActivated = false;
PartFrame partFrame; 

Document.OutputSettings outputSettings = new Document.OutputSettings();
private Elements div, productName, productPrice, productURL, imageURL, pages;


ProductDatabaseHandler productDatabase = new ProductDatabaseHandler();


private String browserPartName = "", browserHTML = "";

String[] shopArray = {"bmxlife","avebmx","manyfestbmx","allday"};

private String [][] informationArray = new String[100][2];

ArrayList<ShopProduct> products = new ArrayList<>();
ArrayList<ShopProduct> specificProducts = new ArrayList<>();
ArrayList<String> pagesArray = new ArrayList<>();
ArrayList<String> pagesArrayAve = new ArrayList<>();


    public ShopResearcher()
    {
        
    }

    public ShopResearcher(String html)
    {
        this.html = html;
    }

    public ShopResearcher(String html, String nameOfShop) 
    {
        this.html = html;
        this.nameOfShop = nameOfShop;
    }
    
    public ArrayList<ShopProduct> getProductsArray(){
        return this.products;
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
        Elements partPage = doc.select(frame.getPropertyReader().getProperty("urlSearch"));//doc.select("div.category-miniature.no-image > p > a[href]");
        System.out.println("JESTEM TU KURWWY: " + partPage);
        
        for(Element e : partPage){
            if(e.absUrl("href").contains(nameOfPart)){
                setHTML(e.absUrl("href"));
                return;
            }
        }
    }
    
    public void setConnection()
    {
        while(counter < MAX_TRIES)
        {
            try {
                doc = Jsoup.connect(this.html).timeout(6000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36").get();
                break;
            } catch (IOException ex) {
                if(++counter == MAX_TRIES) {
                    JOptionPane.showMessageDialog(null, this.frame.getPropertyReader().getProperty("warningTimeoutInformation"), this.frame.getPropertyReader().getProperty("warning"), JOptionPane.ERROR_MESSAGE);
                    throw new NullPointerException();
                }
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
    
    public void setInitialized(boolean value){
        this.initialized = value;
    }
    
    public boolean isInitialized() {
        return this.initialized;
    }
    
    public ProductDatabaseHandler getProductDatabaseHandler()
    {
        return this.productDatabase;
    }
    
    // --- Get url to pages with products ---
    public void setPagesArray() {
    	pages = doc.select(this.frame.getPropertyReader().getProperty("pageSearchElementMain"));
        //System.out.println("SIEEEEEEEEEEMA CO TAM KUREWKI: " + pages.select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")));
    	numberOfPages=0;
        for(int i=0; i < pages.first().select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")).text().replaceAll("\\s+","").length(); i++){
            if(Character.isDigit(pages.first().select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")).text().replaceAll("\\s+","").charAt(i))) numberOfPages+=1;
        }
        System.out.println("ILOSC STRON: " + numberOfPages);
        pagesArray.add(pages.first().select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")).get(1).absUrl(this.frame.getPropertyReader().getProperty("pageSearchAttribute")).substring(0, pages.first().select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")).get(1).absUrl(this.frame.getPropertyReader().getProperty("pageSearchAttribute")).length()-1) + "1");
        for(int index = 1; index < numberOfPages; index++)
        {
            pagesArray.add(pages.first().select(this.frame.getPropertyReader().getProperty("pageSearchElementSub")).get(index).absUrl(this.frame.getPropertyReader().getProperty("pageSearchAttribute")));
           System.out.println("IND: " + index + " NR STRON ALLDAY 1: " + pagesArray .get(index));
        }
    }
    
    public void searchHTMLElements() {
    

        this.arrayOfElements = new Elements[numberOfElements];
        numberOfPages = 1;

        productIndex=0;
        productIndexURL=0;
        productImageIndex=0;
        
        pagesArray.clear();
                    
        
        this.setPagesArray();
        
        if(initialized)
        {
            
           // System.out.println("ILOSC STRON PRZED WYSZ " + numberOfPages);
            
            for(int searchCounter = 0; searchCounter < numberOfPages; searchCounter++){
            
            	if(numberOfPages > 1) {
            		setHTML(pagesArray.get(indexSearchPage));
            		if(indexSearchPage == pagesArray.size() - 1) indexSearchPage = pagesArray.size() - 1;
            		else indexSearchPage++;
                        System.out.println("ZMIANA STRONY");
            		setConnection();                    	
            	}
            	
                div = doc.select(this.frame.getPropertyReader().getProperty("div"));
                productName = div.select(this.frame.getPropertyReader().getProperty("productNameElement"));
                productPrice = div.select(this.frame.getPropertyReader().getProperty("productPriceElement"));
                productURL = div.select(this.frame.getPropertyReader().getProperty("productURLElement"));
                imageURL = doc.select(this.frame.getPropertyReader().getProperty("imageURLElement"));
                Document document;
                    
                    Elements[] categoryElements =  {productName, productPrice, productURL, imageURL};     
                    
                    for(int i=0;i<categoryElements.length;i++){
                     //j=0;
                     
                        for(Element element: categoryElements[i]){
                            switch (i){
                                    case 0 ->  {
                                        products.add(new ShopProduct(element.text(), getCategory()));
                                        System.out.println("NIE WIEM" + element.text());
                                    }
                                    case 1 ->  {
                                        products.get(productIndex).setProductPrice(element.text());
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
                                                products.get(productImageIndex).setImageURL(element.attr("src"));
                                                System.out.println("I: " + productImageIndex + " " + element.attr("src"));
                                                productImageIndex += 1;
                                    }
                            	}
                        	}
                    	}
            		}
           
                for(int z=0;z<products.size();z++){
                    System.out.println("z: " + z + " l: " + products.get(z).getProductDetails()[0] + " , " + products.get(z).getProductDetails()[1] + " , " + products.get(z).getProductDetails()[2]);   
                }
            
                    String nameDatabase;;
                    String priceDatabase;
                    String URLDatabase;
                    String imageURLDatabase;
                    String categoryDatabase;
                    
                    for(int saveCounter=0;saveCounter<products.size();saveCounter++)
                    {
                        products.get(saveCounter).setProductName(products.get(saveCounter).getProductDetails()[0].replace("'", "")); 
                        nameDatabase = "'" + products.get(saveCounter).getProductDetails()[0] + "'";
                        priceDatabase = "'" + products.get(saveCounter).getProductDetails()[1] + "'";
                        URLDatabase = "'" + products.get(saveCounter).getProductDetails()[2] + "'";
                        imageURLDatabase = "'" + products.get(saveCounter).getProductDetails()[3] + "'";
                        categoryDatabase = "'" + category + "'";
                        productDatabase.insertElement(nameDatabase, priceDatabase, URLDatabase, imageURLDatabase, categoryDatabase);
                    }
                    
                    productDatabase.closeConnection();
            }

        else System.out.println("ARRAY NOT INITIALIZED");        
        
    }
     /*   this.arrayOfElements = new Elements[numberOfElements];
        int numberOfPages = 1;

        pagesArrayAve.clear();
        pagesArray.clear();
        
        productIndex = products.size();
        productIndexURL = products.size();
        productImageIndex = products.size();
        
        if(initialized)
        {
            try{
            Elements test = doc.select(" ");
            }catch(SelectorParseException ex){
                System.out.println("SELECTOR PARSE EXCEPTION");
            }
            Elements pages;// = new Elements();
            int indexSearchPage = 0, indexSearchPageAve = 1;
            if (getShopName().equals("avebmx")){
               pages = doc.select("div.pagination");// > a.page");
                System.out.println("TESTTESTETSTETS: " + pages.first().select("a.page").text() + "  ,  " + pages.first().select("a.page").text().replaceAll("\\s+","").length());
                numberOfPages = 0;
                for(int i=0; i < pages.first().select("a.page").text().replaceAll("\\s+","").length(); i++){
                    if(Character.isDigit(pages.first().select("a.page").text().replaceAll("\\s+","").charAt(i))) numberOfPages+=1;
                }
               System.out.println("PAGES SIZE: " + pages.size());
                System.out.println("PAGESSSSS SIZE: " + numberOfPages);
               //if(pages.size() > 0) numberOfPages = pages.size()/2 - 1;
               //else numberOfPages = 1;
               pagesArrayAve.add(pages.first().select("a.page").get(1).absUrl("href").substring(0, pages.first().select("a.page").select("a").get(1).absUrl("href").length()-1) + "1");
                System.out.println("IND 0 NR STRONY: " + pagesArrayAve.get(0));
               for(int index = 1; index < numberOfPages; index++)
                {
                    pagesArrayAve.add(pages.first().select("a.page").select("a").get(index).absUrl("href"));
                    //pagesArrayAve.add(pages.select("a").get(index).absUrl("href"));
                    System.out.println("IND: " + index + " NR STRON ALLDAY 1: " + pagesArrayAve.get(index));
                }
                System.out.println("NR STRON: " + numberOfPages);
            }
            else if(getShopName().equals("allday")){
                //Elements pagesA = doc.select("div#js-product-list-top.products-selection");// > div.row.sort-by-row > ul.page-list.clearfix.text-xs-center");
                //pages = pagesA.select("div.row.sort-by-row > nav.pagination > ul.page-list.clearfix.text-xs-center");
               /* --- pages = doc.select("ul.page-list.clearfix.text-xs-center");
                if(pages.size() > 0) numberOfPages = pages.size();//Integer.parseInt(pages.get(pages.size()-1).text());
                else numberOfPages = 1;
                for(int index = 1; index < pages.size(); index++)
                {
                    pagesArray.add(pages.select("a").get(index).absUrl("href"));
                    System.out.println("NR STRON ALLDAY: " + pages.select("a").get(index).absUrl("href"));
                }
                System.out.println("NR STRON: " + numberOfPages); --- 
                               pages = doc.select("ul.page-list.clearfix.text-xs-center");// > a.page");
                System.out.println("TESTTESTETSTETS: " + pages.first().select("a").text() + "  ,  " + pages.first().select("a").text().replaceAll("\\s+","").length());
                numberOfPages = 0;
                for(int i=0; i < pages.first().select("a").text().replaceAll("\\s+","").length(); i++){
                    if(Character.isDigit(pages.first().select("a").text().replaceAll("\\s+","").charAt(i))) numberOfPages+=1;
                }
               System.out.println("PAGES SIZE: " + pages.size());
                System.out.println("PAGESSSSS SIZE: " + numberOfPages);
               //if(pages.size() > 0) numberOfPages = pages.size()/2 - 1;
               //else numberOfPages = 1;
               pagesArray.add(pages.first().select("a").get(1).absUrl("href").substring(0, pages.first().select("a").select("a").get(1).absUrl("href").length()-1) + "1");
                System.out.println("IND 0 NR STRONY: " + pagesArray.get(0));
               for(int index = 1; index < numberOfPages; index++)
                {
                    pagesArray.add(pages.first().select("a").select("a").get(index).absUrl("href"));
                    //pagesArrayAve.add(pages.select("a").get(index).absUrl("href"));
                    System.out.println("IND: " + index + " NR STRON ALLDAY 1: " + pagesArray.get(index));
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
                    
                                System.out.println();
                    
                    if(frame.getMainPanel().getShopName().equals("manyfestbmx")) {
                        imageURL = doc.select(htmlElements[4]).select("img[data-full-size-image-url]");
                        imageAttribute = "data-src";
                    }
                    else {
                        imageURL = doc.select(htmlElements[4]);
                        System.out.println("IMAGEEEEEE: " + imageURL);
                        imageAttribute = "src";
                    }
                    
                    Elements[] categoryElements =  {productName, productPrice, productURL, imageURL};     
                    
                    for(int i=0;i<categoryElements.length;i++){
                     //j=0;
                     
                        for(Element element: categoryElements[i]){
                            switch (i){
                                    case 0 ->  {
                                        products.add(new ShopProduct(element.text(), getCategory()));
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
                    if(getShopName().equals("avebmx") && numberOfPages > 1){
                        setHTML(pagesArrayAve.get(indexSearchPageAve));
                        if(indexSearchPageAve == pagesArrayAve.size() - 1) indexSearchPageAve = pagesArrayAve.size() - 1;
                        else indexSearchPageAve++;
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
                    System.out.println("z: " + z + " l: " + products.get(z).getProductDetails()[0] + " , " + products.get(z).getProductDetails()[1] + " , " + products.get(z).getProductDetails()[2] + " , ");   
                }
            
                    String nameDatabase;;
                    String priceDatabase;
                    String URLDatabase;
                    String imageURLDatabase;
                    String category;
                    
                    for(int saveCounter=0;saveCounter<products.size();saveCounter++)
                    {
                        products.get(saveCounter).setProductName(products.get(saveCounter).getProductDetails()[0].replace("'", "")); 
                        nameDatabase = "'" + products.get(saveCounter).getProductDetails()[0] + "'";
                        priceDatabase = "'" + products.get(saveCounter).getProductDetails()[1] + "'";
                        URLDatabase = "'" + products.get(saveCounter).getProductDetails()[2] + "'";
                        imageURLDatabase = "'" + products.get(saveCounter).getProductDetails()[3] + "'";
                        category = "'" + getCategory() + "'";
                        productDatabase.insertElement(nameDatabase, priceDatabase, URLDatabase, imageURLDatabase, category);
                    }
                    
                    productDatabase.closeConnection();
            }
        else System.out.println("ARRAY NOT INITIALIZED");        
    }
    */
    public String getDescription(String className) throws NullPointerException{   
      String[] separator = className.split(",");
      String finalS,str;
      Document jsoupDoc;
      for(int i=0; i < separator.length; i++){
            finalS = doc.select(separator[i]).html();
            jsoupDoc = Jsoup.parse(finalS);
        //select all <br> tags and append \n after that
            jsoupDoc.select("br").after("\\n");
        //select all <p> tags and prepend \n before that
            jsoupDoc.select("p").before("\\n");
        //get the HTML from the document, and retaining original new lines            
            System.out.println("SDHIUASHD: " + Jsoup.clean(jsoupDoc.html(),"", Whitelist.none(), outputSettings));
            str = jsoupDoc.html().replaceAll("\\\\n", "\n").replaceAll("&nbsp;", "");
            outputSettings.prettyPrint(false);
            if(!Jsoup.clean(str,"", Whitelist.none(), outputSettings).trim().isEmpty()) return Jsoup.clean(str,"", Whitelist.none(), outputSettings);
      }
      throw new NullPointerException();
    }
    
    public ArrayList<ShopProduct> getInformations()
    {
        return this.products;
    }
    
    public ArrayList<ShopProduct> getSpecificInformations(){
        return this.specificProducts;
    }
    
    public void setSpecificInformations(String category){
        for(int i=0; i < products.size(); i++){
            if(products.get(i).getCategory().equals(category)) specificProducts.add(products.get(i));
        }
    }
    
    public void clearProductsArray(){
        this.specificProducts.clear();
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
    
    public void test(){
        setHTML("https://bmxlife.pl/czesci");
        setConnection();
        Elements partPage = doc.select("div#producers-container > ul#products.producers.main-category.clearfix > li > a[href]");
        for(Element e : partPage){
            if(e.absUrl("href").contains("kierownice")){
                        System.out.println("TESTTESTTEST: " + e.absUrl("href"));
                return;
            }
        }

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
    
    public void initializePartPanel(boolean firstInitialized)
    {
        frame.setPartPanel(this, firstInitialized);
        frame.setActivePanel(frame.getPartLabel());
        //frame.getFeaturePanel().discountApply();
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
    }
    
    
}
