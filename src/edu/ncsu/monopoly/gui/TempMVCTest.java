package edu.ncsu.monopoly.gui;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author massie
 */
public class TempMVCTest {

    /**
     * @param args the command line arguments
     */
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
        /*GameMaster master = GameMaster.instance();
 
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setGUI(new MockGUI());
        master.setTestMode(true);
        master.reset();
        master.setNumberOfPlayers(1); */
        System.out.println(master.getPlayer(0).getPosition());
        master.movePlayer(0, 1);
        Player p = master.getPlayer(0);
        p.purchase();
        master.movePlayer(0, 1);
        System.out.println(p.getPosition());
        p.purchase();
        master.movePlayer(0, 1);
        System.out.println(p.getPosition());
        p.purchase();
        master.movePlayer(0, 1);
        p.purchase();
        System.out.println(p.getPosition());
        master.movePlayer(0, 1);
        System.out.println(p.getPosition());
        p.purchase();

        BuyHouseView view = new BuyHouseView();
        BuyHouseModel model = new BuyHouseModel(p, view);
        view.setVisible(true);
    }
    
}
