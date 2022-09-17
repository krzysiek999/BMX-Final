 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author krzys
 */
public class ImageHandler {
    
    URL url;
    Image image = null;
    ImageIcon imageIcon;
    BufferedImage bi = null;
    private int resizeWidth=120, resizeHeight=120;

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
    
    public void setLabel(JLabel label, int width, int height){
        Icon icon = label.getIcon();
        bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
        //resizeImage(width, height, bi);
    }
    
    public void resizeJLabelIcon(int width, int height, JLabel label){
        ImageIcon actualImageIcon = (ImageIcon)label.getIcon();
        image = actualImageIcon.getImage();
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        this.imageIcon = new ImageIcon(bi);
        label.setIcon(imageIcon);
    }
    
    public void resizeImage(){
         image = image.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_DEFAULT);
    }
    
    public void setLabelIcon(JLabel label){
        imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);
    }
    
    public ImageIcon getImage(){
        imageIcon = new ImageIcon(image);
        return this.imageIcon;
    }
        
}
