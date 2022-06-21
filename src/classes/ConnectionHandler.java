/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author sluja
 */
public class ConnectionHandler extends ShopResearcher{
   
    
    public ConnectionHandler(String html) {
        super(html);
        super.setConnection();
    }
    
    public void searchAddress(String mainClass, String secondClass, String lowerClass, String attribute, String partialAddress)
    {
        Elements links = doc.select(mainClass);
        links = links.select(secondClass);
        //System.out.println(links);
        for (Element link : links) {
            String a = link.getElementsByTag(lowerClass).attr(attribute);
            //System.out.println("LOLOL: " + a);
            if(a.contains(partialAddress))
            {
                System.out.println("CHUJ: " + a);
                break;
            }
            
        }
    }
    
    private void setChangedHTML(String html){}
    
}
