package edu.ncsu.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import edu.ncsu.monopoly.*;
import edu.ncsu.monopoly.Cell;
import edu.ncsu.monopoly.GameBoard;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.MonopolyGUI;
import edu.ncsu.monopoly.Player;

public class MainWindow extends JFrame implements MonopolyGUI{
   
  
    PlayerPanel[] playerPanels;
    ArrayList guiCells = new ArrayList();
    
    JPanel eastPanel = new JPanel();  
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel westPanel = new JPanel();

    public MainWindow() {
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container c = getContentPane();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        
        setSize(d);
        
        c.add(northPanel, BorderLayout.NORTH);
        c.add(southPanel, BorderLayout.SOUTH);
        c.add(eastPanel, BorderLayout.EAST);
        c.add(westPanel, BorderLayout.WEST);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }   

    private void addCells(JPanel panel, List cells) {
        for(int x=0; x<cells.size(); x++) {
            GUICell cell = new GUICell((Cell)cells.get(x));
            panel.add(cell);
            guiCells.add(cell);
        }
    }

    private void buildPlayerPanels() {
        GameMaster master = GameMaster.instance();
        JPanel infoPanel = new JPanel();
        int players = master.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[master.getNumberOfPlayers()];
        for (int i = 0; i< master.getNumberOfPlayers(); i++){
            playerPanels[i] = new PlayerPanel(master.getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }
    
    public void buildPlayerPanelsTry(int players){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[players];
        for (int i = 0; i < players; i++){
            playerPanels[i] = new PlayerPanel(GameMaster.instance().getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }

    public void enableEndTurnBtn(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);

    }

    public void enablePurchaseBtn(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    public int[] getDiceRoll() {
        TestDiceRollDialog dialog = new TestDiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }

    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }
    
    public boolean isDrawCardButtonEnabled(int currentPlayerIndex){
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }
    
    public boolean isEndTurnButtonEnabled(int currentPlayerIndex){
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }
    
    public boolean isGetOutOfJainButtonEnabled(int currentPlayerIndex){
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
	
    public void movePlayer(int index, int from, int to) {
        GUICell fromCell = queryCell(from);
        GUICell toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(index);
    }
    //TODO: Remove
    public RespondDialog openRespondDialog(TradeDeal deal) {
        GUIRespondDialog dialog = new GUIRespondDialog();
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }
    
    public boolean respondDialog (TradeDeal deal){
        RespondView view = new RespondView();
        RespondModel model = new RespondModel(view, deal);
        view.setVisible(true);
        return model.getResponse();
    }

    public TradeDialog openTradeDialog() {
        //GUITradeDialog dialog = new GUITradeDialog(this);
        //dialog.setVisible(true);
        //return dialog;
        TradeView tradeView = new TradeView(this);
        TradeModel tradeModel = new TradeModel(tradeView);
        tradeView.setVisible(true);
        return tradeModel;
    }
	
    private GUICell queryCell(int index) {
        Cell cell = GameMaster.instance().getGameBoard().getCell(index);
        for(int x = 0; x < guiCells.size(); x++) {
            GUICell guiCell = (GUICell)guiCells.get(x);
            if(guiCell.getCell() == cell) return guiCell;
        }
        return null;
    }

    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }
    
    public void setBuyHouseEnabledTry(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }

    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }
    
    public void setDrawCardEnabledTry(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }

    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }
    
    public void setEndTurnEnabledTry(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setEndTurnEnabled(b);
    }

    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }
    
    public void seteGetOutOfJailEnabled(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }
    
    public void setPurchasePropertyEnabled(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(b);
    }

    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }
    
    public void setRollDiceEnabled(boolean b, int currentPlayerIndex){
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }

    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }
	
    public void setupGameBoard(GameBoard board) {
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
        northPanel.setLayout(new GridLayout(1, dimension.width + 2));
        southPanel.setLayout(new GridLayout(1, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, 0));
        eastPanel.setLayout(new GridLayout(dimension.height, 1));
        addCells(northPanel, GameBoardUtil.getNorthCells(board));
        addCells(southPanel, GameBoardUtil.getSouthCells(board));
        addCells(eastPanel, GameBoardUtil.getEastCells(board));
        addCells(westPanel, GameBoardUtil.getWestCells(board));
        buildPlayerPanels();
    }
    
    public void buildGameBoardTry(GameBoard board){
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
        northPanel.setLayout(new GridLayout(1, dimension.width + 2));
        southPanel.setLayout(new GridLayout(1, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, 0));
        eastPanel.setLayout(new GridLayout(dimension.height, 1));
        addCells(northPanel, GameBoardUtil.getNorthCells(board));
        addCells(southPanel, GameBoardUtil.getSouthCells(board));
        addCells(eastPanel, GameBoardUtil.getEastCells(board));
        addCells(westPanel, GameBoardUtil.getWestCells(board));
    }

    public void showBuyHouseDialog(Player currentPlayer) {
        //BuyHouseDialog dialog = new BuyHouseDialog(currentPlayer);
        //dialog.setVisible(true);
        BuyHouseView buyHouseView = new BuyHouseView();
        BuyHouseModel buyHouseModel = new BuyHouseModel(currentPlayer, buyHouseView);
        buyHouseView.setVisible(true);
        
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public int showUtilDiceRoll() {
        return UtilDiceRoll.showDialog();
    }

    public void startGame() {
        int numberOfPlayers = GameMaster.instance().getNumberOfPlayers();
        for(int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }
    
    public void startGameTry(int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++)
            movePlayer(i, 0, 0);
    }

    public void update() {
        for(int i = 0; i < playerPanels.length; i++) {
            playerPanels[i].displayInfo();
        }
        for(int j = 0; j < guiCells.size(); j++ ) {
            GUICell cell = (GUICell)guiCells.get(j);
            cell.displayInfo();
        }
    }
}
