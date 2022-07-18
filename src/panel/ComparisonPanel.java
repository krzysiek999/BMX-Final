/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import classes.ConnectionHandler;
import classes.ShopResearcher;
import frame.MainFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;


/**
 *
 * @author krzys
 */
public class ComparisonPanel extends javax.swing.JPanel {

    /**
     * Creates new form ComparisonPanel
     */
    private JSplitPane splitPane;
    private GridLayout comparisonLayout = new GridLayout(2,1,20,20);
    MainFrame mainFrame;
    Box box;
    JPanel splitPanel = new JPanel();
    
    private ProductPanel product1 = new ProductPanel();
    private ProductPanel product2 = new ProductPanel();
    ButtonPanel buttonPanel;
    
    
    public ComparisonPanel(MainFrame frame) {
        
        this.mainFrame = frame;
        
        buttonPanel = new ButtonPanel();
        
        //splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, product1, product2);
        
        //splitPane.setDividerLocation(frame.getElementOneHeight()/3);
        
        
        //splitPanel.add(splitPane);
        splitPanel.setLayout(comparisonLayout);
        splitPanel.setMaximumSize(new Dimension(frame.getElementOneWidth(), frame.getElementOneHeight()));
        splitPanel.setBounds(0, 0, frame.getElementOneWidth(), frame.getElementOneHeight());
        splitPanel.add(product1);
        splitPanel.add(product2);
        box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(850,900));
       
     
        
        box.add(splitPanel);
        box.add(Box.createVerticalStrut(30));
        box.add(buttonPanel);
        
        
        this.add(box);
    }
    
    public ProductPanel getProductOne()
    {
        return this.product1;
    }
    
    public ProductPanel getProductTwo()
    {
        return this.product2;
    }
    
    public void repaintMainPanel()
    {
        this.revalidate();
        this.repaint();
    }
    
    public void setButtonText()
    {
        this.buttonPanel.setButtonText();
        repaintMainPanel();
    }
    
    public MainFrame getFrame()
    {
        return this.mainFrame;
    }
    class ButtonPanel extends JPanel implements ActionListener
    {    
        JButton backButton = new JButton(getFrame().getResourceBundle().getString("back"));
        JButton basketButton = new JButton(getFrame().getResourceBundle().getString("basket"));
        JButton okokButton = new JButton(getFrame().getResourceBundle().getString("erase"));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 40);

        public ButtonPanel() {
        
            this.setLayout(layout);
            this.add(backButton);
            this.add(basketButton);
            this.add(okokButton);
            
            backButton.addActionListener(this);
            basketButton.addActionListener(this);
            okokButton.addActionListener(this);
            
        }
        
        public void setButtonText()
        {
            this.backButton.setText(getFrame().getResourceBundle().getString("back"));
            this.basketButton.setText(getFrame().getResourceBundle().getString("basket"));
            this.repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource().equals(backButton))
            {
              mainFrame.setCompareButtonVisible(false);
              mainFrame.setActivePanel(mainFrame.getMainLabel());
            }
            else if(e.getSource().equals(basketButton))
            {
               boolean visibilty;
               visibilty = !mainFrame.getMainPanel().getBasketFrame().isVisible();            
               mainFrame.getBasketFrame().setVisible(visibilty);
               mainFrame.setCompareButtonVisible(true);
            }
        }
    }
    
    class ProductPanel extends JScrollPane
    {

        Box productBox;
        JLabel productNameLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        //JScrollPane scrollPane = new JScrollPane();
        JPanel elementsPanel = new JPanel();
        private boolean filled = false;
        private String nameOfShop = "";
        private JTextArea description = new JTextArea();//150,60);
        private ConnectionHandler connectionHandler;
        private int numberOfShop = 0;
        
        public ProductPanel() 
        {
            this.setMaximumSize(new Dimension(300,500));
            this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            description.setWrapStyleWord(true);
            //description.setMaximumSize(new Dimension(200, 400));
            //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            this.setPreferredSize(new Dimension(350,700));
            productBox = Box.createVerticalBox();
            productBox.add(productNameLabel);
            productBox.add(Box.createVerticalStrut(20));
            productBox.add(priceLabel);
            productBox.add(Box.createVerticalStrut(20));
            productBox.add(description);
            elementsPanel.add(productBox);
            description.setEditable(false);
            this.setViewportView(elementsPanel);
            //this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //scrollPane.add(productBox);
            //this.add(scrollPane);
            
        }
        
        public boolean isFilled()
        {
            return this.filled;
        }
        
        public void fill(boolean value)
        {
            this.filled = value;
        }
        
        public void setDescription(String partHTML, String nameOfShop)
        {
            try{
            mainFrame.getMainPanel().getResearcher(nameOfShop).setHTML(partHTML);
            mainFrame.getMainPanel().getResearcher(nameOfShop).setConnection();
            description.setText(mainFrame.getMainPanel().getResearcher(nameOfShop).getDescription(mainFrame.getComparisonShop())); 
            }catch(NullPointerException ex){
                description.setText(mainFrame.getResourceBundle().getString("nullDescription")); 
            }
        }
        
        private void repaintPanel()
        {
            this.revalidate();
            this.repaint();
        }
        
        public void setProductName(String name)
        {
            productNameLabel.setText(name);
            repaintPanel();
            repaintMainPanel();
        }
        
        public void setShopName(String name)
        {
            this.nameOfShop = name;
            if(name.contains("manyfestbmx")) numberOfShop = 3;
            else if(name.contains("bmxlife")) numberOfShop = 1;
            else if(name.contains("avebmx")) numberOfShop = 2;
        }
        
        public String getShopName()
        {
            return this.nameOfShop;
        }
        
        public int getNumberOfShop()
        {
            return this.numberOfShop;
        }
        
        public void setPrice(String price)
        {
            priceLabel.setText(price);
            repaintPanel();
            repaintMainPanel();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
