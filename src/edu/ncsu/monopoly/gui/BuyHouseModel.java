package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Player;
import java.util.ArrayList;
import java.util.List;

public class BuyHouseModel {
    private final Player player;
    
    public BuyHouseModel(Player player) {
        this.player = player;
    }
    
    public List<String> getMonopolies(){
        List<String> monopoly = new ArrayList<>();
        for (String s : player.getMonopolies())
            monopoly.add(s);
        return monopoly;
    }
    
    public void purchaseHouse(String monopoly, int numberOfHouses){
        player.purchaseHouse(monopoly, numberOfHouses);
    }
}
