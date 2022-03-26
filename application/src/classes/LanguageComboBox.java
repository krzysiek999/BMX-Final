/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author sluja
 */
public class LanguageComboBox extends JComboBox
{
    private DefaultComboBoxModel model;
     
    public LanguageComboBox() {
        model = new DefaultComboBoxModel();
        setModel(model);
        setRenderer(new LanguageRenderer());
        setEditor(new LanguageEditor());
    }
     
    /**
     * Add an array items to this combo box.
     * Each item is an array of two String elements:
     * - first element is country name.
     * - second element is path of an image file for country flag.
     * @param items
     */
    public void addItems(String[][] items) {
        for (String[] anItem : items) {
            model.addElement(anItem);
        }
    }
}
