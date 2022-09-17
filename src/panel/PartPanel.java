/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import classes.ImageHandler;
import classes.ShopProduct;
import classes.ShopResearcher;
//import frame.MainFrame;
//import frame.PartFrame;
import frame.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



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
    FlowLayout buttonLayout = new FlowLayout(25);
    JPanel buttonPanel = new JPanel(buttonLayout);
    JPanel scrollPanel = new JPanel();
    BoxLayout mainLayout;
    ShopPanel shopPanel;
    
    String resourceLanguage;
    //ResourceBundle languageChoice;
    
    //PartFrame frame;
    MainFrame mainFrame;
    BasketMainFrame basketFrame; 
    DefaultTableModel tableModel;
    JTable table;
    InnerActionHandler actionHandler = new InnerActionHandler();
    ImageHandler imageHandler = new ImageHandler();
    
    public PartPanel(ShopResearcher researcher, MainFrame frame, boolean firstInitialized) 
    {
        
        this.researcher = researcher;
        //this.frame = thisFrame;
        this.mainFrame = frame;
        this.basketFrame = frame.getMainPanel().getBasketFrame();
        //this.basketFrame.setResearcher(researcher);
        mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(mainLayout);
        
        
        
        backButton = new JButton(mainFrame.getPropertyReader().getProperty("back"));
        basketButton = new JButton(mainFrame.getPropertyReader().getProperty("basket"));
        addBasketButton = new JButton(mainFrame.getPropertyReader().getProperty("addBasket"));
        
        addBasketButton.addActionListener(actionHandler);
        backButton.addActionListener(actionHandler);
        basketButton.addActionListener(actionHandler);
        
        addBasketButton.setEnabled(false);
       // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(frame.getElementOneWidth(),800));
        
        this.add(new JLabel(researcher.getShopName()));
        buttonPanel.setPreferredSize(new Dimension(50,5));
        buttonPanel.add(backButton);
        buttonPanel.add(addBasketButton);
        buttonPanel.add(basketButton);
        
        this.add(buttonPanel);
        setLabel(firstInitialized);
    }
    
    public JButton getAddBasketButton(){
        return this.addBasketButton;
    }
    
    public ShopResearcher getResearcher(){
        return this.researcher;
    }
    
    private void setLabel(boolean firstInitialized)
    {
        ArrayList<ShopProduct> arrayInformation = new ArrayList<ShopProduct>();
        if(firstInitialized) arrayInformation = researcher.getInformations();
        else arrayInformation = researcher.getSpecificInformations();
        
        shopPanel = new ShopPanel();
        
        /*
        for(int i=0; i < arrayInformation.size(); i++) {
            shopPanel.addProduct(arrayInformation.get(i).getProductName(), arrayInformation.get(i).getProductPrice(), arrayInformation.get(i).getImageURL());
        }*/
        
        shopPanel.setLabel(arrayInformation);
        
        tableModel = shopPanel.getJTableModel();
        table = shopPanel.getTable();
        table.getSelectionModel().addListSelectionListener(actionHandler);

        scrollPane.getViewport().add(table,0);
        scrollPanel.add(scrollPane);
        this.add(scrollPanel);
    }
    
    public void setButtonText()
    {
        this.backButton.setText(mainFrame.getPropertyReader().getProperty("back"));
        this.basketButton.setText(mainFrame.getPropertyReader().getProperty("basket"));
        this.addBasketButton.setText(mainFrame.getPropertyReader().getProperty("addBasket"));
        this.repaint();  
    }
    
    public JTable getJTable(){
        return this.table;
    }
    
    public int getTablePriceColumn(){
      //  return this.shopPanel.getPriceColumn();
      return 0;
    }

    public void updatePrice(int row, String value){
        //this.getJTable().setValueAt(value, row, this.shopPanel.getPriceColumn());
    }
    
    public JButton getBasketButton(){
        return this.basketButton;
    }

    
    class ShopPanel extends JPanel {
        
      /*  JButton productButton;
        private int buttonSize = 120, buttonGap = 20, elementSize = 150;
        private JLabel productNameLabel, productPriceLabel;
        private JPanel productPanel;
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, buttonGap, buttonGap);
        GridLayout productLayout = new GridLayout(3,1,0,20); 
        
        public ShopPanel() {
            this.setLayout(layout);
            //productPanel = new JPanel(productLayout);
            this.setPreferredSize(new Dimension(mainFrame.getElementOneWidth() - 200,800));
        }
        
        public void addProduct(String productName, String price, String imageURL) {
            productButton = new JButton();
            productPanel = new JPanel(productLayout);
            productPanel.setLayout(productLayout);
            //productLayout = new BoxLayout(productPanel, BoxLayout.PAGE_AXIS);
            
            productButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
            productPanel.setPreferredSize(new Dimension(buttonSize,elementSize));
            
            imageHandler.setImage(imageURL);
            imageHandler.resizeImage();
            
            productButton.setIcon(imageHandler.getImage());
            productButton.addActionListener(actionHandler);
            
            this.productNameLabel = new JLabel(productName);
            this.productPriceLabel = new JLabel(price);
            
            productPanel.add(productButton);
            productPanel.add(this.productNameLabel);
            productPanel.add(this.productPriceLabel);
            
            this.add(productPanel);
        }*/
        
        JLabel numberLabel = new JLabel();
        JLabel partLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        
        private final int NUMBER_COLUMN = 0, PART_COLUMN = 1, PRICE_COLUMN = 2, IMAGE_COLUMN = 3;
        
        final String[] TABLE_COLUMNS = {"Nr","Part","Price","Image"};
        private static final int ROW_HEIGHT = 100;
        
        private JButton productButton;
        
        final DefaultTableModel tableModel = new DefaultTableModel(TABLE_COLUMNS, 4){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==3) return ImageIcon.class;
                return Object.class;
            }
        };
        JTable table;
       

        public ShopPanel() 
        {
        this.setSize(300, 500);
        tableModel.setRowCount(0);
        table = new JTable(tableModel);
        
        table.setRowHeight(ROW_HEIGHT);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(250);
        columnModel.getColumn(3).setPreferredWidth(150);
        
        }
        
        public int getPriceColumn(){
            return this.PRICE_COLUMN;
        }
        
        public void setLabel(ArrayList<ShopProduct> arrayL)
        {
            
            for(int i = 0; i < arrayL.size() - 1; i++) 
            {
                imageHandler.setImage(arrayL.get(i).getProductDetails()[3]);
                imageHandler.resizeImage();
                
                Object[] data = {"" + (i+1), arrayL.get(i).getProductDetails()[0], arrayL.get(i).getProductDetails()[1], imageHandler.getImage()};                
                tableModel.addRow(data);
            }
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
    
    class InnerActionHandler implements ActionListener, ListSelectionListener
    {
        
        //BasketMainPanel basketPanel = basketFrame.getPanel();
        boolean visiblity = false;
        boolean existance = false;
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
        
            if(e.getSource().equals(backButton)) 
            {
                mainFrame.setActivePanel(mainFrame.getMainLabel());
                mainFrame.getFeaturePanel().getAddBasketButton().setEnabled(false);
                
            }
            else if(e.getSource().equals(basketButton))
            {
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
                   nameOfPart.replaceAll("'", "");
                   String price = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
                   basketFrame.getPanel().addElementToBasket(nameOfPart, price, researcher);
               }
            }
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            addBasketButton.setEnabled(true);
            mainFrame.getFeaturePanel().getAddBasketButton().setEnabled(true);
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
