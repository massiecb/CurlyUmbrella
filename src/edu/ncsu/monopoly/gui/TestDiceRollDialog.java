package edu.ncsu.monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class TestDiceRollDialog extends JDialog {
    private JButton btnOK, btnCancel;
    private JTextField txtDiceRoll;
    private List<Integer> diceRoll;
    
    public TestDiceRollDialog(Frame parent) {
        super(parent);
        
        setTitle("Dice Roll Dialog");
        txtDiceRoll = new JTextField(2);
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        setModal(true);
             
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtDiceRoll);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener((ActionEvent e) -> {
            TestDiceRollDialog.this.hide();
            diceRoll = new ArrayList<>();
            diceRoll.add(0, 0);
            diceRoll.add(0, 0);
        });
        
        btnOK.addActionListener((ActionEvent e) -> {
            int amount = 0;
            try{
                amount = Integer.parseInt(txtDiceRoll.getText());
            } catch(NumberFormatException nfe) {
                JOptionPane.showMessageDialog(TestDiceRollDialog.this,
                        "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(amount > 0) {
                diceRoll = new ArrayList<>();
                if((amount % 2) == 0) {
                    diceRoll.add(0, amount/2);
                    diceRoll.add(0,0);
                }
                else {
                    diceRoll.add (0, amount/2);
                    diceRoll.add  (0, (amount / 2) + 1);
                }
            }
            setVisible(false);
        });
        
        this.pack();
    }

    public List<Integer> getDiceRoll() {
        return diceRoll;
    }
}
