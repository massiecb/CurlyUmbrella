package edu.ncsu.monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author massie
 */
public class BuyHouseViewController extends JDialog{
    private final JComboBox cboMonopoly;
    private final JComboBox cboNumber;
    private final JButton cancelButton;
    private final JButton okButton;
    private final BuyHouseModel model;
    private final DefaultComboBoxModel monopolyModel, numberModel;
    
    public BuyHouseViewController(BuyHouseModel model){
        this.model = model;
        
        okButton = new JButton();
        cancelButton = new JButton();
        cboMonopoly = new JComboBox();
        cboNumber = new JComboBox();
        
        monopolyModel = new DefaultComboBoxModel();
        numberModel = new DefaultComboBoxModel();
        
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(3, 2));
        c.add(new JLabel("Select monopoly"));
        c.add(cboMonopoly);
        c.add(new JLabel("Number of houses"));
        c.add(cboNumber);
        c.add(okButton);
        okButton.setText("OK");
        c.add(cancelButton);
        cancelButton.setText("Cancel");
        c.doLayout();
        
        populateMonopolies();
        populateNumbers();
        setMonopolyModel(monopolyModel);
        setNumberModel(numberModel);
        okButton.addActionListener(new OKButtonAction());
        cancelButton.addActionListener(new CancelButtonAction());
        
        this.pack();
    }
    
    public class CancelButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            cancelClicked();
        }
    }
    
    public class OKButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            okClicked();
        }
    }
    
    private void cancelClicked(){
        this.dispose();
    }
    
    private void okClicked(){
        String monopoly = (String) cboMonopoly.getSelectedItem();
        int numberOfHouses = cboMonopoly.getSelectedIndex() + 1;
        model.purchaseHouse(monopoly, numberOfHouses);
        this.dispose();
    }
    
    private void populateMonopolies(){
        for (String s : model.getMonopolies())
            monopolyModel.addElement(s);
    }
    
    private void populateNumbers(){
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5));
        numbers.stream().forEach((i) -> {
            numberModel.addElement(i);
        });
    }
    
    private void setMonopolyModel(ComboBoxModel m){
        cboMonopoly.setModel(m);
    }
    
    private void setNumberModel(ComboBoxModel m){
        cboNumber.setModel(m);
    }
}
