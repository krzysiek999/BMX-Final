/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import classes.DatabaseHandler;
import classes.ImageHandler;
import classes.ShopResearcher;
import frame.BasketMainFrame;
import frame.MainFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 *
 * @author sluja
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    final String TABLE_NAME = "BasketTable";
    
    MainFrame frame;
    private String nameOfShop = "";
    private String nameOfPart = "";
    private int shopNumber = 0;
    
    private BasketMainFrame basketFrame;
    ShopResearcher shopResearcher = new ShopResearcher();
    private boolean partSearched = false;
    private boolean firstInitialized = true;
    
    //Initialize database handler
    private DatabaseHandler databaseHandler = new DatabaseHandler();
    
        ArrayList<ShopResearcher> usedShopArray = new ArrayList<ShopResearcher>();
    
    private int shopIndex = 0;
    private boolean partSelection = false;
    
    boolean notFirstSearch = false;
    
    ImageHandler imageHandler = new ImageHandler();
    
    public MainPanel( MainFrame frame, boolean tableExists) {
        
        this.frame = frame;
        this.setSize(frame.getElementOneWidth(), frame.getElementOneHeight());
        //databaseHandler.setMainPanel(this);
        basketFrame = new BasketMainFrame(this);
        if(!tableExists) databaseHandler.createTable(TABLE_NAME);
        databaseHandler.deleteAllElements(TABLE_NAME);
        initComponents(); 
        setButtonText();
    }
    
    public void setButtonsPosition(double widthDifference, double heightDifference){
        
        List<AbstractButton> buttonsList = Collections.list(buttonGroup1.getElements());
        
        for(int buttonCounter = 0; buttonCounter < buttonsList.size(); buttonCounter++){
            buttonsList.get(buttonCounter).setLocation((int)(buttonsList.get(buttonCounter).getLocation().x * widthDifference), (int)(buttonsList.get(buttonCounter).getLocation().y * heightDifference));
            buttonsList.get(buttonCounter).setSize((int)(buttonsList.get(buttonCounter).getSize().width * (widthDifference + 0.1)), (int)(buttonsList.get(buttonCounter).getSize().height * (heightDifference + 0.1)));
        }
    }
    
    public void setLabelSize(int width, int height){
         imageHandler.resizeJLabelIcon(frame.getLabelWidth(),frame.getLabelHeight(),jLabel3);
         this.revalidate();
         this.repaint();
         //jLabel3.setLocation(0, 0);
         jLabel3.setLocation(0,-80);
         barendsButton.setLocation(14, 24);
    }

    
    public String getTableName()
    {
        return this.TABLE_NAME;
    }
    
    public void setButtonText()
    {
        this.getFrame().setLanguage(this.getFrame().getLanguage());
        this.barendsButton.setText(this.getFrame().getPropertyReader().getProperty("barends"));
        this.barsButton.setText(this.getFrame().getPropertyReader().getProperty("bars"));
        this.stemsButton.setText(this.getFrame().getPropertyReader().getProperty("stems"));
        this.gripsButton.setText(this.getFrame().getPropertyReader().getProperty("grips"));
        this.steersButton.setText(this.getFrame().getPropertyReader().getProperty("steers"));
        this.forksButton.setText(this.getFrame().getPropertyReader().getProperty("forks"));
        this.tyresButton.setText(this.getFrame().getPropertyReader().getProperty("tires"));
        this.hubsButton.setText(this.getFrame().getPropertyReader().getProperty("hubs"));
        this.spokesButton.setText(this.getFrame().getPropertyReader().getProperty("spokes"));
        this.hubguardsButton.setText(this.getFrame().getPropertyReader().getProperty("hubguards"));
        this.cranksButton.setText(this.getFrame().getPropertyReader().getProperty("cranks"));
        this.chainButton.setText(this.getFrame().getPropertyReader().getProperty("chains"));
        this.chainwheelsButton.setText(this.getFrame().getPropertyReader().getProperty("sprockets"));
        this.pedalsButton.setText(this.getFrame().getPropertyReader().getProperty("pedals"));
        this.seatpostsButton.setText(this.getFrame().getPropertyReader().getProperty("posts"));
        this.seatsButton.setText(this.getFrame().getPropertyReader().getProperty("seats"));
        this.supportsButton.setText(this.getFrame().getPropertyReader().getProperty("supports"));
        this.pegsButton.setText(this.getFrame().getPropertyReader().getProperty("pegs"));
        this.rimsButton.setText(this.getFrame().getPropertyReader().getProperty("rims"));
        this.framesButton.setText(this.getFrame().getPropertyReader().getProperty("frames"));
        this.helmetButton.setText(this.getFrame().getPropertyReader().getProperty("helmets"));
        this.padsButton.setText(this.getFrame().getPropertyReader().getProperty("pads"));
        repaint();
    }
    
    public DatabaseHandler getDatabaseHandler()
    {
        return this.databaseHandler;
    }

    public MainFrame getFrame()
    {
        return this.frame;
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
        jRadioButton1 = new javax.swing.JRadioButton();
        barsButton = new javax.swing.JButton();
        hubguardsButton = new javax.swing.JButton();
        chainwheelsButton = new javax.swing.JButton();
        chainButton = new javax.swing.JButton();
        steersButton = new javax.swing.JButton();
        pegsButton = new javax.swing.JButton();
        barendsButton = new javax.swing.JButton();
        forksButton = new javax.swing.JButton();
        hubsButton = new javax.swing.JButton();
        spokesButton = new javax.swing.JButton();
        seatsButton = new javax.swing.JButton();
        stemsButton = new javax.swing.JButton();
        supportsButton = new javax.swing.JButton();
        cranksButton = new javax.swing.JButton();
        rimsButton = new javax.swing.JButton();
        tyresButton = new javax.swing.JButton();
        seatpostsButton = new javax.swing.JButton();
        pedalsButton = new javax.swing.JButton();
        framesButton = new javax.swing.JButton();
        gripsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        helmetButton = new javax.swing.JButton();
        padsButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setLayout(null);

        barsButton.setText("KIEROWNICE");
        buttonGroup1.add(barsButton);
        barsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barsButtonActionPerformed(evt);
            }
        });
        add(barsButton);
        barsButton.setBounds(420, 70, 140, 30);

        hubguardsButton.setText("HUBGUARDY");
        buttonGroup1.add(hubguardsButton);
        hubguardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hubguardsButtonActionPerformed(evt);
            }
        });
        add(hubguardsButton);
        hubguardsButton.setBounds(510, 640, 130, 30);

        chainwheelsButton.setText("ZĘBATKI");
        buttonGroup1.add(chainwheelsButton);
        chainwheelsButton.setDefaultCapable(false);
        chainwheelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chainwheelsButtonActionPerformed(evt);
            }
        });
        add(chainwheelsButton);
        chainwheelsButton.setBounds(180, 510, 120, 30);

        chainButton.setText("ŁAŃCUCHY");
        buttonGroup1.add(chainButton);
        chainButton.setDefaultCapable(false);
        chainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chainButtonActionPerformed(evt);
            }
        });
        add(chainButton);
        chainButton.setBounds(120, 570, 110, 30);

        steersButton.setText("STERY");
        buttonGroup1.add(steersButton);
        steersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                steersButtonActionPerformed(evt);
            }
        });
        add(steersButton);
        steersButton.setBounds(480, 240, 90, 30);

        pegsButton.setText("PEGI");
        pegsButton.setBorderPainted(false);
        buttonGroup1.add(pegsButton);
        pegsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegsButtonActionPerformed(evt);
            }
        });
        add(pegsButton);
        pegsButton.setBounds(0, 580, 100, 30);

        barendsButton.setText("BARENDY");
        buttonGroup1.add(barendsButton);
        barendsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barendsButtonActionPerformed(evt);
            }
        });
        add(barendsButton);
        barendsButton.setBounds(80, 10, 120, 30);

        forksButton.setText("WIDELCE");
        buttonGroup1.add(forksButton);
        forksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forksButtonActionPerformed(evt);
            }
        });
        add(forksButton);
        forksButton.setBounds(430, 510, 100, 30);

        hubsButton.setText("PIASTY");
        buttonGroup1.add(hubsButton);
        hubsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hubsButtonActionPerformed(evt);
            }
        });
        add(hubsButton);
        hubsButton.setBounds(540, 590, 100, 30);

        spokesButton.setText("SZPRYCHY");
        buttonGroup1.add(spokesButton);
        spokesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spokesButtonActionPerformed(evt);
            }
        });
        add(spokesButton);
        spokesButton.setBounds(500, 710, 120, 30);

        seatsButton.setText("SIODEŁKO");
        buttonGroup1.add(seatsButton);
        seatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatsButtonActionPerformed(evt);
            }
        });
        add(seatsButton);
        seatsButton.setBounds(200, 290, 120, 30);

        stemsButton.setText("MOSTKI");
        buttonGroup1.add(stemsButton);
        stemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stemsButtonActionPerformed(evt);
            }
        });
        add(stemsButton);
        stemsButton.setBounds(460, 170, 100, 30);

        supportsButton.setText("SUPPORTY");
        buttonGroup1.add(supportsButton);
        supportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supportsButtonActionPerformed(evt);
            }
        });
        add(supportsButton);
        supportsButton.setBounds(270, 560, 130, 30);

        cranksButton.setText("KORBY");
        buttonGroup1.add(cranksButton);
        cranksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cranksButtonActionPerformed(evt);
            }
        });
        add(cranksButton);
        cranksButton.setBounds(220, 630, 100, 30);

        rimsButton.setText("OBRĘCZE");
        buttonGroup1.add(rimsButton);
        rimsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rimsButtonActionPerformed(evt);
            }
        });
        add(rimsButton);
        rimsButton.setBounds(30, 470, 110, 30);

        tyresButton.setText("OPONY");
        buttonGroup1.add(tyresButton);
        tyresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyresButtonActionPerformed(evt);
            }
        });
        add(tyresButton);
        tyresButton.setBounds(620, 460, 90, 30);

        seatpostsButton.setText("SZTYCE");
        buttonGroup1.add(seatpostsButton);
        seatpostsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatpostsButtonActionPerformed(evt);
            }
        });
        add(seatpostsButton);
        seatpostsButton.setBounds(230, 360, 110, 30);

        pedalsButton.setText("PEDAŁY");
        buttonGroup1.add(pedalsButton);
        pedalsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedalsButtonActionPerformed(evt);
            }
        });
        add(pedalsButton);
        pedalsButton.setBounds(300, 420, 100, 30);

        framesButton.setText("RAMY");
        buttonGroup1.add(framesButton);
        framesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                framesButtonActionPerformed(evt);
            }
        });
        add(framesButton);
        framesButton.setBounds(370, 300, 90, 30);

        gripsButton.setText("GRIPY");
        buttonGroup1.add(gripsButton);
        gripsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gripsButtonActionPerformed(evt);
            }
        });
        add(gripsButton);
        gripsButton.setBounds(230, 20, 90, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bmxIcon2.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(0, 0, 720, 870);

        helmetButton.setText("jButton1");
        helmetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helmetButtonActionPerformed(evt);
            }
        });
        add(helmetButton);
        helmetButton.setBounds(110, 210, 130, 40);

        padsButton.setText("jButton1");
        padsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                padsButtonActionPerformed(evt);
            }
        });
        add(padsButton);
        padsButton.setBounds(110, 150, 130, 40);

        jButton1.setText("jButton1");
        add(jButton1);
        jButton1.setBounds(40, 70, 77, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void barsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barsButtonActionPerformed
        setPartName("kierownice",true);
    }//GEN-LAST:event_barsButtonActionPerformed

    private void framesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_framesButtonActionPerformed
        setPartName("ramy",true);
    }//GEN-LAST:event_framesButtonActionPerformed

    private void tyresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tyresButtonActionPerformed
        setPartName("opony",true);
    }//GEN-LAST:event_tyresButtonActionPerformed

    private void rimsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rimsButtonActionPerformed
        setPartName("obrecze",true);
    }//GEN-LAST:event_rimsButtonActionPerformed

    private void cranksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cranksButtonActionPerformed
        setPartName("korby",true);
    }//GEN-LAST:event_cranksButtonActionPerformed

    private void supportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supportsButtonActionPerformed
        setPartName("suporty",true);
    }//GEN-LAST:event_supportsButtonActionPerformed

    private void stemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stemsButtonActionPerformed
        String stemName = "wsporniki";
        if(getShopName().equals("manyfestbmx")) stemName = "mosty";
        setPartName(stemName,true);
    }//GEN-LAST:event_stemsButtonActionPerformed

    private void seatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatsButtonActionPerformed
        setPartName("siodelka",true);
    }//GEN-LAST:event_seatsButtonActionPerformed

    private void spokesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spokesButtonActionPerformed
        setPartName("szprychy",true);
    }//GEN-LAST:event_spokesButtonActionPerformed

    private void hubsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hubsButtonActionPerformed
        setPartName("piasty",true);
    }//GEN-LAST:event_hubsButtonActionPerformed

    private void forksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forksButtonActionPerformed
        setPartName("widelce",true);
    }//GEN-LAST:event_forksButtonActionPerformed

    private void barendsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barendsButtonActionPerformed
        String barendsName = "barendy";
        if(getShopName().equals("allday")) barendsName = "korki";
        setPartName(barendsName,true);
    }//GEN-LAST:event_barendsButtonActionPerformed

    private void pegsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegsButtonActionPerformed
        setPartName("pegi",true);
    }//GEN-LAST:event_pegsButtonActionPerformed

    private void steersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_steersButtonActionPerformed
        setPartName("stery",true);
    }//GEN-LAST:event_steersButtonActionPerformed

    private void chainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chainButtonActionPerformed
        setPartName("lancuchy",true);
    }//GEN-LAST:event_chainButtonActionPerformed

    
    private void chainwheelsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chainwheelsButtonActionPerformed
        setPartName("zebatki",true);
    }//GEN-LAST:event_chainwheelsButtonActionPerformed

    private void hubguardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hubguardsButtonActionPerformed
        String hubguardName = "hubguardy";
        if(getShopName().equals("allday") || getShopName().equals("manyfestbmx")) hubguardName = "hubguard";
        setPartName(hubguardName,true);
    }//GEN-LAST:event_hubguardsButtonActionPerformed

    private void seatpostsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatpostsButtonActionPerformed
        setPartName("sztyce",true);
    }//GEN-LAST:event_seatpostsButtonActionPerformed

    private void pedalsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedalsButtonActionPerformed
        setPartName("pedaly",true);
    }//GEN-LAST:event_pedalsButtonActionPerformed

    private void gripsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gripsButtonActionPerformed
        String gripName = "gripy";
        if(getShopName().equals("allday")) gripName = "chwyty";
        setPartName(gripName,true);
    }//GEN-LAST:event_gripsButtonActionPerformed

    private void helmetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helmetButtonActionPerformed
        setPartName("kaski", false);
    }//GEN-LAST:event_helmetButtonActionPerformed

    private void padsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_padsButtonActionPerformed
        setPartName("ochraniacze", false);
    }//GEN-LAST:event_padsButtonActionPerformed

    public BasketMainFrame getBasketFrame()
    {
        return basketFrame;
    }
    
    private String[] getHTMLElements(String partName, int shopNumber,int pageNumber)
    {
           URL url = this.getClass().getClassLoader().getResource("properties/info.txt");
           
           File file = new File(url.getFile());
       
           BufferedReader br;
           String[] part = new String[3];
           int lineNumber = 1;
           
           
           try {
            br = new BufferedReader(new FileReader(file)); 
            String st;
            
            while ((st = br.readLine()) != null)
            {  
                if(lineNumber == shopNumber)
                {
                    part = st.split(";");
                    System.out.println(part[0]);
                    System.out.println(part[1]);
                    System.out.println(part[2]);
                    System.out.println(part[3]);
                    System.out.println(part[4]);
                    System.out.println(part[5]);
                    System.out.println(part[6]);
                    System.out.println(part[7]);
                    //frame.getWearPanel().setPage(part[6]);
                    frame.setComparisonShop(part[7]);
                    break;
                }
                
                lineNumber += 1;
            }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            part[0] = part[0] + partName; 
            part[6] = part[6] + partName;
            
            switch(shopNumber)
            {
                case 1: break;
                case 2:
                {
                    part[0] = part[0] + "?page=" + pageNumber;
                    part[6] = part[6] + "?page=" + pageNumber;
                    break;
                }
                case 3:
                {
                    if(partName.equals("pedaly")) part[0] = part[0] + "?resultsPerPage=99999";
                    else part[0] = part[0] + "-bmx?resultsPerPage=99999";                    
                    part[6] = part[6] + "-bmx?resultsPerPage=99999";
                }
            }
             
           return part;
       }
    
       public void setResearcher(boolean partSelection)
       {
           setResearcher(nameOfPart, nameOfShop, shopNumber, partSelection);
       }
       
       private void setResearcher(String partName, String shopName, int shopNumber, boolean partSelection){
        
        try{   
            //String[] parts = getHTMLElements(partName,shopNumber,1);
                   // System.out.println("JESTEM TU");
            frame.setPropertyReader(shopName);

            String html = "";
            if(partSelection) html = frame.getPropertyReader().getProperty("url");
            else html = frame.getPropertyReader().getProperty("safetyURL");
            
            System.out.println("htmmml: " + html);
            
            if(!this.isUsed(shopName) || !this.partPreviousSearched(partName)){
           
                // notFirstSearch = false;

                if(!this.isUsed(shopName)) {                
                    shopResearcher = new ShopResearcher(html, shopName);
                    usedShopArray.add(shopResearcher);
                }
                else if(!this.partPreviousSearched(partName)) {
                    shopResearcher = this.getResearcher(shopName);
                    shopResearcher.clearProductsArray();
                    shopResearcher.setHTML(html); 
                  //  notFirstSearch = true;
                }
                shopResearcher.setFrame(this.frame);
                shopResearcher.setConnection();
                shopResearcher.searchPage(partName);
                
                if(!this.getFrame().productTableExists() && firstInitialized) {
                    shopResearcher.createTable();
                    this.getFrame().getPropertyReader().saveProperty("pTExist", "true");//getFilesHandler().saveSettings("pTExist", "true");   
                }
                else if(firstInitialized) shopResearcher.getProductDatabaseHandler().deleteAllElements(shopResearcher.getProductDatabaseHandler().getTableTitle());

                shopResearcher.setCategory(partName);
                shopResearcher.setConnection();
                shopResearcher.setInitialized(true);
                //shopResearcher.initializeArrayOfElements(htmlElements);
                shopResearcher.searchHTMLElements();
                // if(notFirstSearch){
                shopResearcher.setSpecificInformations(partName);
                shopResearcher.initializePartPanel(true);
           // }
           // else shopResearcher.initializePartPanel(true);
            }
            else{
                getResearcher(shopName).clearProductsArray();
                getResearcher(shopName).setSpecificInformations(partName);
                getResearcher(shopName).initializePartPanel(false);
            }
            firstInitialized = false;
            partSearched = true;
            }catch(NullPointerException ex){
                
                frame.setActivePanel(frame.getMainLabel());
                removeElement(getResearcher(shopName));
                System.out.println("SIEMANO JESTEM TU");
                ex.printStackTrace();
            }
       }
       
       public boolean partPreviousSearched(String category){
           for(int i=0; i < usedShopArray.size(); i++){
            for(int j=0; j < usedShopArray.get(i).getProductsArray().size(); j++){
                if(usedShopArray.get(i).getProductsArray().get(j).getCategory().equals(category)) return true;
            }
        }
        return false;
       }
       
       public boolean isUsed(String nameOfShop){
        for(int i=0; i < usedShopArray.size(); i++){
            if(usedShopArray.get(i).getShopName().equals(nameOfShop)) return true;
        }
        return false;
       }
       
       public boolean getSearchState()
       {
           return this.partSearched;
       }
       
       public ShopResearcher getResearcher(String nameOfShop)
       {
           for(int index = 0; index < usedShopArray.size(); index++){
               if(usedShopArray.get(index).getShopName().equals(nameOfShop)) return this.usedShopArray.get(index);
           }
           return null;
       }
       
       public void removeElement(ShopResearcher researcher){
           usedShopArray.remove(researcher);
       }
       
       public void setPartName(String name, boolean value)
       {
           this.nameOfPart = name;
           if(frame.getFeaturePanel().getAvebmxBox().isSelected() || frame.getFeaturePanel().getBmxlifeBox().isSelected() || frame.getFeaturePanel().getManyfestbmxBox().isSelected() || frame.getFeaturePanel().getAlldayBox().isSelected())setResearcher(nameOfPart,nameOfShop,shopNumber,value);
           else if(frame.getFeaturePanel().getAllShopsBox().isSelected()) setResearcher(nameOfPart,nameOfShop,shopNumber,value);
       }
       
       public String getPartName()
       {
           return this.nameOfPart;
       }
       
       public void setShop(String name, int number)
       {
           this.nameOfShop = name;
           this.shopNumber = number;
       }

       public String getShopName()
       {
           return this.nameOfShop;
       }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton barendsButton;
    private javax.swing.JButton barsButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chainButton;
    private javax.swing.JButton chainwheelsButton;
    private javax.swing.JButton cranksButton;
    private javax.swing.JButton forksButton;
    private javax.swing.JButton framesButton;
    private javax.swing.JButton gripsButton;
    private javax.swing.JButton helmetButton;
    private javax.swing.JButton hubguardsButton;
    private javax.swing.JButton hubsButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JButton padsButton;
    private javax.swing.JButton pedalsButton;
    private javax.swing.JButton pegsButton;
    private javax.swing.JButton rimsButton;
    private javax.swing.JButton seatpostsButton;
    private javax.swing.JButton seatsButton;
    private javax.swing.JButton spokesButton;
    private javax.swing.JButton steersButton;
    private javax.swing.JButton stemsButton;
    private javax.swing.JButton supportsButton;
    private javax.swing.JButton tyresButton;
    // End of variables declaration//GEN-END:variables
}
