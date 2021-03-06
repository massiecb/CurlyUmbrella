package edu.ncsu.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;

import edu.ncsu.monopoly.*;
import edu.ncsu.monopoly.Card;
import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;
import java.awt.Color;

public class PlayerPanel extends JPanel {

    private final JButton btnBuyHouse;
    private final JButton btnDrawCard;
    private final JButton btnEndTurn;
    private final JButton btnGetOutOfJail;
    private final JButton btnPurchaseProperty;
    private final JButton btnRollDice;
    private final JButton btnTrade;
    
    private final JLabel lblMoney;
    private final JLabel lblName;
    
    private final Player player;
    
    private final JTextArea txtProperty;

    public PlayerPanel(Player player) {
        JPanel pnlAction = new JPanel();
        JPanel pnlInfo = new JPanel();
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        this.player = player;
        lblName = new JLabel();
        lblMoney = new JLabel();
        txtProperty = new JTextArea(30, 70);
        txtProperty.setDisabledTextColor(Color.black);

        txtProperty.setEnabled(false);

        JPanel pnlName = new JPanel();
        JPanel pnlProperties = new JPanel();

        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.add(pnlName, BorderLayout.NORTH);
        pnlInfo.add(pnlProperties, BorderLayout.CENTER);

        pnlProperties.setLayout(new OverlayLayout(pnlProperties));

        pnlName.add(lblName);
        pnlName.add(lblMoney);
        pnlProperties.add(txtProperty);

        pnlAction.setLayout(new GridLayout(3, 3));
        pnlAction.add(btnBuyHouse);
        pnlAction.add(btnRollDice);
        pnlAction.add(btnPurchaseProperty);
        pnlAction.add(btnGetOutOfJail);
        pnlAction.add(btnEndTurn);
        pnlAction.add(btnDrawCard);
        pnlAction.add(btnTrade);

        pnlAction.doLayout();
        pnlInfo.doLayout();
        pnlName.doLayout();
        pnlProperties.doLayout();
        this.doLayout();

        setLayout(new BorderLayout());
        add(pnlInfo, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.SOUTH);

        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);

        setBorder(new BevelBorder(BevelBorder.RAISED));
    }
    
    public void setBuyHouseActionListener (ActionListener a ){
        btnBuyHouse.addActionListener(a);
    }
    
    public void setRollDiceActionListener (ActionListener a) {
        btnRollDice.addActionListener(a);
    }
    
    public void setPurchasePropertyActionListener (ActionListener a) {
        btnPurchaseProperty.addActionListener(a);
    }
    
    public void setGetOutOfJailActionListener (ActionListener a) {
        btnGetOutOfJail.addActionListener(a);
    }
    
    public void setEndTurnActionListener (ActionListener a) {
        btnEndTurn.addActionListener(a);
    }
    
    public void setDrawCardActionListener (ActionListener a) {
        btnDrawCard.addActionListener(a);
    }
    
    public void setTradeActionListener (ActionListener a) {
        btnTrade.addActionListener(a);
    }

    public void displayInfo() {
        lblName.setText(player.getName());
        lblMoney.setText("$ " + player.getMoney());
        StringBuilder buf = new StringBuilder();
        Cell[] cells = player.getAllProperties();
        for (int i = 0; i < cells.length; i++) {
            buf.append(cells[i]).append("\n");
        }
        txtProperty.setText(buf.toString());
    }
    
    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }
    
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }
    
    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }
    
    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }

    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setEnabled(b);
    }

    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setEnabled(b);
    }

    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setEnabled(b);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    public void setTradeEnabled(boolean b) {
        btnTrade.setEnabled(b);
    }
}