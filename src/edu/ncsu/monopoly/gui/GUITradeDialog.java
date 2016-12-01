package edu.ncsu.monopoly.gui;

import java.awt.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.ncsu.monopoly.*;
import edu.ncsu.monopoly.TradeDeal;
import edu.ncsu.monopoly.TradeDialog;

/* Refactorings
 * No Longer used in packaage. This implementation mixed Concerns
 * Has been seperated into TradeModel and TradeView which 
 * implememnts this class in MVC to seperate the concerns. The new
 * implementation also removes raw types and uses generics instead.
 * The new implementation also doesn't return the Dialog box itself, but rather
 * a only the TradeDeal type the other classes are concerned with.
 * All depricated functions have been removed.
*/

public class GUITradeDialog extends JDialog implements TradeDialog {
    private JButton btnOK, btnCancel;
    private JComboBox cboSellers, cboProperties;

    private TradeDeal deal;
    private JTextField txtAmount;
    
    public GUITradeDialog(Frame parent) {
        super(parent);
        
        setTitle("Trade Property");
        cboSellers = new JComboBox();
        cboProperties = new JComboBox();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        btnOK.setEnabled(false);
        
        buildSellersCombo();
        setModal(true);
             
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(cboSellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(cboProperties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            GUITradeDialog.this.setVisible(false);
        });
        
        cboSellers.addItemListener((ItemEvent e) -> {
            Player player = (Player)e.getItem();
            updatePropertiesCombo(player);
        });
        
        btnOK.addActionListener((ActionEvent e) -> {
            int amount = 0;
            try{
                amount = Integer.parseInt(txtAmount.getText());
            } catch(NumberFormatException nfe) {
                JOptionPane.showMessageDialog(GUITradeDialog.this,
                        "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount < 0) {
                JOptionPane.showMessageDialog(GUITradeDialog.this,
                        "Amount should be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cell cell = (Cell)cboProperties.getSelectedItem();
            if(cell == null) return;
            Player player = (Player)cboSellers.getSelectedItem();
            Player currentPlayer = GameMaster.instance().getCurrentPlayer();
            if(currentPlayer.getMoney() > amount) {
                deal = new TradeDeal();
                deal.setAmount(amount);
                deal.setPropertyName(cell.getName());
                deal.setSellerIndex(GameMaster.instance().getPlayerIndex(player));
            }
            else {
                JOptionPane.showMessageDialog(GUITradeDialog.this,
                        "Amount cannot equal or exceed all your money", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setVisible(false);
        });
        
        this.pack();
    }

    private void buildSellersCombo() {
        List sellers = GameMaster.instance().getSellerList();
        for (Iterator iter = sellers.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            cboSellers.addItem(player);
        }
        if(sellers.size() > 0) {
            updatePropertiesCombo((Player)sellers.get(0));
        }
    }

    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }

    private void updatePropertiesCombo(Player player) {
        cboProperties.removeAllItems();
        Cell[] cells = player.getAllProperties();
        btnOK.setEnabled(cells.length > 0);
        for (int i = 0; i < cells.length; i++) {
            cboProperties.addItem(cells[i]);
        }
    }

}
