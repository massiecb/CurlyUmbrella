package edu.ncsu.monopoly.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ncsu.monopoly.RespondDialog;
import edu.ncsu.monopoly.TradeDeal;

/* Refactorings
 * No Longer used in packaage. This implementation mixed Concerns
 * Has been seperated into RespondModel and RespondViewController which 
 * implememnts this class in MVC to seperate the concerns. The new
 * implementation also removes raw types and uses generics instead.
 * The new implementation also doesn't return the Dialog box itself, but rather
 * a boolean to indicate the users input.
 * All depricated functions have been removed in the new implementations.
*/

public class GUIRespondDialog extends JDialog implements RespondDialog {
    private boolean response;
    JTextArea txtMessage = new JTextArea();
    private final int WIDTH = 300;
    private final int HEIGHT = 200;
    
    public GUIRespondDialog() {
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        txtMessage.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        txtMessage.setEditable(false);
        txtMessage.setLineWrap(true);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(txtMessage, BorderLayout.CENTER);
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnYes);
        pnlButtons.add(btnNo);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        btnYes.addActionListener((ActionEvent e) -> {
            response = true;
            setVisible(false);
        });

        btnNo.addActionListener((ActionEvent e) -> {
            response = false;
            setVisible(false);
        });
    
        setModal(true);
        pack();
    }

    @Override
    public boolean getResponse() {
        return response;
    }
    
    public void setDeal(TradeDeal deal) {
        txtMessage.setText(deal.makeMessage());
    }

}
