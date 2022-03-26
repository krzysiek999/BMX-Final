/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import frame.MainFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author sluja
 */
public class UserPanel extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form UserPanel
     */
    MainFrame frame;
    BoxLayout layout;
    String resourceLanguage;
    
    ResourceBundle languageChoice;
    
    JToggleButton userButton;
    JButton purchaseBasket;
    JButton settingsButton;
    JButton exitButton;
    JButton searchButton;
    
    JPanel buttonPanel = new JPanel();
    MainPanel mainPanel;
    BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
    
   
    
    public UserPanel(MainFrame frame) {
        
        this.frame = frame;
       
        this.resourceLanguage = "properties/" + frame.getLanguage();
        languageChoice = ResourceBundle.getBundle(this.resourceLanguage); 
        userButton = new JToggleButton(languageChoice.getString("userButton"));
        purchaseBasket = new JButton(languageChoice.getString("purchaseBasket"));
        settingsButton = new JButton(languageChoice.getString("settings"));
        exitButton = new JButton(languageChoice.getString("exit"));
        searchButton = new JButton(languageChoice.getString("search"));
        
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(100, this.frame.getAppHeight()));
        addComponents();
    }
    
    private void addComponents()
    {
        userButton.setPreferredSize(new Dimension(80,20));
        purchaseBasket.setPreferredSize(new Dimension(80,20));
        settingsButton.setPreferredSize(new Dimension(80,20));
        exitButton.setPreferredSize(new Dimension(80,20));
        searchButton.setPreferredSize(new Dimension(80,20));
        
        this.add(Box.createVerticalStrut(10));
        this.add(userButton);
        this.add(Box.createVerticalStrut(10));
        this.add(purchaseBasket);
        this.add(Box.createVerticalStrut(10));
        this.add(searchButton);
        this.add(Box.createVerticalStrut(10));
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(10));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
        
        
        userButton.addActionListener(this);
        exitButton.addActionListener(this);
        searchButton.addActionListener(this);
        purchaseBasket.addActionListener(this);
        setButtonsVisible(false);
                
    }
    
    private void setButtonsVisible(boolean flag)
    {
        purchaseBasket.setVisible(flag);
        settingsButton.setVisible(flag);
        exitButton.setVisible(flag);
        searchButton.setVisible(flag);
    }
    
    public void setMainPanel(MainPanel panel)
    {
        this.mainPanel = panel;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        
    if(e.getSource() == userButton) 
    {
        if (userButton.isSelected()) setButtonsVisible(true);
        else setButtonsVisible(false);
    }
    
    if(e.getSource() == exitButton) System.exit(0);
    
    if(e.getSource() == searchButton)
    { 
        if(frame.getMainPanel().getBasketFrame().isVisible())  frame.getMainPanel().getBasketFrame().getPanel().getCompareButton().setEnabled(true);
        
        frame.getMainPanel().setVisible(false);
        frame.setComparisonVisible();
        frame.revalidate();
        frame.repaint();
        
       
        
    }
    
    if(e.getSource() == purchaseBasket) 
    {
        if(frame.getMainPanel().getBasketFrame().isVisible()) frame.getMainPanel().getBasketFrame().setVisible(false);
        else frame.getMainPanel().getBasketFrame().setVisible(true);
        
        frame.getMainPanel().getBasketFrame().getPanel().getCompareButton().setEnabled(false);
    }
    
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
