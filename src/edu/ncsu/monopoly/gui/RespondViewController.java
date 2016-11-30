package edu.ncsu.monopoly.gui;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 *
 * @author massie
 */
public class RespondViewController extends JDialog{
    private  JTextArea message;
    private JButton accept;
    private JButton decline;
    private RespondModel model;
    private RespondViewController view;
    
    public RespondViewController(RespondModel model){
        this.model = model;
        message = new JTextArea();
        accept = new JButton("Yes");
        decline = new JButton("No");
        message.setPreferredSize(new Dimension(300, 200));
        message.setEditable(false);
        message.setLineWrap(true);
        
        setMessageText(model.getDealMessage());
        accept.addActionListener(new AcceptButtonAction());
        decline.addActionListener(new DeclineButtonAction());
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(message, BorderLayout.CENTER);
        JPanel panelButtons = new JPanel();
        panelButtons.add(accept);
        panelButtons.add(decline);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        view = this;
        setModal(true);
        this.pack();
    }
    
    public class AcceptButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.setResponse(true);
            view.dispose();
        }
    }
    
    public class DeclineButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.setResponse(false);
            view.dispose();
            
        }
    }
    private void setMessageText(String offer){
        message.setText(offer);
    }
}
