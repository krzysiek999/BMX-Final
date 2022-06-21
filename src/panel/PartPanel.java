/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import classes.ShopProduct;
import classes.ShopResearcher;
//import frame.MainFrame;
//import frame.PartFrame;
import frame.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author krzys
 */
public class PartPanel extends javax.swing.JPanel {

    /**
     * Creates new form PartPanel
     */
    ShopResearcher researcher;
   // ScrollPaneLayout layout = new ScrollPaneLayout();
    //GridLayout layout = new GridLayout(0, 3);
    JTextArea textArea = new JTextArea(20, 20); 
    JScrollPane scrollPane = new JScrollPane();
    private JButton backButton, basketButton, addBasketButton;  
    
    String resourceLanguage;
    //ResourceBundle languageChoice;
    
    PartFrame frame;
    MainFrame mainFrame;
    BasketMainFrame basketFrame; 
    DefaultTableModel tableModel;
    JTable table;
    
    
    public PartPanel(ShopResearcher researcher, MainFrame frame, PartFrame thisFrame) 
    {
        
        this.researcher = researcher;
        this.frame = thisFrame;
        this.mainFrame = frame;
        this.basketFrame = frame.getMainPanel().getBasketFrame();
        
        this.basketFrame.setResearcher(researcher);
        
        
        //this.resourceLanguage = "properties/" + mainFrame.getLanguage();
        //languageChoice = ResourceBundle.getBundle(this.resourceLanguage);
        
        InnerActionHandler actionHandler = new InnerActionHandler();
        
        backButton = new JButton(mainFrame.getResourceBundle().getString("back"));
        basketButton = new JButton(mainFrame.getResourceBundle().getString("basket"));
        addBasketButton = new JButton(mainFrame.getResourceBundle().getString("addBasket"));
        addBasketButton.addActionListener(actionHandler);
        backButton.addActionListener(actionHandler);
        basketButton.addActionListener(actionHandler);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 800));
        
        this.add(new JLabel(researcher.getShopName()));
        this.add(new JLabel("  "));
        this.add(backButton);
        this.add(addBasketButton);
        this.add(basketButton);
        
        setLabel();
    }
    
    private void setLabel()
    {
        ArrayList<ShopProduct> arrayInformation = researcher.getInformations();
        
        ShopPanel shopPanel = new ShopPanel();
        
        shopPanel.setLabel(arrayInformation);
        
        tableModel = shopPanel.getJTableModel();
        table = shopPanel.getTable();

        scrollPane.getViewport().add(shopPanel,0);
        this.add(scrollPane);

    }
    
    public void setButtonText()
    {
        this.backButton.setText(mainFrame.getResourceBundle().getString("back"));
        this.basketButton.setText(mainFrame.getResourceBundle().getString("basket"));
        this.addBasketButton.setText(mainFrame.getResourceBundle().getString("addBasket"));
        this.repaint();  
    }

    
    class ShopPanel extends JPanel
    {
        JLabel numberLabel = new JLabel();
        JLabel partLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        
        final String[] TABLE_COLUMNS = {"Number","Part","Price"};
        private static final int ROW_HEIGHT = 40;
        
        final DefaultTableModel tableModel = new DefaultTableModel(TABLE_COLUMNS, 3);
        JTable table;
       

        public ShopPanel() 
        {
        this.setSize(500, 500);
        tableModel.setRowCount(0);
        table = new JTable(tableModel);
        
        table.setRowHeight(ROW_HEIGHT);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(350);
        
        }
        
        public void setLabel(ArrayList<ShopProduct> arrayL)
        {
            
            for(int i = 0; i < arrayL.size(); i++) 
                tableModel.addRow(new String[]{"" + (i+1), arrayL.get(i).getProductDetails()[0], arrayL.get(i).getProductDetails()[1]});
            this.add(table);
            
        }
        
        public DefaultTableModel getJTableModel()
        {
            return this.tableModel;
        }
        
        public JTable getTable()
        {
            return this.table;
        }
        
    }
    
    class InnerActionHandler implements ActionListener
    {
        
        //BasketMainPanel basketPanel = basketFrame.getPanel();
        boolean visiblity = false;
        boolean existance = false;
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
        
            if(e.getSource().equals(backButton)) 
            {
                mainFrame.setVisible(true);
                frame.dispose();
            }
            else if(e.getSource().equals(basketButton))
            {
             /*   basketFrame.getPanel().getTabPane().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               if(basketFrame.getPanel().getTabPane().getTabCount() != 0) basketFrame.getPanel().addElementToBasket("", "", null, false);
            }
        });*/
                if(!basketFrame.isVisible()) basketFrame.setVisible(true);
                else basketFrame.setVisible(false);
            }
            else if(e.getSource().equals(addBasketButton)) 
            {
                
                int z = basketFrame.getPanel().getTabPane().getTabCount();
            
                if(z == 0) basketFrame.getPanel().addTab(researcher.getShopName());
                else
                {
                    existance = false;
                    
                    for(int i = 0; i < z; i++)
                    {
                        String str = basketFrame.getPanel().getTabPane().getTitleAt(i);
                        if(!str.equals(researcher.getShopName())) existance = true;
                        else 
                        {
                            existance = false;
                            break;
                        }
                    }

                    if(existance) basketFrame.getPanel().addTab(researcher.getShopName());
                        
                    
                }
                
               if(table.getSelectedRow() != -1)
               {
                   String nameOfPart = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
                   String price = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
                   basketFrame.getPanel().addElementToBasket(nameOfPart, price, researcher);
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

        mainPane = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane mainPane;
    // End of variables declaration//GEN-END:variables
}
