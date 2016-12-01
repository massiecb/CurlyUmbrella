package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.GameMaster;
import java.util.List;


/**
 *
 * @author massie
 */
public class UtilDiceRollModel {
    private int diceValue;
    private List<Integer> diceRoll;
    
    public UtilDiceRollModel(){
        
    }
    
    public void rollDice() {
        diceRoll = GameMaster.instance().rollDice();
        diceValue = diceRoll.get(0) + diceRoll.get(1);
    }
    
    public int getDiceValue(){
        return diceValue;
    }
    
}
