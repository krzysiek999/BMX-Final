/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import classes.BrowserHandler;
import classes.ShopResearcher;
import frame.BasketMainFrame;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author krzys
 */
public class BasketMainPanel extends javax.swing.JPanel {

    /**
     * Creates new form BasketMainPanel
     */
    private final int amountOfShop = 4;
    //private String[] shopArray = new String[amountOfShop];
    //private BasketShopPanel[] basketTabPanel = new BasketShopPanel[amountOfShop];
    private ArrayList<String> shopArray = new ArrayList<String>();
    private ArrayList<BasketShopPanel> basketTabPanel = new ArrayList<BasketShopPanel>();
    private ArrayList<Float> priceList = new ArrayList<Float>();
    
    
    private boolean previousExistance = false;
    private MainPanel mainPanel;
    
    BasketMainFrame frame;
    BrowserHandler browser = new BrowserHandler();
    private String resourceLanguage;
    ResourceBundle resource;
    
    private int i = 0, j = 0, previousTabNumber = 0, labelCounter = 0;
    
    float totalPrice = 0;
    int productPrice = 0;
    
    public BasketMainPanel(MainPanel mainPanel, BasketMainFrame frame) {
        
        this.mainPanel = mainPanel;
        
        resourceLanguage = "properties/" + this.mainPanel.getFrame().getLanguage();
        resource = ResourceBundle.getBundle(this.resourceLanguage);
        
        initComponents(); 
        deleteButton.setText(resource.getString("delete"));
        browserButton.setText(resource.getString("search"));
        compareButton.setText(resource.getString("compare"));
        compareButton.setEnabled(false);
        
        shopTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                basketTabPanel.get(getTabPane().getSelectedIndex()).getTable().getSelectionModel().clearSelection();
                 
            }
        });
        
        
    }
    
    public JButton getCompareButton()
    {
        return this.compareButton;
    }
    
    public JTabbedPane getTabPane()
    {
        return this.shopTabbedPane;
    }
    
    public void addElementToArray(String shopName)
    {
        shopArray.add(shopName); 
    }
    
   /* public String[] getArray()
    {
        return shopArray;
    }*/

    public int getAmountOfElements()
    {
        return this.i;
    }
    
    public void addTab(String name)
    {
       basketTabPanel.add(new BasketShopPanel(name));
       this.getTabPane().addTab(name, basketTabPanel.get(i));
       getTabPane().setSelectedIndex(i);
       i++;
    }
    
    public void addElementToBasket(String name, String price, ShopResearcher researcher)
    {
       
            int numberOfTab = 0;
            String tabTitle = "";
        
            for(int counter = 0; counter < this.getTabPane().getTabCount(); counter++)
            {
                tabTitle = this.getTabPane().getTitleAt(counter);
            
                if(tabTitle.equals(researcher.getShopName())) 
                {
                    numberOfTab = counter;
                }
            }
        
            basketTabPanel.get(numberOfTab).getPanel().addElementToTable(name, price);
            setPrice(getTabPane().getTitleAt(numberOfTab), numberOfTab, basketTabPanel.get(numberOfTab));
        
    }
    
    void setPrice(String shopName, int numberOfTab, BasketShopPanel panel)
    {
        
        
        String priceString;
        int counter = 0;
        JLabel[] arrayLabelPrice = {totalPriceLabel1,totalPriceLabel2,totalPriceLabel3,totalPriceLabel4};
        JLabel[] arrayLabelShop = {shopOneLabel,shopTwoLabel,shopThreeLabel,shopFourLabel};
        
        if(panel.getProductsPrice() != null)
        {
            
           if(panel.getProductsPrice().size() <= 1) counter = 0;
           else counter = panel.getProductsPrice().size() - 1;
        
           if(counter != 0 && previousExistance)
           {
               float previousPrice = 0;
               String previousPriceString = "";
               float previousPriceFloat = 0;
               
               for(int counterPreviousPrice = 0; counterPreviousPrice < panel.getProductsPrice().size(); counterPreviousPrice++)
               {
                   if(panel.getProductsPrice().get(counterPreviousPrice).contains("PLN"))  previousPriceString = panel.getProductsPrice().get(counterPreviousPrice).replace("PLN", " ");
                       else 
                       {
                            previousPriceString = panel.getProductsPrice().get(counterPreviousPrice).replace("zł", " "); 
                            previousPriceString = previousPriceString.replace(",", "."); 
                            previousPriceString = previousPriceString.replace(" ", "");
                       }
                       previousPriceFloat = Float.parseFloat(previousPriceString);
                   
                   previousPrice += previousPriceFloat;
               }
               
               priceList.set(numberOfTab, previousPrice);
               previousExistance = false;
           }
           
           else
           {
                while(counter < panel.getProductsPrice().size())
                {
                
                       if(panel.getProductsPrice().get(counter).contains("PLN"))  priceString = panel.getProductsPrice().get(counter).replace("PLN", " ");
                       else 
                       {
                            priceString = panel.getProductsPrice().get(counter).replace("zł", " "); 
                            priceString = priceString.replace(",", "."); 
                            priceString = priceString.replace(" ","");
                       }
                       float priceFloat = Float.parseFloat(priceString);
                    
                    
                       if(previousExistance && (priceList.get(numberOfTab).floatValue() == 0.0) )
                        {
                            priceList.set(numberOfTab, priceFloat);
                            previousExistance = false;
                        }
                        else if(!previousExistance || (previousExistance && priceList.get(numberOfTab) != 0.0))
                        {
                            if(numberOfTab == previousTabNumber && counter == 0 && !previousExistance)
                            {
                                priceList.add(priceFloat);
                            }
                            else if(numberOfTab != previousTabNumber && counter == 0 && !previousExistance)
                            {
                                priceList.add(priceFloat);
                                previousTabNumber = numberOfTab;
                            }
                            else if(counter == 0 && previousExistance)
                            {
                                priceList.set(numberOfTab, priceFloat);
                                previousExistance = false;
                            }
                            else if(counter != 0)
                            {
                                float actualPrice = priceList.get(numberOfTab);
                                float sumPrice = actualPrice + priceFloat;
                                priceList.set(numberOfTab, sumPrice);
                            }
                        }
                      counter++;
                }
            }

            labelCounter = numberOfTab;
            
            switch(labelCounter)
            {
                case 0:
                {
                
                    shopOneLabel.setText(shopName);
                    totalPriceLabel1.setText(priceList.get(numberOfTab).toString());
                    shopOneLabel.setVisible(true);
                    totalPriceLabel1.setVisible(true);
                    break;
                }
                case 1:
                {
                    shopTwoLabel.setText(shopName);
                    totalPriceLabel2.setText(priceList.get(numberOfTab).toString());
                    shopTwoLabel.setVisible(true);
                    totalPriceLabel2.setVisible(true);
                    break;
                }
                case 2:
                {
                    shopThreeLabel.setText(shopName);
                    totalPriceLabel3.setText(priceList.get(numberOfTab).toString());
                    shopThreeLabel.setVisible(true);
                    totalPriceLabel3.setVisible(true);
                    break;
                }
                case 3: 
                {
                    shopFourLabel.setText(shopName);
                    totalPriceLabel4.setText(priceList.get(numberOfTab).toString());
                    shopFourLabel.setVisible(true);
                    totalPriceLabel4.setVisible(true);
                }
            }
            
            totalLabel.setText("Łączna kwota");
            totalLabel.setVisible(true);
            
            float totalPriceLabelFloat = 0;
            
            for(int counterTotal = 0; counterTotal < priceList.size(); counterTotal++) totalPriceLabelFloat += priceList.get(counterTotal);
            totalPriceLabel.setText("" + totalPriceLabelFloat);
            totalPriceLabel.setVisible(true);
        }
        else 
        {
            priceList.set(numberOfTab, (float)0.0);
            
            for(int counterElse = 0; counterElse < arrayLabelPrice.length; counterElse++)
            {
                if(getTabPane().getTitleAt(numberOfTab).equals(arrayLabelShop[counterElse].getText())) 
                {
                    arrayLabelPrice[counterElse].setText("0.0");
                    break;
                }
            }
            
            float totalPriceElseFloat = 0;
            
            for(int counterTotalElse = 0; counterTotalElse < priceList.size(); counterTotalElse++) totalPriceElseFloat += priceList.get(counterTotalElse).floatValue();
            totalPriceLabel.setText("" + totalPriceElseFloat);
            previousExistance = true;
            
        }
        
    }
    
  /*  void setPrice(int numberOfTab, BasketShopPanel panel)
    {
        
        int counter;
        String priceString;
        
      
        
        System.out.println("NUMER: " + numberOfTab);
        System.out.println("NUMER POP: " + previousTabNumber);

        if(panel.getProductsPrice() != null)
        {   
            if(panel.getProductsPrice().size() <= 1) counter = 0;
            else counter = panel.getProductsPrice().size() - 1;
        
            while(counter < panel.getProductsPrice().size())
            {
                
                    if(panel.getProductsPrice().get(counter).contains("PLN"))  priceString = panel.getProductsPrice().get(counter).replace("PLN", " ");
                    else 
                    {
                        priceString = panel.getProductsPrice().get(counter).replace("zł", " "); 
                        priceString = priceString.replace(",", "."); 
                    }
                    float priceFloat = Float.parseFloat(priceString);
                    
                    
                    if(numberOfTab == previousTabNumber && counter == 0) priceList.add(priceFloat);
                    else if(numberOfTab != previousTabNumber && counter == 0)
                    {
                        priceList.add(priceFloat);
                        previousTabNumber = numberOfTab;
                    } 
                    else if(counter != 0)
                    {
                        float actualPrice = priceList.get(numberOfTab).floatValue();
                        float sumPrice = actualPrice + priceFloat;
                        priceList.set(numberOfTab, sumPrice);
                    }
                counter++;
                
            }
            
            totalPriceLabel.setText("" + priceList.get(numberOfTab));
        }
        else totalPriceLabel.setText("0"); 
        
        for(int ij = 0; ij < priceList.size(); ij++)
        {
            System.out.println("PRICELIST: " + priceList.get(ij));
        }
      
       
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shopTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        browserButton = new javax.swing.JButton();
        totalPriceLabel1 = new javax.swing.JLabel();
        shopOneLabel = new javax.swing.JLabel();
        shopTwoLabel = new javax.swing.JLabel();
        totalPriceLabel2 = new javax.swing.JLabel();
        shopThreeLabel = new javax.swing.JLabel();
        totalPriceLabel3 = new javax.swing.JLabel();
        shopFourLabel = new javax.swing.JLabel();
        totalPriceLabel4 = new javax.swing.JLabel();
        totalPriceLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        compareButton = new javax.swing.JButton();

        deleteButton.setText("jButton1");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        browserButton.setText("jButton2");
        browserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browserButtonActionPerformed(evt);
            }
        });

        totalPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        compareButton.setText("jButton1");
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(shopFourLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(shopThreeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shopTwoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shopOneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(totalPriceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalPriceLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(totalPriceLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(totalPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(totalPriceLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(compareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(browserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalPriceLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shopOneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(shopTwoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(totalPriceLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shopThreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalPriceLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shopFourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalPriceLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(compareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(browserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shopTabbedPane)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(shopTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int numberOfTab = this.getTabPane().getSelectedIndex();
        if(basketTabPanel.get(numberOfTab).getTable().getSelectedRowCount() != 0)
        {
            basketTabPanel.get(numberOfTab).deleteElementFromTable();
            previousExistance = true;
            setPrice(this.getTabPane().getTitleAt(numberOfTab),numberOfTab,basketTabPanel.get(numberOfTab));
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void browserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browserButtonActionPerformed

            try{
                int numberOfTab = this.getTabPane().getSelectedIndex();
                int selectedRow = basketTabPanel.get(numberOfTab).getTable().getSelectedRow();
                browser.setShop(this.getTabPane().getTitleAt(numberOfTab));
                browser.setNameOfPart(basketTabPanel.get(numberOfTab).getTable().getModel().getValueAt(selectedRow, 0).toString());
               // browser.setCategory(this.mainPanel.);
                browser.setHTML();
                System.out.println("xdxd  " + mainPanel.getBasketFrame().getResearcher().getHTML());
                System.out.println("lololol" + numberOfTab);
                System.out.println("asfdsad" + this.getTabPane().getTitleAt(numberOfTab));
                browser.openBrowser();
            }catch(IndexOutOfBoundsException ex)
            {
                JOptionPane.showMessageDialog(null, "Wybierz przedmiot!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_browserButtonActionPerformed

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compareButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browserButton;
    private javax.swing.JButton compareButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel shopFourLabel;
    private javax.swing.JLabel shopOneLabel;
    private javax.swing.JTabbedPane shopTabbedPane;
    private javax.swing.JLabel shopThreeLabel;
    private javax.swing.JLabel shopTwoLabel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel totalPriceLabel1;
    private javax.swing.JLabel totalPriceLabel2;
    private javax.swing.JLabel totalPriceLabel3;
    private javax.swing.JLabel totalPriceLabel4;
    // End of variables declaration//GEN-END:variables
}
