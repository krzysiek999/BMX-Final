/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frame;


import classes.FilesHandler;
import classes.PropertyReader;
import classes.ShopResearcher;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ResourceBundle;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import panel.*;




/**
 *
 * @author sluja
 */
public class MainFrame extends javax.swing.JFrame {
    
    final String MAIN_PANEL = "Main Panel", SETTING_PANEL = "Setting Panel", COMPARISON_PANEL = "Comparison Panel", LOADING_PANEL = "Loading Panel", PART_PANEL = "Part Panel";
    
    private boolean tableExist = false, productTableExist = false;
    
    int WIDTH = 1300, HEIGHT = 1000, FIRST_ELEMENT_WIDTH = 900, FIRST_ELEMENT_HEIGHT = 900, SECOND_ELEMENT_WIDTH = 300, SECOND_ELEMENT_HEIGTH = 900, LABEL_SIZE_WIDTH = 0, LABEL_SIZE_HEIGHT = 0;
    final int HEIGHT_REFERENCE = 1080, WIDTH_REFERENCE = 1920;
    
    int xPosition = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - WIDTH/2;
    int yPosition = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - HEIGHT/2;
    final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().height, SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
    String language, compareShopName;
    
    //ResourceBundle resource, propertyReader;
    PropertyReader propertyReader = PropertyReader.getInstance();
    //ResourceBundle resource
    
    BorderLayout mainFrameLayout_0ld = new BorderLayout();
    FlowLayout mainFrameLayout = new FlowLayout(FlowLayout.LEADING,5,5);
    CardLayout elementFirstLayout = new CardLayout(5, 5);
    SearchPanel searchPanel;
    ComparisonPanel comparisonPanel;
    SettingsPanel settingsPanel;
    PartPanel partPanel;
    
    
    MainPanel mainPanel;
    JPanel panel = new JPanel();
    JPanel centerPanel = new JPanel();
    FeaturePanel featurePanel;
    
    JMenuBar menuBar;

    MenuPanel menuPanel;
    
    double widthDifference, heightDifference;
    
    private LoadingPanel loadingPanel;
    
    
    String activePanel;
    
    private Color actualColor;
    
    private FilesHandler filesHandler;
    
    public MainFrame(String language)
    {
        setLanguage(language);
        setMenu();
        System.out.println("WYS: " + SCREEN_HEIGHT + " , SZER: " + SCREEN_WIDTH);
        setAppSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        xPosition = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - WIDTH/2;
        yPosition = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - HEIGHT/2;
        featurePanel = new FeaturePanel(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(xPosition,yPosition,WIDTH,HEIGHT);
        this.panel.setLayout(mainFrameLayout);
        centerPanel.setLayout(elementFirstLayout);
        panel.add(centerPanel);
        panel.add(featurePanel);
        this.add(panel);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setResizable(false);
    }
    
    private void setAppSize(int width, int height){
        
        heightDifference = height / (HEIGHT_REFERENCE * 1.0); 
        widthDifference = width / (WIDTH_REFERENCE * 1.0);
        
        System.out.println("DIFFERENCES: " + heightDifference + " , " + widthDifference);
        
        this.WIDTH = (int)(widthDifference * 1300);
        this.HEIGHT = (int)(heightDifference * 1000);
        this.FIRST_ELEMENT_HEIGHT = (int)(heightDifference * 900);
        this.SECOND_ELEMENT_HEIGTH = (int)(heightDifference * 900);
        this.LABEL_SIZE_HEIGHT = (int)(heightDifference * 870);// - 200;
        this.FIRST_ELEMENT_WIDTH = (int)(widthDifference * 900);
        this.SECOND_ELEMENT_WIDTH = (int)(widthDifference * 300);
        this.LABEL_SIZE_WIDTH = (int)(widthDifference * 720);// - 120;
        
        System.out.println("HEIGHT: " + heightDifference + " , WIDTH: " + widthDifference);
        
        centerPanel.setPreferredSize(new Dimension(FIRST_ELEMENT_WIDTH, FIRST_ELEMENT_HEIGHT));
        System.out.println("WYMIARY: " + WIDTH + " , " + HEIGHT + " , " + FIRST_ELEMENT_HEIGHT + " , " + FIRST_ELEMENT_WIDTH + " , " + SECOND_ELEMENT_HEIGTH + " , " + SECOND_ELEMENT_WIDTH); //+ "");
    }
    
    public int getLabelWidth(){
        return this.LABEL_SIZE_WIDTH;
    }
    
      public int getLabelHeight(){
        return this.LABEL_SIZE_HEIGHT;
    }
    
    public void setProductTableExist(boolean value){
        this.productTableExist = value;
    }
    
    public void setComparisonShop(String value){
        this.compareShopName = value;
    }
    
    public String getComparisonShop(){
        return this.compareShopName;
    }
    
   /* public void setPropertyReader(String filename) {
        //this.propertyReader = ResourceBundle.getBundle("properties/" +filename);
        this.resource.setPropertyFilename("properties/" +filename);
        this.resource.setConnection();
    }*/
    
    public FeaturePanel getFeaturePanel(){
        return this.featurePanel;
    }
    
    public void setTableExist(boolean value){
        this.tableExist = value;
    }
    
    public PartPanel getPartPanel(){
        return this.partPanel;
    }
            
    
    public int getElementTwoWidth(){
        return this.SECOND_ELEMENT_WIDTH;
    }
    
    public int getElementTwoHeigth(){
        return this.SECOND_ELEMENT_HEIGTH;
    }
    
    public boolean productTableExists(){
        return this.productTableExist;
    }
    
    public boolean tableExists(){
        return this.tableExist;
    }
    
    public void setMainFeature()
    {
        mainPanel = new MainPanel(this, this.tableExists());
        settingsPanel = new SettingsPanel(this);
        comparisonPanel = new ComparisonPanel(this);
        loadingPanel = new LoadingPanel(this);
        
        mainPanel.setLabelSize(400, 400);
        mainPanel.setButtonsPosition(widthDifference, heightDifference);
        
        this.centerPanel.add(mainPanel, MAIN_PANEL);
        this.centerPanel.add(settingsPanel, SETTING_PANEL);
        this.centerPanel.add(comparisonPanel, COMPARISON_PANEL);
        this.centerPanel.add(loadingPanel, LOADING_PANEL);
        
        this.setActivePanel(MAIN_PANEL);                
    }
    
    public void setPartPanel(ShopResearcher researcher, boolean firstInitialized)
    {
        this.partPanel = new PartPanel(researcher, this, firstInitialized);
        this.centerPanel.add(partPanel, PART_PANEL);
    }
    
    public String getPartLabel()
    {
        return this.PART_PANEL;
    }
    
    public void saveTableExistConfig(String value)
    {
        //StringBuilder modValue = new StringBuilder("tExist:");
        //this.filesHandler.writeToFile((modValue.append(value)).toString(), 1);
        this.propertyReader.saveProperty("tExist", value);
    }
    
    public void setMenu()
    {
        menuPanel = new MenuPanel(propertyReader.getProperty("userButton"),this);
        menuBar = new JMenuBar();
        menuBar.add(menuPanel);
        this.setJMenuBar(menuBar);
        
    }
    
    public void setPanelBackground(Color color)
    {
        this.mainPanel.setBackground(color);
        this.comparisonPanel.setBackground(color);
        this.actualColor = color;
        this.settingsPanel.setActualColor(color);  
    }
    
    public Color getPanelBackground()
    {
        return this.actualColor;
    }
    
    public void setActivePanel(String name)
    {
        this.getElementFirstLayout().show(centerPanel, name);
        
        if(name.equals(PART_PANEL)) this.getFeaturePanel().setElementsEnabled(true);
        else this.getFeaturePanel().setElementsEnabled(false);
        
        if(name.equals(MAIN_PANEL)) this.getFeaturePanel().setChoiceEnabled(true);
        else this.getFeaturePanel().setChoiceEnabled(false);
        
        this.activePanel = name;
    }
      
    public void changeLanguage(String language)
    {
        setLanguage(language);
        this.setButtonText();
        this.getFeaturePanel().setButtonsText();
    }
    
    public void startSearching()
    {
        
    }

    public void setCompareButtonVisible(boolean value)
    {
        this.getMainPanel().getBasketFrame().getPanel().getCompareButton().setEnabled(value);
        this.getBasketFrame().repaint();
    }
    
    public void setFilesHandler(FilesHandler filesHandler)
    {
        this.filesHandler = filesHandler;
    }
    
    public FilesHandler getFilesHandler()
    {
        return this.filesHandler;
    }
    
    //SETTERS
    public void setPropertyReader(String filename)
    {
        //this.resource = ResourceBundle.getBundle("properties/" + getLanguage());
        this.propertyReader.setPropertyFilename("properties/" + filename + ".properties");
        this.propertyReader.setConnection();
    }
    
    public void setLanguage(String language)
    {
        this.language = language;
        setPropertyReader(getLanguage());
    }
    
    //GETTERS
    public String getActivePanel()
    {
        return this.activePanel;
    }
    
    public ComparisonPanel getComparisonPanel()
    {
        return this.comparisonPanel;
    }
 
    public JPanel getFramePanel()
    {
        return this.panel;
    }
    
    public MainPanel getMainPanel()
    {
        return this.mainPanel;
    }
    
    
    public int getAppWidth()
    {
        return this.WIDTH;
    }
    public int getAppHeight()
    {
        return this.HEIGHT;
    }
    
    public int getElementOneWidth()
    {
        return this.FIRST_ELEMENT_WIDTH;
    }
    
    public int getElementOneHeight()
    {
        return this.FIRST_ELEMENT_HEIGHT;
    }
    
    public String getComparisonLabel()
    {
        return this.COMPARISON_PANEL;
    }
    
    public String getMainLabel()
    {
        return this.MAIN_PANEL;
    }
    
    public String getSettingsLabel()
    {
        return this.SETTING_PANEL;
    }
    


    public String getLanguage()
    {
        return this.language;
    }
    
    public CardLayout getElementFirstLayout()
    {
        return this.elementFirstLayout;
    }
    
    public FlowLayout getMainFrameLayout()
    {
        return this.mainFrameLayout;
    }
    
    public PropertyReader getPropertyReader()
    {
        return this.propertyReader;
    }
    
    public BasketMainPanel getBasketPanel()
    {
        return this.getMainPanel().getBasketFrame().getPanel();
    }
    
    public BasketMainFrame getBasketFrame()
    {
        return this.getMainPanel().getBasketFrame();
    }
    
    public SettingsPanel getSettingsPanel()
    {
        return this.settingsPanel;
    }
    
    public void setButtonText()
    {
        this.menuPanel.setButtonText();
        this.getBasketFrame().setButtonText();
        this.getComparisonPanel().setButtonText();
        this.getSettingsPanel().setButtonText();
        this.getMainPanel().setButtonText();
        //if(this.getMainPanel().getSearchState()) this.getPartFrame().setButtonText();
        this.repaint();
    }
    
    public MenuPanel getMenuPanel()
    {
        return this.menuPanel;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents





    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
     /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
