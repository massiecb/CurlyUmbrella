package edu.ncsu.monopoly.gui;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.*;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Platform.exit;
/**
 *
 * @author massie
 */
public class TempMVCTest {

    public static void main(String[] args) {
        GameMaster master = GameMaster.instance();
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setGUI(new MockGUI());
        master.setTestMode(true);
        master.reset();
        master.setNumberOfPlayers(1);
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

        BuyHouseView view = new BuyHouseView();
        BuyHouseModel model = new BuyHouseModel(p, view);
        view.setVisible(true);
    }
    
}
