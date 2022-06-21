/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author krzys
 */
public class BasketShopPanel extends javax.swing.JPanel {

    final private String[] TABLE_COLUMNS = {"Nazwa","Cena"};
    final private DefaultTableModel tableModel = new DefaultTableModel(TABLE_COLUMNS, 2);
    final private int ROW_HEIGHT = 60;
    JTable table;
    private String nameOfShop;
    JScrollPane scrollPane = new JScrollPane();
    
    boolean singleSelection = true;
    
    int width = 380, height = 500;
    
    BasketMainPanel basketPanel;
    
    ArrayList<String> priceList = new ArrayList<String>();    
    public BasketShopPanel(String nameOfShop, BasketMainPanel basketPanel) {
        
        this.basketPanel = basketPanel;
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(width, height));
        
        this.setSize(width, height);
        this.nameOfShop = nameOfShop;
        
        tableModel.setRowCount(0);

        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(ROW_HEIGHT);

        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(0).setPreferredWidth(100);
       
        scrollPane.getViewport().add(table,0);
        this.add(scrollPane);

    }
    
    public void addElementToTable(String nameOfPart, String price)
    {
      boolean consist = price.contains("Stara cena");
      
      if(consist)
      {
          String[] priceArray = price.split(":");
          price = priceArray[2];
      }
      
      tableModel.addRow(new String[]{nameOfPart,price});
      scrollPane.getViewport().add(table,0);
      this.add(scrollPane);
    }
    
    public ArrayList<String> getProductsPrice()
    {
        int amount = tableModel.getRowCount();
        
        if(amount != 0)
        {
            priceList.removeAll(priceList);
        for(int i=0; i<tableModel.getRowCount();i++)
            {
                priceList.add(tableModel.getValueAt(i, 1).toString());
            }
        return priceList;
        }
        else return null;
        
    }
    
    public void deleteElementFromTable()
    {
        int row = table.getSelectedRow();
        String nameOfProduct = "'" + table.getModel().getValueAt(row, 0).toString() + "'";
        this.basketPanel.getMainPanel().getDatabaseHandler().deleteOneElement(this.basketPanel.getMainPanel().getDatabaseHandler().getTableTitle(), nameOfProduct);
        tableModel.removeRow(row); 
    }
    
    public String[] getProductsName()
    {
        String[] name = new String[tableModel.getRowCount()];
        for(int i=0; i<tableModel.getRowCount();i++)
        {
            name[i] = tableModel.getValueAt(i, 1).toString();
        }
        return name;
    }

    public BasketShopPanel getPanel()
    {
        return this;
    }
    
    public int getSizeOfBasket()
    {
       return this.table.getRowCount();
    }
    
    public JTable getTable()
    {
        return this.table;
    }
    
    public int getTableSize()
    {
        return this.table.getRowCount();
    }
    
    public String getShopName()
    {
        return this.nameOfShop;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
