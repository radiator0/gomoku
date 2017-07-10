package display;

import connection.Multiplayer;
import connection.Online;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Frame extends JFrame {
    Panel panel = new Panel();
    StartPanel startpanel = new StartPanel(this);
    Online multi;
    int refresh;

    public Frame(int refresh){
        super("GOMOKU");
        this.refresh = refresh;
        add(startpanel);


        pack();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void createGame(){
        multi = new Online();
        JOptionPane.showMessageDialog (null, "GAME ID: "  +  multi.getGameId(), "Game key", JOptionPane.PLAIN_MESSAGE);
        startOnline(refresh, multi);
    }

    public void joinGame(){
        String input = "";
        while(input.length()!=7){
            input = JOptionPane.showInputDialog(null ,"ENTER GAME ID:");
        }
        multi = new Online(input);
        startOnline(refresh, multi);
    }

    private void startOnline(int refresh, Online multi){
        remove(startpanel);
        panel = new Panel(multi);
        add(panel);
        pack();
        this.setLocationRelativeTo(null);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final Timer timer = new Timer(refresh, new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        // panel.repaint();

                        final SwingWorker worker = new SwingWorker() {
                            @Override
                            protected Object doInBackground() throws Exception {
                                // pobiera z neta ruchy
                                if(multi.isMyTurn() && multi.getLastNumber()>0){
                                    panel.outsideMove(multi.getLastSpot());
                                }
                                return null;
                            }
                        };
                        worker.execute();
                    }
                });
                timer.start();
            }
        });
    }


    public Frame(){
        super("Kółko i krzyżyk");
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
