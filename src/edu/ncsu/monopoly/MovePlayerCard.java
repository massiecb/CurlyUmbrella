package edu.ncsu.monopoly;


public class MovePlayerCard extends Card {
    
    private final String destination;
    private final int type;

    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = GameMaster.instance().getGameBoard().queryCellIndex(destination);
        int currentCell = GameMaster.instance().getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if(currentCell > newCell) {
            diceValue = (GameMaster.instance().getGameBoard().getCellNumber() + 
                (newCell - currentCell));
        }
        else if(currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        GameMaster.instance().movePlayer(currentPlayer, diceValue);
    }

    @Override
    public int getCardType() {
        return type;
    }

    @Override
    public String getLabel() {
        return "Go to " + destination;
    }

}
