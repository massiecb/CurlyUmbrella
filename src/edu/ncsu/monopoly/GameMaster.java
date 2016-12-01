package edu.ncsu.monopoly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameMaster {

    private static GameMaster gameMaster;
    static final public int MAX_PLAYER = 8;
    private final List<Die> dice;
    private GameBoard gameBoard;
    private int initAmountOfMoney;
    private final List<Player> players;
    private int turn = 0;
    private int utilDiceRoll;
    private boolean testMode;
    private TradeDeal deal;

    public static GameMaster instance() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }

    private GameMaster() {
        this.players = new ArrayList<>();
        initAmountOfMoney = 1500;
        dice = new ArrayList<>();
        dice.add(new Die());
        dice.add(new Die());
    }

    public Card drawnCard() {
        CardCell cell = (CardCell) getCurrentPlayer().getPosition();
        Card card;
        if (cell.getType() == Card.TYPE_CC) {
            card = getGameBoard().drawCCCard();

        } else {
            card = getGameBoard().drawChanceCard();
        }
        return card;
    }

    public void doCard(Card card) {
        Card c = card;
        c.applyAction();
    }

    public void doCellAction() {
        getCurrentPlayer().getPosition().playAction();
    }

    public void leaveJail() {
        getCurrentPlayer().getOutOfJail();
    }

    public void playerPurchaseProperty() {
        Player player = getCurrentPlayer();
        player.purchase();
    }

    public void diceRoll() {
        List<Integer> rolls = rollDice();
        if ((rolls.get(0) + rolls.get(1)) > 0) {
            Player player = getCurrentPlayer();
            movePlayer(player, rolls.get(0) + rolls.get(1));
        }
    }

    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }

    public Player getCurrentPlayer() {
        return getPlayer(turn);
    }

    public int getCurrentPlayerIndex() {
        return turn;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getInitAmountOfMoney() {
        return initAmountOfMoney;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfSellers() {
        return players.size() - 1;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public ArrayList getSellerList() {
        ArrayList sellers = new ArrayList();
        for (Player player : players) {
            if (player != getCurrentPlayer()) {
                sellers.add(player);
            }
        }
        return sellers;
    }

    public int getTurn() {
        return turn;
    }

    public int getUtilDiceRoll() {
        return this.utilDiceRoll;
    }

    public void movePlayer(int playerIndex, int diceValue) {
        Player player = players.get(playerIndex);
        movePlayer(player, diceValue);
    }

    public void movePlayer(Player player, int diceValue) {
        Cell currentPosition = player.getPosition();
        int positionIndex = gameBoard.queryCellIndex(currentPosition.getName());
        int newIndex = (positionIndex + diceValue) % gameBoard.getCellNumber();
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellNumber()) {
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
    }

    public int getCellIndex(Cell cell) {
        return gameBoard.queryCellIndex(cell.getName());
    }

    public boolean isCellPurchaseable() {
        return getCurrentPlayer().getPosition().isAvailable();
    }

    public boolean playerCanAffordProperty() {
        Cell currentCell = getCurrentPlayer().getPosition();
        return currentCell.getPrice() <= getCurrentPlayer().getMoney() && currentCell.getPrice() > 0;
    }

    public void reset() {
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        if (gameBoard != null) {
            gameBoard.removeCards();
        }
        turn = 0;
    }

    public List<Integer> rollDice() {
        List<Integer> returnDice = new ArrayList<>();
        returnDice.add(dice.get(0).getRoll());
        returnDice.add(dice.get(1).getRoll());
        return returnDice;
    }

    public void sendToJail(Player player) {
        int oldPosition = gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
        player.setPosition(gameBoard.queryCell("Jail"));
        player.setInJail(true);
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }

    public void setInitAmountOfMoney(int money) {
        this.initAmountOfMoney = money;
    }

    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            player.setMoney(initAmountOfMoney);
            players.add(player);
        }
    }

    public void setUtilDiceRoll(int diceRoll) {
        this.utilDiceRoll = diceRoll;
    }

    public void switchTurn() {
        turn = (turn + 1) % getNumberOfPlayers();
        if (getCurrentPlayer().isBankrupt()) {
            getCurrentPlayer().releaseProperties();
            turn = (turn + 1) % getNumberOfPlayers();
        }
    }

    public void setTestMode(boolean b) {
        testMode = b;
    }

    public List<Integer> getDiceRolls() {
        List<Integer> diceValues = new ArrayList<>();
        diceValues.add(0, dice.get(0).getRoll());
        diceValues.add(1, dice.get(0).getRoll());
        return diceValues;
    }
}
