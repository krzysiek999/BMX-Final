/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import classes.ShopResearcher;
import frame.BasketMainFrame;
import frame.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sluja
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    MainFrame frame;
    private String nameOfShop = "";
    private String nameOfPart = "";
    private int shopNumber = 0;
    
    private BasketMainFrame basketFrame;
    
    public MainPanel(MainFrame frame) {
        
        this.setSize(frame.getAppWidth(), frame.getAppHeight());
        this.frame = frame;
        basketFrame = new BasketMainFrame(this);
        initComponents();
        
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
        jButton1 = new javax.swing.JButton();
        barsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bmxlifeCheckBox = new javax.swing.JCheckBox();
        manyfestCheckBox = new javax.swing.JCheckBox();
        avebmxCheckBox = new javax.swing.JCheckBox();
        alldayCheckBox = new javax.swing.JCheckBox();
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

        setLayout(null);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(634, 299, 73, 23);

        barsButton.setText("KIEROWNICE");
        barsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barsButtonActionPerformed(evt);
            }
        });
        add(barsButton);
        barsButton.setBounds(380, 70, 140, 20);

        jLabel1.setText("Sklepy:");
        add(jLabel1);
        jLabel1.setBounds(20, 650, 60, 14);

        buttonGroup.add(bmxlifeCheckBox);
        bmxlifeCheckBox.setText("BmxLife");
        bmxlifeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmxlifeCheckBoxActionPerformed(evt);
            }
        });
        add(bmxlifeCheckBox);
        bmxlifeCheckBox.setBounds(90, 660, 110, 23);

        buttonGroup.add(manyfestCheckBox);
        manyfestCheckBox.setText("ManyfestBmx");
        manyfestCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manyfestCheckBoxActionPerformed(evt);
            }
        });
        add(manyfestCheckBox);
        manyfestCheckBox.setBounds(200, 630, 130, 23);

        buttonGroup.add(avebmxCheckBox);
        avebmxCheckBox.setText("AveBmx");
        avebmxCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avebmxCheckBoxActionPerformed(evt);
            }
        });
        add(avebmxCheckBox);
        avebmxCheckBox.setBounds(90, 630, 110, 23);

        buttonGroup.add(alldayCheckBox);
        alldayCheckBox.setText("AlldayBmx");
        alldayCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alldayCheckBoxActionPerformed(evt);
            }
        });
        add(alldayCheckBox);
        alldayCheckBox.setBounds(200, 660, 130, 23);

        hubguardsButton.setText("HUBGUARDY");
        hubguardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hubguardsButtonActionPerformed(evt);
            }
        });
        add(hubguardsButton);
        hubguardsButton.setBounds(470, 460, 130, 30);

        chainwheelsButton.setText("ZĘBATKI");
        chainwheelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chainwheelsButtonActionPerformed(evt);
            }
        });
        add(chainwheelsButton);
        chainwheelsButton.setBounds(210, 420, 100, 30);

        chainButton.setText("ŁAŃCUCHY");
        chainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chainButtonActionPerformed(evt);
            }
        });
        add(chainButton);
        chainButton.setBounds(170, 380, 110, 30);

        steersButton.setText("STERY");
        steersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                steersButtonActionPerformed(evt);
            }
        });
        add(steersButton);
        steersButton.setBounds(470, 240, 90, 30);

        pegsButton.setText("PEGI");
        pegsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegsButtonActionPerformed(evt);
            }
        });
        add(pegsButton);
        pegsButton.setBounds(90, 420, 90, 30);

        barendsButton.setText("BARENDY");
        barendsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barendsButtonActionPerformed(evt);
            }
        });
        add(barendsButton);
        barendsButton.setBounds(550, 50, 120, 30);

        forksButton.setText("WIDELCE");
        forksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forksButtonActionPerformed(evt);
            }
        });
        add(forksButton);
        forksButton.setBounds(400, 360, 100, 30);

        hubsButton.setText("PIASTY");
        hubsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hubsButtonActionPerformed(evt);
            }
        });
        add(hubsButton);
        hubsButton.setBounds(480, 420, 100, 30);

        spokesButton.setText("SZPRYCHY");
        spokesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spokesButtonActionPerformed(evt);
            }
        });
        add(spokesButton);
        spokesButton.setBounds(440, 530, 120, 30);

        seatsButton.setText("SIODEŁKO");
        seatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatsButtonActionPerformed(evt);
            }
        });
        add(seatsButton);
        seatsButton.setBounds(200, 230, 120, 30);

        stemsButton.setText("MOSTKI");
        stemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stemsButtonActionPerformed(evt);
            }
        });
        add(stemsButton);
        stemsButton.setBounds(420, 150, 100, 30);

        supportsButton.setText("SUPPORTY");
        supportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supportsButtonActionPerformed(evt);
            }
        });
        add(supportsButton);
        supportsButton.setBounds(320, 410, 100, 30);

        cranksButton.setText("KORBY");
        cranksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cranksButtonActionPerformed(evt);
            }
        });
        add(cranksButton);
        cranksButton.setBounds(270, 460, 100, 30);

        rimsButton.setText("OBRĘCZE");
        rimsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rimsButtonActionPerformed(evt);
            }
        });
        add(rimsButton);
        rimsButton.setBounds(120, 490, 110, 30);

        tyresButton.setText("OPONY");
        tyresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tyresButtonActionPerformed(evt);
            }
        });
        add(tyresButton);
        tyresButton.setBounds(560, 370, 90, 30);

        seatpostsButton.setText("SZTYCE");
        seatpostsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatpostsButtonActionPerformed(evt);
            }
        });
        add(seatpostsButton);
        seatpostsButton.setBounds(210, 280, 110, 20);

        pedalsButton.setText("PEDAŁY");
        pedalsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedalsButtonActionPerformed(evt);
            }
        });
        add(pedalsButton);
        pedalsButton.setBounds(310, 320, 100, 30);

        framesButton.setText("RAMY");
        framesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                framesButtonActionPerformed(evt);
            }
        });
        add(framesButton);
        framesButton.setBounds(340, 230, 90, 30);

        gripsButton.setText("GRIPY");
        add(gripsButton);
        gripsButton.setBounds(260, 20, 90, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bmxIconR.PNG"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(10, 10, 620, 640);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(frame.getLanguage());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void barsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barsButtonActionPerformed
             
        setResearcher("kierownice", nameOfShop,shopNumber);
    }//GEN-LAST:event_barsButtonActionPerformed

    private void avebmxCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avebmxCheckBoxActionPerformed
        setShop("avebmx",2);
    }//GEN-LAST:event_avebmxCheckBoxActionPerformed

    private void bmxlifeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmxlifeCheckBoxActionPerformed
         setShop("bmxlife",1);
    }//GEN-LAST:event_bmxlifeCheckBoxActionPerformed

    private void manyfestCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manyfestCheckBoxActionPerformed
        setShop("manyfestbmx",3);
    }//GEN-LAST:event_manyfestCheckBoxActionPerformed

    private void alldayCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alldayCheckBoxActionPerformed
         setShop("allday",4);
    }//GEN-LAST:event_alldayCheckBoxActionPerformed

    private void framesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_framesButtonActionPerformed
        setPartName("ramy");
    }//GEN-LAST:event_framesButtonActionPerformed

    private void tyresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tyresButtonActionPerformed
        setPartName("opony");
    }//GEN-LAST:event_tyresButtonActionPerformed

    private void rimsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rimsButtonActionPerformed
        setPartName("obrecze");
    }//GEN-LAST:event_rimsButtonActionPerformed

    private void cranksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cranksButtonActionPerformed
        setPartName("korby");
    }//GEN-LAST:event_cranksButtonActionPerformed

    private void supportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supportsButtonActionPerformed
        setPartName("suporty");
    }//GEN-LAST:event_supportsButtonActionPerformed

    private void stemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stemsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stemsButtonActionPerformed

    private void seatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatsButtonActionPerformed
        setPartName("siodelka");
    }//GEN-LAST:event_seatsButtonActionPerformed

    private void spokesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spokesButtonActionPerformed
        setPartName("szprychy");
    }//GEN-LAST:event_spokesButtonActionPerformed

    private void hubsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hubsButtonActionPerformed
        setPartName("piasty");
    }//GEN-LAST:event_hubsButtonActionPerformed

    private void forksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forksButtonActionPerformed
        setPartName("widelce");
    }//GEN-LAST:event_forksButtonActionPerformed

    private void barendsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barendsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barendsButtonActionPerformed

    private void pegsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pegsButtonActionPerformed
        setPartName("pegi");
    }//GEN-LAST:event_pegsButtonActionPerformed

    private void steersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_steersButtonActionPerformed
        setPartName("stery");
    }//GEN-LAST:event_steersButtonActionPerformed

    private void chainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chainButtonActionPerformed
        setPartName("lancuchy");
    }//GEN-LAST:event_chainButtonActionPerformed

    private void chainwheelsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chainwheelsButtonActionPerformed
        setPartName("zebatki");
    }//GEN-LAST:event_chainwheelsButtonActionPerformed

    private void hubguardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hubguardsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hubguardsButtonActionPerformed

    private void seatpostsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatpostsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seatpostsButtonActionPerformed

    private void pedalsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedalsButtonActionPerformed
        setPartName("pedaly");
    }//GEN-LAST:event_pedalsButtonActionPerformed

    public BasketMainFrame getBasketFrame()
    {
        return basketFrame;
    }
    
       private String[] getHTMLElements(String namePart, int shopNumber,int pageNumber)
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
                    break;
                }
                
                lineNumber += 1;
            }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            part[0] = part[0] + namePart; 
            
            switch(shopNumber)
            {
                case 1: break;
                case 2:
                {
                    part[0] = part[0] + "?page=" + pageNumber;
                    break;
                }
                case 3:
                {
                    part[0] = part[0] + "-bmx?resultsPerPage=99999";
                }
            }
             
           return part;
       }
       private void setResearcher()
       {
           setResearcher(nameOfPart, nameOfShop, shopNumber);
       }
       
       private void setResearcher(String namePart, String nameShop, int shopNumber)
       {
        
        
        String[] parts = getHTMLElements(namePart,shopNumber,1);
        
        String html = parts[0];
        String[] htmlElements = {parts[1],parts[2]};
        ShopResearcher shopResearcher = new ShopResearcher(html, nameShop, 2);
        
        shopResearcher.setCategory(namePart);
        shopResearcher.setConnection();
        shopResearcher.initializeArrayOfElements(htmlElements);
        shopResearcher.setFrame(this.frame);
        shopResearcher.searchHTMLElements();
        shopResearcher.initializePartPanel();
        
       }
       
       private void setPartName(String name)
       {
           this.nameOfPart = name;
           if(avebmxCheckBox.isSelected() || bmxlifeCheckBox.isSelected() || manyfestCheckBox.isSelected() || alldayCheckBox.isSelected()) setResearcher(nameOfPart,nameOfShop,shopNumber);
       }
       
       private void setShop(String name, int number)
       {
           this.nameOfShop = name;
           this.shopNumber = number;
       }
       
       
       
       public String getShopName()
       {
           return this.nameOfShop;
       }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alldayCheckBox;
    private javax.swing.JCheckBox avebmxCheckBox;
    private javax.swing.JButton barendsButton;
    private javax.swing.JButton barsButton;
    private javax.swing.JCheckBox bmxlifeCheckBox;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton chainButton;
    private javax.swing.JButton chainwheelsButton;
    private javax.swing.JButton cranksButton;
    private javax.swing.JButton forksButton;
    private javax.swing.JButton framesButton;
    private javax.swing.JButton gripsButton;
    private javax.swing.JButton hubguardsButton;
    private javax.swing.JButton hubsButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox manyfestCheckBox;
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