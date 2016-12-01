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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements MonopolyGUI{
   
  
    private List<PlayerPanel> playerPanels;
    private List<GUICell> guiCells;
    
    JPanel eastPanel = new JPanel();  
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel westPanel = new JPanel();

    public MainWindow() {
        guiCells = new ArrayList<>();
        
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
            @Override
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
        playerPanels = new ArrayList<>();
        for (int i = 0; i< master.getNumberOfPlayers(); i++){
            playerPanels.add(i, new PlayerPanel(master.getPlayer(i)));
            infoPanel.add(playerPanels.get(i));
            playerPanels.get(i).displayInfo();
        }
        
        addActionListeners();
    }

    @Override
    public void enableEndTurnBtn(int playerIndex) {
        playerPanels.get(playerIndex).setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels.get(playerIndex).setRollDiceEnabled(true);
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
        playerPanels.get(playerIndex).setPurchasePropertyEnabled(true);
    }

    @Override
    public List<Integer> getDiceRoll() {
        TestDiceRollDialog dialog = new TestDiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels.get(currentPlayerIndex).isDrawCardButtonEnabled();
    }
    

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels.get(currentPlayerIndex).isEndTurnButtonEnabled();
    }
    

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels.get(currentPlayerIndex).isGetOutOfJailButtonEnabled();
    }
    

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels.get(i).isTradeButtonEnabled();
    }
	
    @Override
    public void movePlayer(int index, int from, int to) {
        GUICell fromCell = queryCell(from);
        GUICell toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(index);
    }
    
    @Override
    public boolean respondDialog (TradeDeal deal){
        RespondModel model = new RespondModel(deal);
        RespondViewController view = new RespondViewController(model);
        view.setVisible(true);
        return model.getResponse();
    }

    @Override
    public TradeDialog openTradeDialog() {
        TradeModel tradeModel = new TradeModel();
        TradeView tradeView = new TradeView(this, tradeModel);
        tradeView.setVisible(true);
        return tradeModel;
    }
	
    private GUICell queryCell(int index) {
        Cell cell = GameMaster.instance().getGameBoard().getCell(index);
        for (GUICell guiCell : guiCells) {
            if(guiCell.getCell() == cell) 
                return guiCell;
        }
        return null;
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setBuyHouseEnabled(b);
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setDrawCardEnabled(b);
    }

    @Override
    public void setEndTurnEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setEndTurnEnabled(b);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setGetOutOfJailEnabled(b);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setPurchasePropertyEnabled(b);
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels.get(currentPlayerIndex).setRollDiceEnabled(b);
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        playerPanels.get(index).setTradeEnabled(b);
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

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        BuyHouseModel buyHouseModel = new BuyHouseModel(currentPlayer);
        BuyHouseViewController buyHouseView = new BuyHouseViewController(buyHouseModel);
        buyHouseView.setVisible(true);
        
    }

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    @Override
    public int showUtilDiceRoll() {
        UtilDiceRollModel model = new UtilDiceRollModel();
        UtilDiceRollViewController view = new UtilDiceRollViewController(model);
        view.setVisible(true);
        return model.getDiceValue();
    }

    @Override
    public void startGame() {
        int numberOfPlayers = GameMaster.instance().getNumberOfPlayers();
        for(int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < playerPanels.size(); i++) {
            playerPanels.get(i).displayInfo();
        }
        for(int j = 0; j < guiCells.size(); j++ ) {
            GUICell cell = (GUICell)guiCells.get(j);
            cell.displayInfo();
        }
    }
    
    private void addActionListeners(){
        for (PlayerPanel panel : playerPanels){
            panel.setBuyHouseActionListener (new BuyHouseButtonAction());
            panel.setRollDiceActionListener (new RollDiceButtonAction());
            panel.setPurchasePropertyActionListener (new PurchasePropertyButtonAction());
            panel.setGetOutOfJailActionListener (new GetOutOfJailButtonAction());
            panel.setEndTurnActionListener (new EndTurnButtonAction());
            panel.setDrawCardActionListener (new DrawCardButtonAction());
            panel.setTradeActionListener (new TradeButtonAction());
        }
        
    }
    
    private class BuyHouseButtonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            showBuyHouseDialog(GameMaster.instance().getCurrentPlayer());
        }
    }
    
    private class RollDiceButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameMaster.instance().btnRollDiceClicked();
        }
    }
    
    private class PurchasePropertyButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameMaster.instance().btnPurchasePropertyClicked();
        }
    }
    
    private class GetOutOfJailButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameMaster.instance().btnGetOutOfJailClicked();
        }
    }
    
    private class EndTurnButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameMaster.instance().btnEndTurnClicked();
        }
    }
    
    private class DrawCardButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Card card = GameMaster.instance().btnDrawCardClicked();
            JOptionPane.showMessageDialog(playerPanels.get(GameMaster.instance().getCurrentPlayerIndex()), card.getLabel());
        }
    }
    
    private class TradeButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameMaster.instance().btnTradeClicked();
        }
        
    }
}
