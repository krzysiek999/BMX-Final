/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import frame.MainFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class SettingsPanel extends javax.swing.JPanel{

//Declaring variables
    private MainFrame frame;
    private String language;
    //ResourceBundle resource;
    
    private int selectedLanguage = 0;

    private String[] countryList  = {"Polish",
                                     "English",
                                     "German"};
    private String actualLanguage, changedLanguage;
    
    private Box box = new Box(BoxLayout.Y_AXIS);
    
    private JPanel settingPanel = new JPanel();
    private ButtonPanel buttonPanel;
    private LanguagePanel languagePanel;
    private ColorChooserPanel colorPanel;
    private Color backgroundColor;
    private Color previousColor;
    private boolean updateLanguage = false;

    BoxLayout layout = new BoxLayout(settingPanel, BoxLayout.Y_AXIS);
            
    public SettingsPanel(MainFrame frame) {
        
        this.frame = frame;
    //Initializing application language
        this.language = "properties/" + frame.getLanguage();   
    //Setting size of settings' panel
        this.setPreferredSize(new Dimension(frame.getElementOneWidth(),frame.getElementOneHeight()));
    //Setting variable --> it is used to change language    
        actualLanguage = frame.getLanguage();
        setSettingPanel();
    }

    
    private void setSettingPanel()
    {       
    //Setting actual color of app --> used to retrieve original color of app
        setPreviousColor(frame.getMainPanel().getBackground());
        buttonPanel = new ButtonPanel();
        languagePanel = new LanguagePanel(actualLanguage);
        colorPanel = new ColorChooserPanel(this);
        
    
        box.add(languagePanel);
        box.createVerticalStrut(15);
        box.add(colorPanel);
        box.createVerticalStrut(15);
        box.add(buttonPanel);
        
        this.add(box);
    }
    
    public void setAppBackgroundColor(Color color)
    {
        this.backgroundColor = color;
    }
    
    public Color getAppBackgroundColor()
    {
        return this.backgroundColor;
    }
    
    private void setPreviousColor(Color color)
    {
        this.previousColor = color;
    }
    
    public Color getPreviousColor()
    {
        return this.previousColor;
    }
    
    public MainFrame getFrame()
    {
        return this.frame;
    }
    
    public void setButtonText()
    {
        this.buttonPanel.setButtonText();
        this.colorPanel.setButtonText();
        this.languagePanel.setButtonText();
        this.repaint();
    }
    
    public void setActualColor(Color color)
    {
        this.colorPanel.setActualColor(color);
    }

    class ColorChooserPanel extends JPanel implements ActionListener
    {
        private final JColorChooser colorChooser = new JColorChooser();
        private JButton colorButton = new JButton(frame.getResourceBundle().getString("chooseColor"));
        private JLabel backgroundColorLabel = new JLabel(frame.getResourceBundle().getString("bColor"));
        private JTextArea colorLabel = new JTextArea(4,8);
        private JScrollPane colorScrollPane = new JScrollPane(colorLabel);
        private SettingsPanel mainPanel;
        private JScrollPane scrollPane;
        private JButton defaultColorButton = new JButton(frame.getResourceBundle().getString("defaultColorButton"));
        
        FlowLayout layout = new FlowLayout(80,20,20);
        public ColorChooserPanel(SettingsPanel mainPanel) 
        {
            this.mainPanel = mainPanel;
            this.setLayout(layout);
            colorButton.addActionListener(this);
            defaultColorButton.addActionListener(this);
            
            colorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            colorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            colorScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            
            colorLabel.setPreferredSize(new Dimension(60,60));
            colorLabel.setMinimumSize(new Dimension(60,60));
            colorLabel.setBackground(frame.getPanelBackground());
            
            defaultColorButton.setVisible(false);
            
            this.add(backgroundColorLabel);
            this.add(colorScrollPane);
            this.add(colorButton);
            this.add(defaultColorButton);
        }
        
        public void setActualColor(Color color)
        {
            this.colorLabel.setBackground(color);
            this.defaultColorButton.setVisible(true);
        }
        
        private void changeConfigColor(Color color)
        {
            
            getFrame().getFilesHandler().writeToFile("bColor:" + Integer.toString(color.getRGB())+ "\n",0);   
        }
                
        public void setButtonText()
        {
            this.backgroundColorLabel.setText(frame.getResourceBundle().getString("bColor"));
            this.colorButton.setText(frame.getResourceBundle().getString("chooseColor"));
            this.defaultColorButton.setText(frame.getResourceBundle().getString("defaultColorButton"));
            this.repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource().equals(colorButton))
            {
                Color initialcolor=Color.RED;    
                Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);  
                colorLabel.setBackground(color);
                setAppBackgroundColor(color);
                defaultColorButton.setVisible(true);
                frame.setPanelBackground(color);
                changeConfigColor(color);
            }
            else
            {
                Color defaultColor = UIManager.getColor("Panel.background");
                colorLabel.setBackground(defaultColor);
                setAppBackgroundColor(defaultColor);
                defaultColorButton.setVisible(false);
                frame.setPanelBackground(defaultColor);
                changeConfigColor(defaultColor);
            }
        }
        
    }
    
    class LanguagePanel extends JPanel implements ItemListener
    {       
        private JComboBox languageComboBox = new JComboBox();
        private FlowLayout layout = new FlowLayout(60, 30, 30);
        private JLabel languageLabel = new JLabel(frame.getResourceBundle().getString("language")); 

        public LanguagePanel(String language)
        {            
            languageComboBox.addItem(countryList[0]);
            languageComboBox.addItem(countryList[1]);
            languageComboBox.addItem(countryList[2]);
            languageComboBox.getModel().setSelectedItem(language);
            languageComboBox.addItemListener(this);
            setFeature();
            this.add(languageComboBox);
        }
        
        private void setFeature()
        {
            this.add(languageLabel);
        }
        
        public void setButtonText()
        {
            languageLabel.setText(frame.getResourceBundle().getString("language"));
            this.repaint();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
            updateLanguage = true;
            changedLanguage = languageComboBox.getSelectedItem().toString();
            }
        }
    }
    
    class ButtonPanel extends JPanel implements ActionListener
    {
        String language = "properties/" + frame.getLanguage();
        //ResourceBundle resource = ResourceBundle.getBundle(language);
        
        JButton eraseButton = new JButton(frame.getResourceBundle().getString("erase"));
        JButton acceptButton = new JButton(frame.getResourceBundle().getString("accept"));
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT, 20, 40);

        public ButtonPanel() {
                
            eraseButton.addActionListener(this);
            acceptButton.addActionListener(this);
            
            this.setLayout(layout);
            this.add(eraseButton);
            this.add(acceptButton);
            
        }
        
        public void setButtonText()
        {
            this.acceptButton.setText(frame.getResourceBundle().getString("accept"));
            this.eraseButton.setText(frame.getResourceBundle().getString("erase"));
            
            this.repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == acceptButton)
            {
                if(updateLanguage) 
                {
                    frame.changeLanguage(changedLanguage);
                    updateLanguage = false;
                }
            }
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

        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(712, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
