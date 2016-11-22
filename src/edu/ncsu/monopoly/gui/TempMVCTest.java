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
        GameMaster.instance().setNumberOfPlayers(1);
        GameMaster master = GameMaster.instance();
        GameBoard gb = new GameBoardFull();
        Player p = new Player();
        PropertyCell[] cell = new PropertyCell[gb.getPropertyNumberForColor("blue")];
        cell = gb.getPropertiesInMonopoly("blue");
        for (PropertyCell c : cell){
            p.buyProperty(c, c.getPrice());
        }
        String[] monopolies = p.getMonopolies();
        for (String s : monopolies)
            System.out.println(s);
        BuyHouseView view = new BuyHouseView();
        BuyHouseModel model = new BuyHouseModel(p, view);
        view.setVisible(true);
    }
    
}
