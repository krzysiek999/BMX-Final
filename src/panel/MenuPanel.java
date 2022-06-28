/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import frame.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author krzys
 */
public class MenuPanel extends JMenu implements ActionListener
{

//Declaring variables
    private String nameOfMenu;
    
    JMenuItem basketButton, setMainPanelVisibleButton, settingsButton, comparisonButton, exitButton;
    MainFrame frame;
    JMenu menu = new JMenu();

    
    public MenuPanel(String name, MainFrame frame) 
    {
        super(name);
        this.frame = frame;
        this.nameOfMenu = name;
        addMenuElement();
    }
    
    
    public void addMenuElement()
    {
        basketButton = new JMenuItem(this.frame.getResourceBundle().getString("purchaseBasket"));
        settingsButton = new JMenuItem(this.frame.getResourceBundle().getString("settings"));
        comparisonButton = new JMenuItem(this.frame.getResourceBundle().getString("compareButton"));
        exitButton = new JMenuItem(this.frame.getResourceBundle().getString("exit"));
        setMainPanelVisibleButton = new JMenuItem(this.frame.getResourceBundle().getString("mainPanel"));
        
        basketButton.addActionListener(this);
        settingsButton.addActionListener(this);
        comparisonButton.addActionListener(this);
        exitButton.addActionListener(this);
        setMainPanelVisibleButton.addActionListener(this);
        
        this.add(setMainPanelVisibleButton);
        this.addSeparator();
        this.add(basketButton);
        this.addSeparator();
        this.add(settingsButton);
        this.addSeparator();
        this.add(comparisonButton);
        this.addSeparator();
        this.add(exitButton);
    }
    
    
    
    public void setButtonText()
    {
        this.basketButton.setText(this.frame.getResourceBundle().getString("purchaseBasket"));
        this.settingsButton.setText(this.frame.getResourceBundle().getString("settings"));
        this.comparisonButton.setText(this.frame.getResourceBundle().getString("compareButton"));
        this.exitButton.setText(this.frame.getResourceBundle().getString("exit"));
        this.setMainPanelVisibleButton.setText(this.frame.getResourceBundle().getString("mainPanel"));
        this.setText(this.frame.getResourceBundle().getString("userButton"));
        this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       String actionName = ((JButton)e.getSource()).getText();
        
        if(e.getSource() == basketButton || actionName.equals(frame.getResourceBundle().getString("basket")))
        {
            if(this.frame.getMainPanel().getBasketFrame().isVisible()) this.frame.getMainPanel().getBasketFrame().setVisible(false);
            else this.frame.getMainPanel().getBasketFrame().setVisible(true);
        }
        else if((e.getSource() == setMainPanelVisibleButton || actionName.equals(frame.getResourceBundle().getString("mainPanel")))&& !this.frame.getMainPanel().isVisible() ) 
        {
            this.frame.setActivePanel(this.frame.getMainLabel());
            this.frame.setCompareButtonVisible(false);
        }
        else if(e.getSource() == settingsButton || actionName.equals(frame.getResourceBundle().getString("settings")))
        {
            this.frame.setActivePanel(this.frame.getSettingsLabel());
            this.frame.setCompareButtonVisible(false);
        }
        else if(e.getSource() == comparisonButton || actionName.equals(frame.getResourceBundle().getString("compareButton")))
        {
            this.frame.setActivePanel(this.frame.getComparisonLabel());
            this.frame.setCompareButtonVisible(true);
        }
        else if(e.getSource() == exitButton || actionName.equals(frame.getResourceBundle().getString("exit"))) System.exit(0);  
        
    } 
}
