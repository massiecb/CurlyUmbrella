package edu.ncsu.monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameBoard {

    private final ArrayList cells;
    private final ArrayList chanceCards;
    private final HashMap colorGroups;
    private final ArrayList communityChestCards;
    private final ArrayList<Card> drawnChanceCards;
    private final ArrayList<Card> drawnCCCards;

    public GameBoard() {
        this.drawnCCCards = new ArrayList<>();
        this.drawnChanceCards = new ArrayList<>();
        this.communityChestCards = new ArrayList();
        this.colorGroups = new HashMap();
        this.chanceCards = new ArrayList();
        this.cells = new ArrayList();
        Cell go = new GoCell();
        addCell(go);
    }
    
    public void shuffleCards(){
        shuffleCommunityCards();
        shuffleChanceCards();
    }
    
    private void shuffleCommunityCards(){
        Collections.shuffle(communityChestCards);
    }
    
    private void shuffleChanceCards(){
        Collections.shuffle(chanceCards);
    }

    public void addCard(Card card) {
        if (card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }

    public void addToDrawn(Card card) {
        if (card.getCardType() == Card.TYPE_CC) {
            drawnCCCards.add(card);
            if (communityChestCards.isEmpty()) {
                communityChestCards.addAll(drawnCCCards);
                drawnCCCards.removeAll(drawnCCCards);
                shuffleCommunityCards();
            }
        } else {
            drawnChanceCards.add(card);
            if (chanceCards.isEmpty()){
                chanceCards.addAll(drawnChanceCards);
                drawnChanceCards.removeAll(drawnChanceCards);
                shuffleChanceCards();
            }
        }
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void addCell(PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        colorGroups.put(cell.getColorGroup(), new Integer(propertyNumber + 1));
        cells.add(cell);
    }

    public Card drawCCCard() {
        Card card = (Card) communityChestCards.get(0);
        communityChestCards.remove(0);
        addToDrawn(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = (Card) chanceCards.get(0);
        chanceCards.remove(0);
        addToDrawn(card);
        return card;
    }
    public Cell getCell(int newIndex) {
        return (Cell)cells.get(newIndex);
    }

    public int getCellNumber() {
        return cells.size();
    }

    public PropertyCell[] getPropertiesInMonopoly(String color) {
        PropertyCell[] monopolyCells = 
            new PropertyCell[getPropertyNumberForColor(color)];
        int counter = 0;
        for (int i = 0; i < getCellNumber(); i++) {
            Cell c = getCell(i);
            if(c instanceof PropertyCell) {
                PropertyCell pc = (PropertyCell)c;
                if(pc.getColorGroup().equals(color)) {
                    monopolyCells[counter] = pc;
                    counter++;
                }
            }
        }
    return monopolyCells;
    }
	
    public int getPropertyNumberForColor(String name) {
            Integer number = (Integer)colorGroups.get(name);
            if(number != null) {
                return number;
            }
        return 0;
    }

    public Cell queryCell(String string) {
            for(int i = 0; i < cells.size(); i++){
                Cell temp = (Cell)cells.get(i); 
                if(temp.getName().equals(string)) {
                    return temp;
                }
            }  
        return null;
    }
	
    public int queryCellIndex(String string){
        for(int i = 0; i < cells.size(); i++){
            Cell temp = (Cell)cells.get(i); 
            if(temp.getName().equals(string)) {
                return i;
            }
        }
        return -1;
    }

    public void removeCards() {
        communityChestCards.clear();
    }
}
