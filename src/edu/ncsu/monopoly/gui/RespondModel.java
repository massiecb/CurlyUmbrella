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
    private RespondView view;
    
    RespondModel(RespondView view, TradeDeal deal){
        this.view = view;
        view.addAcceptButtonListener(new AcceptButtonAction());
        view.addDeclineButtonListener(new DeclineButtonAction());
        view.setMessageText(deal.makeMessage());
    }
    
    public class AcceptButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.dispose();
            response = true;
        }
    }
    
    public class DeclineButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.dispose();
            response = false;
        }
    }
    
    @Override
    public boolean getResponse() {
        return response;
    }
    
}
