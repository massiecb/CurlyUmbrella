package edu.ncsu.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Player {
    private final HashMap colorGroups;
    private boolean inJail;
    private int money;
    private String name;

    private Cell position;
    private ArrayList properties;
    private ArrayList railroads;
    private ArrayList utilities;

    public Player() {
        this.utilities = new ArrayList();
        this.railroads = new ArrayList();
        this.properties = new ArrayList();
        this.colorGroups = new HashMap();
        GameBoard gb = GameMaster.instance().getGameBoard();
        inJail = false;
        if(gb != null) {
            position = gb.queryCell("Go");
        }
    }

    public void buyProperty(Cell property, int amount) {
        property.setPlayer(this);
        if(property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell)property;
            properties.add(cell);
            colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup())+1);
        }
        if(property instanceof RailRoadCell) {
            railroads.add(property);
            colorGroups.put(RailRoadCell.COLOR_GROUP, getPropertyNumberForColor(RailRoadCell.COLOR_GROUP)+1);
        }
        if(property instanceof UtilityCell) {
            utilities.add(property);
            colorGroups.put(UtilityCell.COLOR_GROUP, getPropertyNumberForColor(UtilityCell.COLOR_GROUP)+1);
        }
        setMoney(getMoney() - amount);
    }
	
    public boolean canBuyHouse() {
        return (getMonopolies().length != 0);
    }

    public boolean checkProperty(String property) {
        for(int i=0;i<properties.size();i++) {
            Cell cell = (Cell)properties.get(i);
            if(cell.getName().equals(property)) {
                return true;
            }
        }
    return false;	
    }
    
    public void releaseProperties() {
        if (properties.size() > 0) {
            for (int i = 0; i <= properties.size(); i++) {
                PropertyCell cell = getProperty(i);
                properties.remove(i);
                cell.setAvailable(true);
                cell.setPlayer(null);
                cell.setNumHouses(0);
            }
        }
        
        if (utilities.size() > 0) {
            for (int i = 0; i <= utilities.size(); i++) {
                UtilityCell cell = (UtilityCell) utilities.get(i);
                utilities.remove(i);
                cell.setAvailable(true);
                cell.setPlayer(null);
            }  
        }
        
         if (railroads.size() > 0) {
            for (int i = 0; i <= railroads.size(); i++) {
                RailRoadCell cell = (RailRoadCell) railroads.get(i);
                railroads.remove(i);
                cell.setAvailable(true);
                cell.setPlayer(null);
            }  
        }
    }
	
    public void exchangeProperty(Player player) {
        for(int i = 0; i < getPropertyNumber(); i++ ) {
            PropertyCell cell = getProperty(i);
            cell.setPlayer(player);
            if(player == null) {
                cell.setAvailable(true);
                cell.setNumHouses(0);
            }
            else {
                player.properties.add(cell);
                colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup())+1);
            }
        }
    properties.clear();
    }
	
    public String[] getMonopolies() {
        ArrayList monopolies = new ArrayList();
        Iterator colors = colorGroups.keySet().iterator();
        while(colors.hasNext()) {
            String color = (String)colors.next();
    if(!(color.equals(RailRoadCell.COLOR_GROUP)) && !(color.equals(UtilityCell.COLOR_GROUP))) {
        Integer num = (Integer)colorGroups.get(color);
        GameBoard gameBoard = GameMaster.instance().getGameBoard();
        if(num == gameBoard.getPropertyNumberForColor(color)) {
            monopolies.add(color);
            }
        }   
    }
    return (String[])monopolies.toArray(new String[monopolies.size()]);
}
    
    public void getOutOfJail() {
        money -= JailCell.BAIL;
        if(isBankrupt()) {
            money = 0;
            exchangeProperty(null);
        }
        inJail = false;
        GameMaster.instance().updateGUI();
    }
    
    private int getPropertyNumberForColor(String name) {
        Integer number = (Integer)colorGroups.get(name);
        if(number != null) {
            return number;
        }
        return 0;
    }
    
    public void payRentTo(Player owner, int rentValue) {
        if(money < rentValue) {
            owner.money += money;
            money -= rentValue;
        }
        else {
            money -= rentValue;
            owner.money +=rentValue;
        }
        if(isBankrupt()) {
            money = 0;
            exchangeProperty(owner);
        }
    }
    
    public void purchase() {
        if(getPosition().isAvailable()) {
            Cell c = getPosition();
            c.setAvailable(false);
            if(c instanceof PropertyCell) {
                PropertyCell cell = (PropertyCell)c;
                purchaseProperty(cell);
            }
            if(c instanceof RailRoadCell) {
                RailRoadCell cell = (RailRoadCell)c;
                purchaseRailRoad(cell);
            }
            if(c instanceof UtilityCell) {
                UtilityCell cell = (UtilityCell)c;
                purchaseUtility(cell);
            }
        }
    }
    
    public void purchaseHouse(String selectedMonopoly, int houses) {
        GameBoard gb = GameMaster.instance().getGameBoard();
        PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
        if((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            for (PropertyCell cell : cells) {
                int newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    this.setMoney(money - (cell.getHousePrice() * houses));
                    GameMaster.instance().updateGUI();
                }
            }
        }
    }
	
    private void purchaseProperty(PropertyCell cell) {
    buyProperty(cell, cell.getPrice());
    }

    private void purchaseRailRoad(RailRoadCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    private void purchaseUtility(UtilityCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    public void sellProperty(Cell property, int amount) {
        property.setPlayer(null);
        if(property instanceof PropertyCell) {
            properties.remove(property);
        }
        if(property instanceof RailRoadCell) {
            railroads.remove(property);
        }
        if(property instanceof UtilityCell) {
            utilities.remove(property);
        }
        setMoney(getMoney() + amount);
    }
    
    public void resetProperty() {
    	properties = new ArrayList();
    	railroads = new ArrayList();
    	utilities = new ArrayList();
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public Cell[] getAllProperties() {
        ArrayList list = new ArrayList();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return (Cell[])list.toArray(new Cell[list.size()]);
    }
    
    public boolean isBankrupt() {
        return money <= 0;
    }
    
    public int numberOfRR() {
        return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
    }

    public int numberOfUtil() {
        return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
    }
    
    public int getMoney() {
        return this.money;
    }

    public String getName() {
        return name;
    }

    public Cell getPosition() {
        return this.position;
    }
	
    public PropertyCell getProperty(int index) {
        return (PropertyCell)properties.get(index);
    }

    public int getPropertyNumber() {
        return properties.size();
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Cell newPosition) {
        this.position = newPosition;
    }
}