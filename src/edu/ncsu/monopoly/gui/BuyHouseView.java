package edu.ncsu.monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author massie
 */
public class BuyHouseView extends JDialog{
    private JComboBox cboMonopoly;
    private JComboBox cboNumber;
    private JButton cancelButton;
    private JButton okButton;
    
    public BuyHouseView(){
        okButton = new JButton();
        cancelButton = new JButton();
        cboMonopoly = new JComboBox();
        cboNumber = new JComboBox();
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(3, 2));
        c.add(new JLabel("Select monopoly"));
        c.add(cboMonopoly);
        c.add(new JLabel("Number of houses"));
        c.add(okButton);
        c.add(cancelButton);
        c.doLayout();
        this.pack();
    }
    
    public void setMonopolyModel(ComboBoxModel m){
        cboMonopoly.setModel(m);
    }
    
    public void addOKButtonListener(ActionListener a){
        okButton.addActionListener(a);
    }
    
    public void addCancelButtonListener(ActionListener a){
        cancelButton.addActionListener(a);
    }
    
    public JComboBox getMonopolyComboBox(){
        return cboMonopoly;
    }
    
    public JComboBox getNumberComboBox(){
        return cboNumber;
    }
    
}
