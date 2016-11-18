package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class BuyHouseModel {
    private Player player;
    private BuyHouseView view;
    private DefaultComboBoxModel monopolyModel, numberModel;
    
    public BuyHouseModel(Player player, BuyHouseView view){
        this.player = player;
        this.view = view;
        this.view.setMonopolyModel(monopolyModel);
        
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
        view.dispose();
    }
    
    private void okClicked(){
        String monopoly = (String) view.getMonopolyComboBox().getSelectedItem();
        int numberOfHouses = view.getNumberComboBox().getSelectedIndex() + 1;
        player.purchaseHouse(monopoly, numberOfHouses);
        view.dispose();
    }
    
    private void populateMonopolyComboBox(){
        String[] monopolies = player.getMonopolies();
        for (String s : monopolies)
            monopolyModel.addElement(s);
    }
    
}
