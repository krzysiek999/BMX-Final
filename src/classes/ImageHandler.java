/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author krzys
 */
public class ImageHandler {
    
    URL url;
    Image image = null;
    ImageIcon imageIcon;
    private int resizeWidth=100, resizeHeight=100;

    public ImageHandler() {
    }
    
    public void setImage(String imageURL){
        try{
            url = new URL(imageURL);
            image = ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void resizeImage(){
         image = image.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_DEFAULT);
    }
    
    public ImageIcon getImage(){
        imageIcon = new ImageIcon(image);
        return this.imageIcon;
    }
        
}
