/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 *
 * @author sluja
 */
public class LanguageEditor extends BasicComboBoxEditor
{
    private JPanel panel = new JPanel();
    private JLabel labelItem = new JLabel();
    private String selectedValue;
     
    public LanguageEditor() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 5, 2, 2);
         
        labelItem.setOpaque(false);
        labelItem.setHorizontalAlignment(JLabel.LEFT);
        labelItem.setForeground(Color.WHITE);
         
        panel.add(labelItem, constraints);
        panel.setBackground(Color.BLUE);       
    }
     
    public Component getEditorComponent() {
        return this.panel;
    }
     
    public Object getItem() {
        return this.selectedValue;
    }
     
    public void setItem(Object item) {
        if (item == null) {
            return;
        }
        String[] countryItem = (String[]) item;
        selectedValue = countryItem[0];
        labelItem.setText(selectedValue);
        labelItem.setIcon(new ImageIcon(countryItem[1]));      
    }  
    
}
