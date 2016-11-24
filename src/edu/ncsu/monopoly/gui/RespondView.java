package edu.ncsu.monopoly.gui;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author massie
 */
public class RespondView extends JDialog{
    JTextArea message;
    JButton accept;
    JButton decline;
    
    public RespondView(){
        message = new JTextArea();
        accept = new JButton("Yes");
        decline = new JButton("No");
        message.setPreferredSize(new Dimension(300, 200)); // check proper way of ding this
        message.setEditable(false);
        message.setLineWrap(true);
        
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(message, BorderLayout.CENTER);
        JPanel panelButtons = new JPanel();
        panelButtons.add(accept);
        panelButtons.add(decline);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        
        setModal(true);
        this.pack();
    }
    
    public void setMessageText(String offer){
        message.setText(offer);
    }
    
    public void addAcceptButtonListener(ActionListener a){
        accept.addActionListener(a);
    }
    
    public void addDeclineButtonListener(ActionListener a){
        decline.addActionListener(a);
    }
    
    
}
