package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Cell;
import edu.ncsu.monopoly.Player;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author massie
 */
public class TradeView extends JDialog {

    private JButton ok, cancel;
    private JComboBox sellers, properties;
    private JTextField amount;
    private TradeView view;
    private TradeModel model;
    private DefaultComboBoxModel propertiesModel, sellersModel;

    public TradeView(Frame parent, TradeModel model) {
        super(parent);
        this.model = model;
        propertiesModel = new DefaultComboBoxModel();
        sellersModel = new DefaultComboBoxModel();

        setTitle("Trade Property");
        sellers = new JComboBox();
        properties = new JComboBox();
        amount = new JTextField();
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        ok.setEnabled(false);
        setModal(true);
        
        ok.addActionListener(new OkButtonAction());
        cancel.addActionListener(new CancelButtonAction());
        sellers.addItemListener(new sellersUpdateProperties());
        sellers.setModel(sellersModel);
        properties.setModel(propertiesModel);
        populateSellers();

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

        this.view = this;
        this.pack();
    }

    public class OkButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int amount = 0;
            try {
                amount = view.getPrice();
            } catch (NumberFormatException nfe) {
                view.showErrorMessage("Amount should be an integer");
                return;
            }
            if (amount < 0) {
                view.showErrorMessage("Amount should be a positive integer");
                return;
            }
            Cell cell = (Cell) view.properties.getSelectedItem();
            if (cell == null) {
                return;
            }
            Player player = (Player) view.sellers.getSelectedItem();
            Player currentPlayer = model.getCurrentPlayer();
            if (currentPlayer.getMoney() > amount) {
                model.setDeal(amount, cell.getName(), model.getPlayerIndex(player));
            } else {
                view.showErrorMessage("Amount cannot equal or exceed all your money");
                return;
            }
            view.dispose();
        }
    }

    public class CancelButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
    
    public class sellersUpdateProperties implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            Player player = (Player)e.getItem();
            updateProperties(player);
        }
    }

    private void addSellersUpdateListener(ItemListener i) {
        sellers.addItemListener(i);
    }

    private void setSellersModel(ComboBoxModel m) {
        sellers.setModel(m);
    }

    private void setPropertiesModel(ComboBoxModel m) {
        properties.setModel(m);
    }

    private int getPrice() {
        return Integer.parseInt(amount.getText());
    }

    private void showErrorMessage(String s) {
        JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
        this.pack();
    }

    private void populateSellers() {
        for (Player player : model.getSellers()) {
            sellersModel.addElement(player);
        }
    }
    private void updateProperties(Player player){
        propertiesModel.removeAllElements();
        List<Cell> cells = model.getProperties(player);
        if (cells.size() > 0)
            ok.setEnabled(true);
        for (Cell cell : cells) {
            propertiesModel.addElement(cell);
        }  
    }
}
