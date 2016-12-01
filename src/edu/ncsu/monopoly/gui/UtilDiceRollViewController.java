package edu.ncsu.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ncsu.monopoly.GameMaster;
import java.awt.event.ActionListener;
import java.util.List;

public class UtilDiceRollViewController extends JDialog {
    
    private final JButton rollDice;
    private final JButton ok;
    private int diceValue;
    private JLabel prompt;
    private final UtilDiceRollViewController view;
    private UtilDiceRollModel model;

    public UtilDiceRollViewController(UtilDiceRollModel model) {
        this.model = model;
        
        rollDice = new JButton("Roll the Dice!");
        ok = new JButton("OK");
        prompt = new JLabel();
        
        setModal(true);
        ok.setEnabled(false);
        ok.addActionListener (new OkButtonAction());
        rollDice.addActionListener(new RollDiceButtonAction());
        prompt.setText("Please roll the dice to determine your utility bill.");
        
        Container contentPane = getContentPane();
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(rollDice);
        pnlButtons.add(ok);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(prompt, BorderLayout.CENTER);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        view = this;
        this.pack();
    }
    
    public class OkButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
    
    public class RollDiceButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.rollDice();
            prompt.setText ("You rolled " + model.getDiceValue());
            rollDice.setEnabled(false);
            ok.setEnabled(true);
        }
        
    }
    
}
