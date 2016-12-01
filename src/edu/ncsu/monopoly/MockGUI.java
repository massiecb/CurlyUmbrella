package edu.ncsu.monopoly;

import java.util.ArrayList;
import java.util.List;

public class MockGUI implements MonopolyGUI {
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;
    private final boolean[] btnTradeState = new boolean[2];

    @Override
    public void enableEndTurnBtn(int playerIndex) {
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
    }
    
    @Override
    public List<Integer> getDiceRoll() {
        List<Integer> roll = new ArrayList<>();
        roll.add(0, 2);
        roll.add(1, 3);
        return roll;
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }
	
    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJailState;
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
    }

    @Override
    public void movePlayer(int index, int from, int to) {
    }

    @Override
    public TradeDialog openTradeDialog() {
        TradeDialog dialog = new MockTradeDialog();
        return dialog;
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
    	this.btnGetOutOfJailState = b;
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
    }

    @Override
    public void setTradeEnabled(boolean b) {
        this.btnTradeState[GameMaster.instance().getCurrentPlayerIndex()] = b;
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
    }

    @Override
    public void showMessage(String string) {
    }

    @Override
    public void showUtilDiceRoll() {
//      int[] diceValues = GameMaster.instance().rollDice();
//      return diceValues[0] + diceValues[1];
        //return 10;
    }

    @Override
    public void startGame() {
    }

    @Override
    public void update() {
    }

    @Override
    public boolean respondDialog(TradeDeal deal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
