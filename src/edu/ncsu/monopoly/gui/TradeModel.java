package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Cell;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.TradeDeal;
import edu.ncsu.monopoly.TradeDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author massie
 */
public class TradeModel implements TradeDialog {

    private TradeDeal deal;
    private final TradeView view;
    private final DefaultComboBoxModel propertiesModel, sellersModel;

    public TradeModel(TradeView view) {
        propertiesModel = new DefaultComboBoxModel();
        sellersModel = new DefaultComboBoxModel();
        this.view = view;
        view.addOKButtonListener(new OkButtonAction());
        view.addCancelButtonListener(new CancelButtonAction());
        view.addSellersUpdateListener(new sellersUpdateProperties());
        view.setSellersModel(sellersModel);
        view.setPropertiesModel(propertiesModel);
        populateSellersComboBox();
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
            Cell cell = (Cell) view.getProperties().getSelectedItem();
            if (cell == null) {
                return;
            }
            Player player = (Player) view.getSellers().getSelectedItem();
            Player currentPlayer = GameMaster.instance().getCurrentPlayer();
            if (currentPlayer.getMoney() > amount) {
                deal = new TradeDeal();
                deal.setAmount(amount);
                deal.setPropertyName(cell.getName());
                deal.setSellerIndex(GameMaster.instance().getPlayerIndex(player));
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

    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }
    
    private void populateSellersComboBox(){
        List sellers = GameMaster.instance().getSellerList();
        for (Iterator iter = sellers.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            sellersModel.addElement(player);
        }
    }
    

    
    private void updateProperties(Player p){
        propertiesModel.removeAllElements();
        Cell[] cells = p.getAllProperties();
        if (cells.length > 0)
            view.enableOK();
        for (Cell cell : cells) {
            propertiesModel.addElement(cell);
        }
        
    }

}
