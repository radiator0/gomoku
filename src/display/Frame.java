package display;

import connection.Online;
import game.Bot.Bot;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-04-11
 */
public class Frame extends JFrame {
    GamePanel panel;
    StartPanel startpanel = new StartPanel(this);
    LobbyPanel lobbypanel;
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

    public void backToStartpanel(JPanel panel){
        remove(panel);
        add((startpanel = new StartPanel(this)));
        pack();
    }

    public void createGame(){
        remove(startpanel);
        lobbypanel  = new LobbyPanel(this,true);
        add(lobbypanel);
        pack();

        startTimer(lobbypanel);
    }

    public void startTimer(JPanel panel){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final Timer timer = new Timer(refresh, new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        panel.repaint();
                    }
                });
                timer.start();
            }
        });
    }

    public void joinGame(){
        System.out.println("Wywolano joingame");
        lobbypanel  = new LobbyPanel(this,false);
        if(lobbypanel.gameKey != null){
            remove(startpanel);
            add(lobbypanel);
            pack();

            startTimer(lobbypanel);
        }
    }

    public void startBot(String botLevel){
        remove(startpanel);
        panel = new GamePanel(this,botLevel);
        add(panel);
        pack();
        this.setLocationRelativeTo(null);
    }


    public void startOnline(){
        remove(lobbypanel);
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
                                // pobiera z neta ruch przeciwnika
                                panel.opponentMove();
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

/**
    public Frame(){
        super("Gomoku");
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
 **/
}
