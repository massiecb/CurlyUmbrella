package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Player;

public class BuyHouseModel {
    private final Player player;
    
    public BuyHouseModel(Player player) {
        this.player = player;
    }
    
    public String[] getMonopolies(){
        return player.getMonopolies();
    }
    
    public void purchaseHouse(String monopoly, int numberOfHouses){
        player.purchaseHouse(monopoly, numberOfHouses);
    }
}
