package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.RespondDialog;
import edu.ncsu.monopoly.TradeDeal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author massie
 */
public class RespondModel implements RespondDialog{
    
    private boolean response;
    private TradeDeal deal;
    
    RespondModel(TradeDeal deal){
        this.deal = deal;
    }
    @Override
    public boolean getResponse() {
        return response;
    }
    
    public String getDealMessage(){
        return deal.makeMessage();
    }
    
    public void setResponse(boolean b){
        response = b;
    }
}
