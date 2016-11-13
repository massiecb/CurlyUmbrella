package edu.ncsu.monopoly;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author massie
 */
public class GameBoardTest {
    GameBoard GB;
    
    public GameBoardTest() {
    }

    @Test
    public void testCCLastCardDrawn() {
        GB = new GameBoard();
        MoneyCard cardOne = new MoneyCard("Win $50", 50, Card.TYPE_CC);
        GB.addCard(cardOne);
        GB.drawCCCard();
        assertEquals(GB.drawCCCard().getLabel(), cardOne.getLabel());
    }
    
    @Test
    public void testCCMoreThanOneCard(){
        GB = new GameBoard();
        MoneyCard cardOne = new MoneyCard("Win $50", 50, Card.TYPE_CC);
        MoneyCard cardTwo = new MoneyCard("Win $51", 51, Card.TYPE_CC);
        GB.addCard(cardOne);
        GB.addCard(cardTwo);
        assertEquals(GB.drawCCCard().getLabel(), cardOne.getLabel());
        assertEquals(GB.drawCCCard().getLabel(), cardTwo.getLabel());
    }
    
    @Test
    public void testChanceCardsLastDrawn(){
        GB = new GameBoard();
        MoneyCard cardOne = new MoneyCard("Win $50", 50, Card.TYPE_CHANCE);
        GB.addCard(cardOne);
        GB.drawChanceCard();
        assertEquals(GB.drawChanceCard().getLabel(), cardOne.getLabel());
    }
    @Test
    public void testChanceMoreThanOneCard(){
        GB = new GameBoard();
        MoneyCard cardOne = new MoneyCard("Win $50", 50, Card.TYPE_CHANCE);
        MoneyCard cardTwo = new MoneyCard("Win $51", 51, Card.TYPE_CHANCE);
        GB.addCard(cardOne);
        GB.addCard(cardTwo);
        assertEquals(GB.drawChanceCard().getLabel(), cardOne.getLabel());
        assertEquals(GB.drawChanceCard().getLabel(), cardTwo.getLabel());
    }
}
