package edu.ncsu.monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author massie
 */
public class TradeView extends JDialog{
    private JButton ok, cancel;
    private JComboBox sellers, properties;
    private JTextField amount;
    
    public TradeView (Frame parent){
        super(parent);
        setTitle("Trade Property");
        sellers = new JComboBox();
        properties = new JComboBox();
        amount = new JTextField();
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        ok.setEnabled(false);
        setModal(true);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(sellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(properties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(amount);
        contentPane.add(ok);
        contentPane.add(cancel);
        
        this.pack();
    }
    
    public void setSellersModel (ComboBoxModel m){
        sellers.setModel(m);
    }
    
    public void setPropertiesModel (ComboBoxModel m){
        properties.setModel(m);
    }
    
    public void addOKButtonListener (ActionListener a){
        ok.addActionListener(a);
    }
    
    public void addCancelButtonListener (ActionListener a){
        cancel.addActionListener(a);
    }
    
    public int getPrice(){
        return Integer.parseInt(amount.getText());
    }
    
    public void showErrorMessage(String s){
        JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
        this.pack();
    }
    
    public JComboBox getProperties(){
        return properties;
    }
    
    public JComboBox getSellers(){
        return sellers;
    }
    
    public void enableOK(){
        ok.setEnabled(true);
    }
    
    public void addSellersUpdateListener (ItemListener i){
        sellers.addItemListener(i);
    }
}
