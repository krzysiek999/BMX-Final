/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import classes.ProductDatabaseHandler;
import frame.MainFrame;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author krzys
 */
public class FeaturePanel extends javax.swing.JPanel implements ListSelectionListener{

 
    private int width,heigth;
    private MainFrame mainFrame;
    private ProductDatabaseHandler productDatabaseHandler;
    
    Pattern pattern = Pattern.compile("[-]?[0-9]*\\.,?[0-9]+");
    Matcher matcher;
    
    double newPrice = 0, oldPrice = 0;
    String newPriceString = "";
    int amountOfProduct = 0, discount = 0;
    
    public FeaturePanel(MainFrame frame) {
        initComponents();
        this.mainFrame = frame;
        this.width = this.mainFrame.getElementTwoWidth();
        this.heigth = this.mainFrame.getElementTwoHeigth();
        this.setPreferredSize(new Dimension(width, heigth));
        
        setButtonsText();
        
        partsButton.addActionListener(mainFrame.getMenuPanel());
        exitButton.addActionListener(mainFrame.getMenuPanel());
        settingsButton.addActionListener(mainFrame.getMenuPanel());
        basketButton.addActionListener(mainFrame.getMenuPanel());
        comparisonButton.addActionListener(mainFrame.getMenuPanel());
        
        addBasketButton.setEnabled(false);
    }
    
    public void setButtonsText(){
        exitButton.setText(mainFrame.getPropertyReader().getProperty("exit"));
        settingsButton.setText(mainFrame.getPropertyReader().getProperty("settings"));
        basketButton.setText(mainFrame.getPropertyReader().getProperty("basket"));
        comparisonButton.setText(mainFrame.getPropertyReader().getProperty("compareButton"));
        partsButton.setText(mainFrame.getPropertyReader().getProperty("mainPanel"));
        //accessoriesButton.setText(mainFrame.getResourceBundle().getProperty("accessories"));
        discountSpinnerLabel.setText(mainFrame.getPropertyReader().getProperty("discountLabel"));
        menuLabel.setText(mainFrame.getPropertyReader().getProperty("menuLabel"));
        tabsLabel.setText(mainFrame.getPropertyReader().getProperty("tabsLabel"));
        acceptButton.setText(mainFrame.getPropertyReader().getProperty("accept"));
        backButton.setText(mainFrame.getPropertyReader().getProperty("erase"));
        addBasketButton.setText(mainFrame.getPropertyReader().getProperty("addBasket"));
        shopLabel.setText(mainFrame.getPropertyReader().getProperty("shop"));
        allShopsCheckBox.setText(mainFrame.getPropertyReader().getProperty("allShops"));
    }
    
    public void discountApply(){
                 amountOfProduct = this.mainFrame.getPartPanel().getJTable().getRowCount();
        
        for(int counter=0; counter < amountOfProduct; counter++){
            matcher = pattern.matcher((String)mainFrame.getMainPanel().getResearcher(this.mainFrame.getPartPanel().getResearcher().getShopName()).getProductDatabaseHandler().getPrice(counter));
            while(matcher.find()) oldPrice = Float.parseFloat(matcher.group());
            discount = 100 - (int)discountSpinner.getModel().getValue();
            newPrice = oldPrice * ((float)discount/100.0);
            newPriceString = "'" +  String.format("%.2f", newPrice) + "'";    
            mainFrame.getPartPanel().getJTable().getModel().setValueAt("" + newPrice,counter, 2);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }
    
    public JButton getAddBasketButton(){
        return this.addBasketButton;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        partsButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        basketButton = new javax.swing.JButton();
        discountSpinnerLabel = new javax.swing.JLabel();
        discountSpinner = new javax.swing.JSpinner();
        tabsLabel = new javax.swing.JLabel();
        menuLabel = new javax.swing.JLabel();
        comparisonButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        acceptButton = new javax.swing.JButton();
        addBasketButton = new javax.swing.JButton();
        manyfestCheckBox = new javax.swing.JCheckBox();
        alldayCheckBox = new javax.swing.JCheckBox();
        bmxlifeCheckBox = new javax.swing.JCheckBox();
        shopLabel = new javax.swing.JLabel();
        avebmxCheckBox = new javax.swing.JCheckBox();
        allShopsCheckBox = new javax.swing.JCheckBox();

        partsButton.setText("jButton1");

        settingsButton.setText("jButton1");

        exitButton.setText("jButton1");

        basketButton.setText("jButton1");

        discountSpinnerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        discountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        tabsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        menuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        comparisonButton.setText("jButton1");

        backButton.setText("jButton1");
        buttonGroup1.add(backButton);

        acceptButton.setText("jButton1");
        buttonGroup1.add(acceptButton);
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        addBasketButton.setText("jButton1");
        addBasketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBasketButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(manyfestCheckBox);
        manyfestCheckBox.setText("ManyfestBmx");
        manyfestCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manyfestCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup.add(alldayCheckBox);
        alldayCheckBox.setText("AlldayBmx");
        alldayCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alldayCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup.add(bmxlifeCheckBox);
        bmxlifeCheckBox.setText("BmxLife");
        bmxlifeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmxlifeCheckBoxActionPerformed(evt);
            }
        });

        shopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shopLabel.setText("Sklepy");

        buttonGroup.add(avebmxCheckBox);
        avebmxCheckBox.setText("AveBmx");
        avebmxCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avebmxCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup.add(allShopsCheckBox);
        allShopsCheckBox.setText("BmxLife");
        allShopsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allShopsCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(discountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(shopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bmxlifeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(manyfestCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(45, 45, 45)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(avebmxCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(alldayCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comparisonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tabsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(discountSpinnerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(basketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(allShopsCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(partsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(discountSpinnerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(discountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tabsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(partsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(basketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(addBasketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comparisonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(shopLabel)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manyfestCheckBox)
                    .addComponent(avebmxCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bmxlifeCheckBox)
                    .addComponent(alldayCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(allShopsCheckBox)
                .addContainerGap(184, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setElementsEnabled(boolean value){
        this.acceptButton.setEnabled(value);
        this.backButton.setEnabled(value);
        discountSpinner.setEnabled(value);
    }
    
    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        discountApply();
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void addBasketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBasketButtonActionPerformed
        mainFrame.getPartPanel().getAddBasketButton().doClick();
    }//GEN-LAST:event_addBasketButtonActionPerformed

    private void manyfestCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manyfestCheckBoxActionPerformed
        mainFrame.getMainPanel().setShop("manyfestbmx",3);
    }//GEN-LAST:event_manyfestCheckBoxActionPerformed

    private void alldayCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alldayCheckBoxActionPerformed
        mainFrame.getMainPanel().setShop("allday",4);
    }//GEN-LAST:event_alldayCheckBoxActionPerformed

    private void bmxlifeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmxlifeCheckBoxActionPerformed
        mainFrame.getMainPanel().setShop("bmxlife",1);
    }//GEN-LAST:event_bmxlifeCheckBoxActionPerformed

    private void avebmxCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avebmxCheckBoxActionPerformed
        mainFrame.getMainPanel().setShop("avebmx",2);
    }//GEN-LAST:event_avebmxCheckBoxActionPerformed

    private void allShopsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allShopsCheckBoxActionPerformed
        System.out.println("SCREEN SIZE: " + mainFrame.getToolkit().getScreenSize().height);
        System.out.println("SCREEN SIZE 2: " + mainFrame.getToolkit().getScreenSize().width);
    }//GEN-LAST:event_allShopsCheckBoxActionPerformed

    public JCheckBox getAvebmxBox(){
        return this.avebmxCheckBox;
    }
    
    public JCheckBox getBmxlifeBox(){
        return this.bmxlifeCheckBox;
    }
    
    public JCheckBox getManyfestbmxBox(){
        return this.manyfestCheckBox;
    }

    public JCheckBox getAlldayBox(){
        return this.alldayCheckBox;
    }
    
    public JCheckBox getAllShopsBox(){
        return this.allShopsCheckBox;
    }
    
    public void setChoiceEnabled(boolean value){
        this.avebmxCheckBox.setEnabled(value);
        this.bmxlifeCheckBox.setEnabled(value);
        this.manyfestCheckBox.setEnabled(value);
        this.alldayCheckBox.setEnabled(value);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JButton addBasketButton;
    private javax.swing.JCheckBox allShopsCheckBox;
    private javax.swing.JCheckBox alldayCheckBox;
    private javax.swing.JCheckBox avebmxCheckBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton basketButton;
    private javax.swing.JCheckBox bmxlifeCheckBox;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton comparisonButton;
    private javax.swing.JSpinner discountSpinner;
    private javax.swing.JLabel discountSpinnerLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JCheckBox manyfestCheckBox;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JButton partsButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JLabel shopLabel;
    private javax.swing.JLabel tabsLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        addBasketButton.setEnabled(true);
        //mainFrame.getPartPanel().getBasketButton().setEnabled(true);
    }
}
