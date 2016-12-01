package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.GameBoard;
import edu.ncsu.monopoly.GameBoardFull;
import edu.ncsu.monopoly.GameMaster;

/**
 *
 * @author massie
 */
public class TestUtilityDiceRollMVC {

    public static void main(String[] args) {
        GameMaster master = GameMaster.instance();
        MainWindow w = new MainWindow();
        GameBoard gb = new GameBoardFull();
        master.setGameBoard(gb);
        GameMaster.instance().setNumberOfPlayers(1);
        GameMaster.instance().getPlayer(0).setName("test");
        w.setupGameBoard(gb); 
        master.setGUI(w);
        System.out.println(w.showUtilDiceRoll());
    }
    
}
