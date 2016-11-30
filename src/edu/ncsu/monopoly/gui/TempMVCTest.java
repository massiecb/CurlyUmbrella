package edu.ncsu.monopoly.gui;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.*;

/**
 *
 * @author massie
 */
public class TempMVCTest {

    public static void main(String[] args) {
        // TODO code application logic here
        GameMaster master = GameMaster.instance();
        MainWindow w = new MainWindow();
        GameBoard gb = new GameBoardFull();
        master.setGameBoard(gb);
        GameMaster.instance().setNumberOfPlayers(1);
        GameMaster.instance().getPlayer(0).setName("test");
        w.setupGameBoard(gb); 
        master.setGUI(w);
        master.startGame();        
        master.movePlayer(0, 1);
        Player p = master.getPlayer(0);
        p.purchase();
        master.movePlayer(0, 1);
        p.purchase();
        master.movePlayer(0, 1);
        p.purchase();
        master.movePlayer(0, 1);
        p.purchase();
        master.movePlayer(0, 1);
        p.purchase();

        BuyHouseModel model = new BuyHouseModel(p);
        BuyHouseViewController view = new BuyHouseViewController(model);
        view.setVisible(true);
    }
    
}
