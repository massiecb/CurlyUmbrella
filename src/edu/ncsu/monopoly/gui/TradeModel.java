package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Cell;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.TradeDeal;
import edu.ncsu.monopoly.TradeDialog;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author massie
 */
public class TradeModel implements TradeDialog {

    private TradeDeal deal;

    public TradeModel() {
    }
    
    public void setDeal(int amount, String propertyName, int sellerIndex){
        deal = new TradeDeal();
        deal.setAmount(amount);
        deal.setPropertyName(propertyName);
        deal.setSellerIndex(sellerIndex);
    }
    
    public Player getCurrentPlayer(){
        return GameMaster.instance().getCurrentPlayer();
    }
    
    public int getPlayerIndex(Player player){
        return GameMaster.instance().getPlayerIndex(player);
    }
    
    public List<Player> getSellers(){
        List <Player> sellers = GameMaster.instance().getSellerList();
        return sellers;
    }
    
    public List<Cell> getProperties(Player player){
        List<Cell> cells = new ArrayList<>();
        for (Cell c : player.getAllProperties())
            cells.add(c);
        return cells;
    }
    
    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }
    
}
