package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.CardCell;
import edu.ncsu.monopoly.GameMaster;

/**
 *
 * @author massie
 */
public class MonopolyController {/*
    MainWindow view;
    
    public MonopolyController(MainWindow view){
        this.view = view;
        view.buildGameBoardTry(GameMaster.instance().getGameBoard());
        numberOfPlayerPanels();
    }
    
    public void numberOfPlayerPanels(){
        GameMaster master = GameMaster.instance();
        view.buildPlayerPanelsTry(master.getNumberOfPlayers(), GameMaster.instance());
    }
    
    
    public void btnBuyHouseClicked() {
        view.showBuyHouseDialog(GameMaster.instance().getCurrentPlayer());
    }
    public void btnDrawCardClicked() {
        view.setDrawCardEnabled(false);
        CardCell cell = (CardCell)GameMaster.instance().getCurrentPlayer().getPosition();
        GameMaster.instance().cardDrawn(cell);
    }
    
    public void btnEndTurnClicked() {
        view.setAllButtonsEnabled(false);
        GameMaster.instance().getCurrentPlayer().getPosition().playAction();
        if(GameMaster.instance().getCurrentPlayer().isBankrupt())
            playerIsBankrupt();
        else
            GameMaster.instance().switchTurn();
        //updateGUI();
    }
    
    public void btnGetOutOfJailClicked() {
        GameMaster.instance().getCurrentPlayer().getOutOfJail();
        if(GameMaster.instance().getCurrentPlayer().isBankrupt())
            playerIsBankrupt();
        else {
            view.setRollDiceEnabled(true);
            view.setBuyHouseEnabled(GameMaster.instance().getCurrentPlayer().canBuyHouse());
            view.setGetOutOfJailEnabled(GameMaster.instance().getCurrentPlayer().isInJail());
        }
        //upDateGUI();
    }
    public void btnPurchasePropertyClicked() {
        GameMaster.instance().playerPurchaseProperty();
        view.setPurchasePropertyEnabled(false);
        //updateGUI();
    }
    
    public void btnRollDiceClicked() {
        int[] dice = GameMaster.instance().rollDice();
            view.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append(GameMaster.instance().getCurrentPlayer().getName())
                .append(", you rolled ")
                .append(dice[0])
                .append(" and ")
                .append(dice[1]);
            view.showMessage(msg.toString());
            GameMaster.instance().movePlayerTry(dice[0] + dice[1]);
            view.setBuyHouseEnabled(false);
    }
    
    public void btnTradeClicked() {
        TradeView tradeView = new TradeView(view);
        TradeModel tradeModel = new TradeModel(tradeView);
        boolean isAccepted = tradeModel.isTradeAccepted();
        if (isAccepted)
            GameMaster.instance().completeTrade(tradeModel.getTradeDeal());
        //updateGUI();
    }
    
    public void sendToJail(){
        
    }
    
    private void movePlayer(int[] dice){
        GameMaster.instance().movePlayerTry(dice[0] + dice[1]);
        
        //view.movePlayer(GameMaster.instance().getPlayerIndex(GameMaster.instance().getCurrentPlayer()), GameMaster.instance().getCurrentPlayer().getPosition(), 5);
    }
    
    private void playerIsBankrupt() {
            view.setBuyHouseEnabled(false);
            view.setDrawCardEnabled(false);
            view.setEndTurnEnabled(false);
            view.setGetOutOfJailEnabled(false);
            view.setPurchasePropertyEnabled(false);
            view.setRollDiceEnabled(false);
            view.setTradeEnabled(GameMaster.instance().getCurrentPlayerIndex(),false);
    }
    */
}
