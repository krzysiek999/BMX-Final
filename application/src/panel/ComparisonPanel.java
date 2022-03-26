/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import frame.MainFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


/**
 *
 * @author krzys
 */
public class ComparisonPanel extends javax.swing.JPanel {

    /**
     * Creates new form ComparisonPanel
     */
    private int width,height;
    private JSplitPane splitPane;
    MainFrame mainFrame;
    Box box;
    JPanel splitPanel = new JPanel();
    
    private ProductPanel product1 = new ProductPanel();
    private ProductPanel product2 = new ProductPanel();
    
    public ComparisonPanel(int width, int height, MainFrame frame) {
        
        this.mainFrame = frame;
        this.width = width;
        this.height = height;
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, product1, product2);
        splitPane.setPreferredSize(new Dimension(width, height));
        splitPane.setDividerLocation(width/2);
        
        product1.setProductName("XDXD");
        product1.setPrice("123");
        product2.setProductName("ajkfhsjkah");
        product2.setPrice("342");
        
        splitPanel.add(splitPane);
        splitPanel.setBounds(0, 0, width, height);
        box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(800,750));
       
        
        box.add(splitPanel);
        box.add(Box.createVerticalStrut(30));
        box.add(new ButtonPanel());
        
        
        this.add(box);
        
    
    }
    
    public ProductPanel getProductOne()
    {
        return this.product1;
    }
    
    public ProductPanel getProductTwo()
    {
        return this.product2;
    }
    
    public void repaintMainPanel()
    {
        this.revalidate();
        this.repaint();
    }
    
    class ButtonPanel extends JPanel implements ActionListener
    {
        String language = "properties/" + mainFrame.getLanguage();
        ResourceBundle resource = ResourceBundle.getBundle(language);
        
        JButton backButton = new JButton(resource.getString("back"));
        JButton basketButton = new JButton(resource.getString("basket"));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 40);

        public ButtonPanel() {
        
            this.setLayout(layout);
            this.add(backButton);
            this.add(basketButton);
            
            backButton.addActionListener(this);
            basketButton.addActionListener(this);
            
        }
        
        

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource().equals(backButton))
            {
                mainFrame.getMainPanel().getBasketFrame().getPanel().getCompareButton().setEnabled(false);
                mainFrame.getMainPanel().setVisible(true);
                mainFrame.getUserPanel().setVisible(true);
                mainFrame.getComparisonPanel().setVisible(false);
                
            }
            else if(e.getSource().equals(basketButton))
            {
                boolean visibilty;
                visibilty = !mainFrame.getMainPanel().getBasketFrame().isVisible();            
                mainFrame.getMainPanel().getBasketFrame().setVisible(visibilty);
                
                mainFrame.getMainPanel().getBasketFrame().getPanel().getCompareButton().setEnabled(true);
                
            }
            
        }
    }
    
    class ProductPanel extends JPanel
    {

        Box productBox;
        JLabel productNameLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        
        public ProductPanel() 
        {
            this.setPreferredSize(new Dimension(350,700));
            productBox = Box.createVerticalBox();
            productBox.add(productNameLabel);
            productBox.add(Box.createVerticalStrut(20));
            productBox.add(priceLabel);
            
            this.add(productBox);
            
        }
        
        public void repaintPanel()
        {
            this.revalidate();
            this.repaint();
        }
        
        public void setProductName(String name)
        {
            productNameLabel.setText(name);
            repaintPanel();
            repaintMainPanel();
        }
        
        public void setPrice(String price)
        {
            priceLabel.setText(price);
            repaintPanel();
            repaintMainPanel();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
